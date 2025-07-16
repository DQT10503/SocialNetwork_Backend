-- Chèn 10 bản ghi mẫu vào bảng tbl_user
INSERT INTO public.tbl_user (full_name, email, user_name, password_hash, bio, avatar_url, created_at, created_by,
                             updated_at, updated_by, status)
VALUES ('John Doe', 'johndoe@example.com', 'johndoe', 'hashedpassword123',
        'A software engineer specializing in backend development.',
        'https://example.com/avatar1.jpg', '2024-12-24 08:40:00', 'admin',
        '2024-12-24 08:40:00', 'admin', 'ACTIVE'),

       ('Jane Smith', 'janesmith@example.com', 'janesmith', 'hashedpassword456',
        'Data scientist with a passion for AI.',
        'https://example.com/avatar2.jpg', '2024-12-24 09:00:00', 'admin',
        '2024-12-24 09:00:00', 'admin', 'INACTIVE'),

       ('Alice Johnson', 'alicejohnson@example.com', 'alicej', 'hashedpassword789',
        'Project manager with extensive experience in agile methodologies.',
        'https://example.com/avatar3.jpg', '2024-12-24 10:00:00', 'admin',
        '2024-12-24 10:00:00', 'admin', 'ACTIVE'),

       ('Bob Brown', 'bobbrown@example.com', 'bobbrown', 'hashedpassword101',
        'A UX/UI designer focusing on creating intuitive user experiences.',
        'https://example.com/avatar4.jpg', '2024-12-24 11:00:00', 'admin',
        '2024-12-24 11:00:00', 'admin', 'INACTIVE'),

       ('Charlie Davis', 'charlied@example.com', 'charlied', 'hashedpassword202',
        'Front-end developer specializing in Angular and React.',
        'https://example.com/avatar5.jpg', '2024-12-24 12:00:00', 'admin',
        '2024-12-24 12:00:00', 'admin', 'ACTIVE'),

       ('Eve Wilson', 'evewilson@example.com', 'evewilson', 'hashedpassword303',
        'Back-end developer with a strong focus on Java and Spring Boot.',
        'https://example.com/avatar6.jpg', '2024-12-24 13:00:00', 'admin',
        '2024-12-24 13:00:00', 'admin', 'ACTIVE'),

       ('Frank Miller', 'frankmiller@example.com', 'frankm', 'hashedpassword404',
        'Database administrator experienced in PostgreSQL and MySQL.',
        'https://example.com/avatar7.jpg', '2024-12-24 14:00:00', 'admin',
        '2024-12-24 14:00:00', 'admin', 'INACTIVE'),

       ('Grace Lee', 'gracelee@example.com', 'gracelee', 'hashedpassword505',
        'Cloud architect with expertise in AWS and Azure solutions.',
        'https://example.com/avatar8.jpg', '2024-12-24 15:00:00', 'admin',
        '2024-12-24 15:00:00', 'admin', 'ACTIVE'),

       ('Henry Adams', 'henryadams@example.com', 'henryadams', 'hashedpassword606',
        'Cybersecurity expert with a focus on vulnerability assessment.',
        'https://example.com/avatar9.jpg', '2024-12-24 16:00:00', 'admin',
        '2024-12-24 16:00:00', 'admin', 'ACTIVE'),

       ('Ivy Scott', 'ivyscott@example.com', 'ivyscott', 'hashedpassword707',
        'DevOps engineer skilled in CI/CD pipelines and containerization.',
        'https://example.com/avatar10.jpg', '2024-12-24 17:00:00', 'admin',
        '2024-12-24 17:00:00', 'admin', 'INACTIVE');

INSERT INTO public.tbl_user_profile (id, phone, birthday, gender, location, website, occupation, education, interests,
                                     created_at, created_by, updated_at, updated_by, status)
