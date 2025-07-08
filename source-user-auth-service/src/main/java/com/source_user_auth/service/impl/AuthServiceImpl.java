package com.source_user_auth.service.impl;

import com.api.framework.exception.BusinessException;
import com.api.framework.utils.Constants;
import com.api.framework.utils.MessageUtil;
import com.api.framework.utils.Utilities;
import com.source_user_auth.domain.auth.LoginRequest;
import com.source_user_auth.domain.auth.LoginResponse;
import com.source_user_auth.domain.auth.RegisterRequest;
import com.source_user_auth.domain.auth.RegisterResponse;
import com.source_user_auth.domain.keycloak.TokenResponse;
import com.source_user_auth.entity.TblRole;
import com.source_user_auth.entity.TblUser;
import com.source_user_auth.entity.TblUserRole;
import com.source_user_auth.entity.repository.TblRoleRepository;
import com.source_user_auth.entity.repository.TblUserRepository;
import com.source_user_auth.entity.repository.TblUserRoleRepository;
import com.source_user_auth.service.AuthService;
import com.source_user_auth.service.retrofit.KeycloakApiService;
import com.source_user_auth.utils.MessageCode;
import com.source_user_auth.utils.enummerate.AuthStatus;
import com.source_user_auth.utils.enummerate.RoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.resource}")
    private String resource;
    @Value("${keycloak-properties.client-id}")
    private String clientUUID;
    @Value("${keycloak-properties.client-secret}")
    private String clientSecret;
    @Value("${keycloak-properties.grant-type}")
    private String grantType;
    @Value("${keycloak-properties.username-admin}")
    private String usernameAdmin;
    @Value("${keycloak-properties.password-admin}")
    private String passwordAdmin;

    private final TblUserRepository userRepository;
    private final TblUserRoleRepository userRoleRepository;
    private final TblRoleRepository roleRepository;
    private final MessageUtil messageUtil;
    private final KeycloakApiService keycloakApiService;

    public AuthServiceImpl(TblUserRepository userRepository, TblUserRoleRepository userRoleRepository, TblRoleRepository roleRepository, MessageUtil messageUtil, KeycloakApiService keycloakApiService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.messageUtil = messageUtil;
        this.keycloakApiService = keycloakApiService;
    }

    @Override
    public LoginResponse register(RegisterRequest request) throws IOException {
        TblUser existsUser = userRepository.findByEmailOrPhoneAndStatus(request.getEmail(), request.getPhone(), AuthStatus.ACTIVE);
        if (Objects.nonNull(existsUser)) {
            throw new BusinessException(MessageCode.DUPLICATE, messageUtil.getMessage(MessageCode.DUPLICATE), "Email: " + request.getEmail() + ", Phone: " + request.getPhone() + ", Status: " + AuthStatus.ACTIVE);
        }

        TblUser user = Utilities.copyProperties(request, TblUser.class);
        user.setStatus(AuthStatus.ACTIVE);
        user.setAvatarUrl("abc.jpg");
        user.setUsername(generateUsername());
        userRepository.save(user);
        TblRole role = roleRepository.findByName(RoleEnum.ROLE_USER.getValue());
        TblUserRole userRole = new TblUserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRole.setStatus(AuthStatus.ACTIVE);
        userRoleRepository.save(userRole);

        saveUserInKeycloak(user);
        return login(new LoginRequest(user.getUsername(), user.getPassword()));
    }

    @Override
    public LoginResponse login(LoginRequest request) throws IOException {
        Call<TokenResponse> call = keycloakApiService.getToken(realm, resource, grantType, clientSecret, request.getUsername(), request.getPassword());
        Response<TokenResponse> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            logger.error("Failed to login");
            throw new BusinessException(MessageCode.INCORRECT_INFORMATION, response.errorBody().string(), "Username: " + request.getUsername() + "; Password: " + request.getPassword());
        }
        return Utilities.copyProperties(response.body(), LoginResponse.class);
    }

    private String generateUsername() {
        Random random = new Random();
        String username;
        do {
            username = String.valueOf(10000 + random.nextInt(900000));
        } while (userRepository.existsByUsername(username));
        return username;
    }

    private void saveUserInKeycloak(TblUser user) throws IOException {
        String tokenAdmin = "Bearer " + getTokenAdmin();
        List<Map<String, Object>> users;

        try {
            Map<String, Object> roleMap = getClientRole(tokenAdmin);
            List<Map<String, Object>> clientRole = Collections.singletonList(roleMap);
            createUserInKeycloak(tokenAdmin, user);
            users = getUserByUsername(user.getUsername(), tokenAdmin);
            if (users.isEmpty()) {
                throw new BusinessException(Constants.ERR_404, messageUtil.getMessage(Constants.ERR_404), "Username: " + user.getUsername());
            }
            assignRoleToUser(tokenAdmin, users, clientRole);
        } catch (Exception e) {
            List<Map<String, Object>> usersToDelete = getUserByUsername(user.getUsername(), tokenAdmin);
            if (!usersToDelete.isEmpty()) {
                deleteUser(tokenAdmin, (String) usersToDelete.get(0).get("id"));
            }
            throw new BusinessException("KEYCLOAK_ERROR", e.getMessage(), "User: " + user);
        }
    }

    private String getTokenAdmin() throws IOException {
        Call<TokenResponse> call = keycloakApiService.getToken(realm, resource, grantType, clientSecret, usernameAdmin, passwordAdmin);
        Response<TokenResponse> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to get token: " + response.errorBody().string());
        }

        logger.info("Get token admin successfully: {}", response.body().getAccessToken());
        return response.body().getAccessToken();
    }

    private void createUserInKeycloak(String tokenAdmin, TblUser user) throws IOException {
        // Tạo user
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("username", user.getUsername());
        userMap.put("email", user.getEmail());
        userMap.put("enabled", true);
        userMap.put("firstName", user.getFullName().split(" ")[0]);
        userMap.put("lastName", user.getFullName().substring(user.getFullName().indexOf(" ") + 1));
        userMap.put("attributes", Collections.singletonMap("phone", user.getPhone())); // lưu phone vào attributes với key là "phone"; tạo một Map chỉ có một cặp key-value
        // Tạo danh sách credentials
        Map<String, Object> credentials = new HashMap<>();
        credentials.put("type", "password");
        credentials.put("value", user.getPassword());
        credentials.put("temporary", false); // không bắt buộc user đổi mật khẩu khi đăng nhập lần đầu

        List<Map<String, Object>> credentialsList = new ArrayList<>();
        credentialsList.add(credentials); // thêm credentials vào List vì trong Keycloak, credentials là một list
        userMap.put("credentials", credentialsList); // thêm list credentials vào userMap

        Call<Void> call = keycloakApiService.createUser(tokenAdmin, realm, userMap);
        Response<Void> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to create user: " + response.errorBody().string());
        }
        logger.info("Create user successfully: {}", user);
    }

    private void deleteUser(String tokenAdmin, String userId) throws IOException {
        Call<Void> call = keycloakApiService.deleteUser(tokenAdmin, realm, userId);
        Response<Void> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to delete user: " + response.errorBody().string());
        }
        logger.info("Delete user successfully: {}", userId);
    }

    private List<Map<String, Object>> getUserByUsername(String username, String tokenAdmin) throws IOException {
        Call<List<Map<String, Object>>> call = keycloakApiService.detailUser(tokenAdmin, realm, username);
        Response<List<Map<String, Object>>> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to get user: " + response.errorBody().string());
        }
        return response.body();
    }

    private Map<String, Object> getClientRole(String tokenAdmin) throws IOException {
        Call<Map<String, Object>> call = keycloakApiService.getClientRole(tokenAdmin, realm, clientUUID, StringUtils.lowerCase(RoleEnum.ROLE_USER.toString()));
        Response<Map<String, Object>> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to get client role: " + response.errorBody().string());
        }
        return response.body();
    }

    private void assignRoleToUser(String tokenAdmin, List<Map<String, Object>> user, List<Map<String, Object>> roles) throws IOException {
        Call<Void> call = keycloakApiService.assignRoleToUser(tokenAdmin, realm, user.get(0).get("id").toString(), clientUUID, roles);
        Response<Void> response = call.execute();
        if (!response.isSuccessful() && Objects.nonNull(response.errorBody())) {
            throw new RuntimeException("Failed to assign role: " + response.errorBody().string());
        }
        logger.info("Assign role {} successfully for user {}", roles, user);
    }

}
