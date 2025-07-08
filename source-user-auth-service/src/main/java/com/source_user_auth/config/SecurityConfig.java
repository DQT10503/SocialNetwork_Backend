package com.source_user_auth.config;

import com.source_user_auth.security.AuthenticationEntry;
import com.source_user_auth.security.SecurityConfigurer;
import com.source_user_auth.security.TokenProvider;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Đánh dấu đây là cấu hình dành cho Keycloak Adapter
// Giúp tự động cấu hình các bean cần thiết để tích hợp Keycloak với Spring Security
@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    // TokenProvider: Một lớp quản lý và xử lý các token: như là kiểm tra tính hợp lệ, trích xuất thông tin từ token
    @Autowired
    private TokenProvider tokenProvider;
    // Xử lý lỗi xác thực khi người dùng không có quyền try cập
    @Autowired
    private AuthenticationEntry authenticationEntry;

    // Danh sách các phương thức HTTP được phép, bao gồm: GET, POST, PUT, DELETE, OPTIONS
    private static final List<String> DEFAULT_PERMIT_METHODS = Collections.unmodifiableList(
            Arrays.asList(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name()));

    /**
        * Các URL không yêu cầu xác thực: dành cho các tài nguyên tĩnh hoặc công cụ tài liệu API như Swagger
        * "/resources/**":
            - Tài nguyên tĩnh trong ứng dụng như CSS, JS, hình ảnh...
        * "/v2/api-docs":
            - Cung cấp tài liệu API ở định dạng JSON. Swagger UI sẽ sử dụng đường dẫn này để lấy cấu hình API
            - Đường dẫn này do thư viện Swagger hoặc Springfox tạo ra.
        * "/configuration/ui":
            - Cung cấp cấu hình giao diện người dùng cho Swagger.
        * "/swagger-resource/**":
            - Các tài nguyên liên quan đến Swagger, chẳng hạn như cấu hình hoặc thông tin hỗ trợ cần thết để vận hành Swagger UI.
            - Ví dụ: /swagger-resource/configuration  /swagger-resource/swagger-ui-parameters.
            - Mục đích: Hỗ trợ Swagger hoạt động đúng cách.
        * "/configuration/security":
            - Cung cấp các cấu hình liên quan đến bảo mật cho Swagger.
            - Dùng để hiển thị hoặc cấu hình các phương thức xác thực (nếu Swagger có hỗ trợ các API cần bảo mật.
        * "/swagger-ui.html":
            - Ý nghĩa: Đường dẫn truy cập giao diện người dùng của Swagger UI.
            - Truy cập: http://localhost:6789/swagger-ui.html.
        * "/webjars/**":
            - Ý nghĩa: truy cập các tài nguyên được đóng gói sẵn trong thư viện WebJars: phân phối các thư viện front-end thông qua maven hoặc gradle.
     */
    private static final String[] WHITE_LIST = {
            "/resources/**",
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resource/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper(); // Một mapper đơn giản để chuyển đổi các quyền nhận được thành định dạng mong muốn, chuyển các quyền từ Keycloak thành các quyền mà SprSec hiểu được.
        grantedAuthorityMapper.setConvertToUpperCase(true); // Chuyển đổi thành chữ in hoa: "user" -> "USER"

        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider(); // Tích hợp xác thực từ Keycloak, là một phương thức trong lớp KeycloakWebSecurityConfigurerAdapter giúp lấy Keycloak mặc định.
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper); // Đặt mapper vào KeycloakAuthenticationProvider để xử lý quyền từ Keycloak.
        auth.authenticationProvider(keycloakAuthenticationProvider); // Đăng ký KeyCloakAuthenticationProvider với AuthenticationManagerBuilder: đảm bảo SprSec sử dụng Keycloak để xác thực.
    }

    // Theo dõi các phiên đăng nhập đang hoạt động (active sessions) của người dùng.
    // Lưu trữ thông tin về mỗi phiên đăng nhập: username, session id, thông tin xác thực (principal).
    // Có thể triển khai một số tính năng như: giới hạn thiết bị đăng nhập đồng thời, hiển thị danh sách người dùng trực tuyến, đăng xuất từ xa.
    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * CORS (Cross-Origin Resource Sharing):
        - Cho phép ứng dụng nhận các request từ các domain khác nhau.
        - corsConfigurationSource(): là một bean cung cấp cấu hình CORS, như danh sách domain được phép, các phương thức HTTP được phép.
        - Mục tiêu ngăn chặn lỗi CORS khi ứng dụng front-end và back-end chạy ở các domain khác nhau.
     * http.httpBasic.disable():
        - HTTP Basic Authentication sử dụng header HTTP Authorization để truyền thông tin username và password trong mỗi request
        - Vô hiệu hóa vì ứng dụng có thể đang sử dụng một cơ chế bảo mật khác như JWT.
     * http.csrf().disable(): (Cross-Site Request Forgery)
        - Bảo vệ chống lại việc gửi request độc hại thay mặt người dùng.
        - Vô hiệu hóa CSRF: Phù hợp với các ứng dụng API RESTful vì các request được bảo mật bằng Token thay vì session.
     * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS):
        - Session Creation Policy: STATELESS
            + Ứng dụng không duy trì trạng thái người dùng trong session
            + Mỗi request đều phải gửi token để xác thực. Không có thông tin nào được lưu trong session của server.
     * http.exceptionHandling().authenticationEntryPoint(authenticationEntry):
        - Xử lý ngoại lệ:
            + Khi người dùng gửi request mà không có thông tin xác thực hoặc xác thực không hợp lệ, SprSec sẽ chuyển hướng request tới authenticationEntry.
            + authenticationEntry: là một bean được cấu hình để trả về phản hồi phù hợp (VD: 401 Unauthorized)
     * http.apply(new SecurityConfigurer(tokenProvider)):
        - SecurityConfigurer: là một lớp tùy chỉnh do bạn tạo ra để tích hợp các thành phần bảo mật khác.
        - tokenProvider: bean xử lý việc tạo và xác thực JWT.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource());
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/ping").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .anyRequest().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntry)
                .and().apply(new SecurityConfigurer(tokenProvider));
    }

    // configure(WebSecurity web) trong SprSec được sử dụng để loại trừ một số URL ra khỏi các quy tắc bảo mật.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(WHITE_LIST); // các request trong WHITE_LIST sẽ không đi qua Spring Security filter chain.
    }

    // Định nghĩa một CORS configuration cho ứng dụng, cho phép kiểm soát các
    // request từ các nguồn khác nhau
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); // cho pho tất cả các nguồn gửi request đến server.
        configuration.setAllowCredentials(true); // cho phép gửi cookie hoặc thông tin xác thực trong request.
        configuration.setAllowedHeaders(Arrays.asList("*")); // cho phép tất cả các loại headers.
        configuration.setAllowedMethods(DEFAULT_PERMIT_METHODS); // Cho phép các phương thức HTTP.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // Ánh xạ các quy tắc CORS cho từng endpoint
        source.registerCorsConfiguration("/**", configuration); // Áp dụng cấu hình CORS cho tất cả các endpoint.
        return source;
    }

    // Override method sessionAuthenticationStrategy() từ lớp cha. Nó định nghĩa chiến lược xử lý xác thực phiên.
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
}