VALUES (1, '0123456789', '1990-01-01', 1, 'Hanoi', 'https://example1.com', 'Developer', 'University A',
        ARRAY ['Coding', 'Music'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (2, '0987654321', '1992-05-15', 0, 'Ho Chi Minh', 'https://example2.com', 'Designer', 'University B',
        ARRAY ['Drawing', 'Traveling'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (3, '0934567890', '1985-03-10', 1, 'Da Nang', 'https://example3.com', 'Engineer', 'University C',
        ARRAY ['Reading', 'Sports'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (4, '0976543210', '1995-07-20', 0, 'Hai Phong', 'https://example4.com', 'Teacher', 'University D',
        ARRAY ['Writing', 'Cooking'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (5, '0943216789', '1988-11-11', 1, 'Can Tho', 'https://example5.com', 'Manager', 'University E',
        ARRAY ['Traveling', 'Photography'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (6, '0923456789', '1990-02-14', 0, 'Hue', 'https://example6.com', 'Doctor', 'University F',
        ARRAY ['Movies', 'Gardening'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (7, '0967891234', '1987-06-06', 1, 'Quang Ninh', 'https://example7.com', 'Scientist', 'University G',
        ARRAY ['Research', 'Gaming'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (8, '0912345678', '1993-09-09', 0, 'Vung Tau', 'https://example8.com', 'Pilot', 'University H',
        ARRAY ['Flying', 'Swimming'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (9, '0981234567', '1984-12-24', 1, 'Nha Trang', 'https://example9.com', 'Chef', 'University I',
        ARRAY ['Cooking', 'Cycling'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (10, '0954321678', '1989-04-01', 0, 'Phu Quoc', 'https://example10.com', 'Writer', 'University J',
        ARRAY ['Reading', 'Blogging'], NOW(), 'admin', NOW(), 'admin', 'ACTIVE');

INSERT INTO public.tbl_role (name, created_at, created_by, updated_at, updated_by, status)
VALUES ('ROLE_ADMIN', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_USER', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_MANAGER', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_EDITOR', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_VIEWER', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_SUPPORT', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_GUEST', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_SUPERVISOR', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_ANALYST', NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       ('ROLE_DEVELOPER', NOW(), 'system', NOW(), 'system', 'ACTIVE');

INSERT INTO public.tbl_user_role (user_id, role_id, created_at, created_by, updated_at, updated_by, status)
VALUES (1, 1, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (2, 2, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (3, 3, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (4, 4, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (5, 5, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (6, 6, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (7, 7, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (8, 8, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (9, 9, NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (10, 10, NOW(), 'system', NOW(), 'system', 'ACTIVE');

INSERT INTO public.tbl_user_setting (id, notification, privacy_level, theme, language, created_at, created_by,
                                     updated_at, updated_by, status)
VALUES (1, true, 'PRIVATE', 'DARK', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (2, false, 'PUBLIC', 'LIGHT', 'VIE', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (3, true, 'PRIVATE', 'DARK', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (4, false, 'PUBLIC', 'LIGHT', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (5, true, 'PRIVATE', 'DARK', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (6, false, 'PUBLIC', 'LIGHT', 'VIE', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (7, true, 'PRIVATE', 'DARK', 'VIE', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (8, false, 'PUBLIC', 'LIGHT', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (9, true, 'PRIVATE', 'DARK', 'ENG', NOW(), 'admin', NOW(), 'admin', 'ACTIVE'),
       (10, false, 'PUBLIC', 'LIGHT', 'VIE', NOW(), 'admin', NOW(), 'admin', 'ACTIVE');

INSERT INTO public.tbl_user_device (user_id, device_token, device_type, last_login, created_at, created_by, updated_at,
                                    updated_by, status)
VALUES (1, 'token001', 'MOBILE', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (2, 'token002', 'DESKTOP', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (3, 'token003', 'MOBILE', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (4, 'token004', 'DESKTOP', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (5, 'token005', 'MOBILE', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (6, 'token006', 'DESKTOP', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (7, 'token007', 'MOBILE', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (8, 'token008', 'DESKTOP', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (9, 'token009', 'MOBILE', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE'),
       (10, 'token010', 'DESKTOP', NOW(), NOW(), 'system', NOW(), 'system', 'ACTIVE');

INSERT INTO public.tbl_verification_token (user_id, access_token, access_token_expired, refresh_token,
                                           refresh_token_expired, type, created_at, created_by, updated_at, updated_by,
                                           status)
VALUES (1, 'access-token-001', '2024-12-31 23:59:59', 'refresh-token-001', '2025-01-15 23:59:59', 'EMAIL', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (2, 'access-token-002', '2024-12-31 23:59:59', 'refresh-token-002', '2025-01-15 23:59:59', 'SMS', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (3, 'access-token-003', '2024-12-31 23:59:59', 'refresh-token-003', '2025-01-15 23:59:59', 'EMAIL', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (4, 'access-token-004', '2024-12-31 23:59:59', 'refresh-token-004', '2025-01-15 23:59:59', 'SMS', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (5, 'access-token-005', '2024-12-31 23:59:59', 'refresh-token-005', '2025-01-15 23:59:59', 'EMAIL', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (6, 'access-token-006', '2024-12-31 23:59:59', 'refresh-token-006', '2025-01-15 23:59:59', 'SMS', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (7, 'access-token-007', '2024-12-31 23:59:59', 'refresh-token-007', '2025-01-15 23:59:59', 'EMAIL', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (8, 'access-token-008', '2024-12-31 23:59:59', 'refresh-token-008', '2025-01-15 23:59', 'SMS', NOW(), 'system',
        NOW(), 'system', 'ACTIVE'),
       (9, 'access-token-009', '2024-12-31 23:59:59', 'refresh-token-009', '2025-01-15 23:59:59', 'EMAIL', NOW(),
        'system', NOW(), 'system', 'ACTIVE'),
       (10, 'access-token-010', '2024-12-31 23:59:59', 'refresh-token-010', '2025-01-15 23:59:59', 'SMS', NOW(),
        'system', NOW(), 'system', 'ACTIVE');