# DBDiagram (dbdiagram.io) Note

---

## DB Diagram (template) Note

Table users {
  id bigint [pk, increment]
  firstname varchar(100)
  lastname varchar(100)
  email varchar [unique]
  avatar varchar
  password varchar
  ip_address varchar
  facebook_id varchar [unique]
  google_id varchar [unique]
  email_verified_at timestamp
  status boolean [default: true, note: "0:Banned,1:Active"]
  google2fa_status boolean [default: false, note: "0:Disabled,1:Active"]
  google2fa_secret text
  remember_token varchar
  created_at timestamp
  updated_at timestamp
}

Table password_resets {
  email varchar
  token varchar
  created_at timestamp

  Indexes {
    (email)
  }
}

Table oauth_providers {
  id serial [pk]
  name varchar
  alias varchar
  icon varchar
  credentials longtext
  instructions text
  status boolean [default: false]
  created_at timestamp
  updated_at timestamp
}

Table roles {
  id serial [pk]
  name varchar [unique]
  created_at timestamp
  updated_at timestamp
}

Table role_user {
  id serial [pk]
  role_id bigint
  user_id bigint
}

Table departments {
  id serial [pk]
  name varchar
  created_at timestamp
  updated_at timestamp
}

Table department_user {
  id serial [pk]
  department_id bigint
  user_id bigint
}

Table pages {
  id serial [pk]
  title varchar
  slug varchar [unique]
  body text
  short_description varchar(200)
  views bigint [default: 0]
  created_at timestamp
  updated_at timestamp
}

Table settings {
  id bigint [pk, increment]
  key varchar
  value text
}

Table navbar_menu {
  id bigint [pk, increment]
  name varchar(100) [unique]
  link text
  parent_id bigint
  order bigint [default: 0]
  created_at timestamp
  updated_at timestamp
}

Table footer_menu {
  id int [pk, increment]
  name varchar(100) [unique]
  link text
  sort_id int
  created_at timestamp
  updated_at timestamp
}

Table extensions {
  id bigint [pk, increment]
  name varchar
  alias varchar
  logo varchar
  credentials longtext
  instructions text
  status boolean [default: false, note: "0:Disabled,1:Enabled"]
  created_at timestamp
  updated_at timestamp
}

Table mail_templates {
  id bigint [pk, increment]
  alias varchar
  name varchar
  subject varchar
  body longtext
  shortcodes longtext
  status boolean [default: true]
}

Table editor_files {
  id serial [pk]
  path varchar
  created_at timestamp
  updated_at timestamp
}

Table categories {
  id serial [pk]
  name varchar
  slug varchar
  icon varchar
  views bigint [default: 0]
  created_at timestamp
  updated_at timestamp
}

Table articles {
  id bigint [pk, increment]
  title varchar
  slug varchar [unique]
  body text
  short_description varchar(200)
  views bigint [default: 0]
  likes bigint [default: 0]
  dislikes bigint [default: 0]
  created_at timestamp
  updated_at timestamp
}

Table article_category {
  id serial [pk]
  article_id bigint
  category_id bigint
}

Table tickets {
  id bigint [pk, increment, note: "starts at 1000"]
  subject varchar
  priority tinyint [default: 1]    // 1:Normal,2:Low,3:High,4:Urgent
  status tinyint [default: 1]      // 1:Opened,2:Closed
  user_id bigint
  department_id bigint
  created_at timestamp
  updated_at timestamp
}

Table ticket_replies {
  id bigint [pk, increment]
  body longtext
  user_id bigint
  ticket_id bigint
  created_at timestamp
  updated_at timestamp
}

Table ticket_reply_attachments {
  id bigint [pk, increment]
  name varchar
  path varchar
  ticket_reply_id bigint
  created_at timestamp
  updated_at timestamp
}

Table notifications {
  id bigint [pk, increment]
  user_id bigint
  title varchar
  link varchar
  image varchar
  status boolean [default: false]  // 0:Unread,1:Read
  created_at timestamp
  updated_at timestamp
}

Ref: role_user.role_id > roles.id [delete: cascade]
Ref: role_user.user_id > users.id [delete: cascade]
Ref: department_user.department_id > departments.id [delete: cascade]
Ref: department_user.user_id > users.id [delete: cascade]
Ref: navbar_menu.parent_id > navbar_menu.id [delete: cascade]
Ref: article_category.article_id > articles.id [delete: cascade]
Ref: article_category.category_id > categories.id [delete: cascade]
Ref: tickets.user_id > users.id [delete: cascade]
Ref: tickets.department_id > departments.id [delete: cascade]
Ref: ticket_replies.user_id > users.id [delete: cascade]
Ref: ticket_replies.ticket_id > tickets.id [delete: cascade]
Ref: ticket_reply_attachments.ticket_reply_id > ticket_replies.id [delete: cascade]
Ref: notifications.user_id > users.id [delete: cascade]

---

## DB Diagram (ver 0.1) Note

-- 1. Table users (đã có, bổ sung thêm một số cột)
Table users {
  id bigint [pk, increment]
  firstname varchar(100)
  lastname varchar(100)
  email varchar [unique]
  avatar varchar
  password varchar
  ip_address varchar
  facebook_id varchar [unique]
  google_id varchar [unique]
  email_verified_at timestamp
  status boolean [default: true, note: "0:Banned,1:Active"]
  google2fa_status boolean [default: false, note: "0:Disabled,1:Active"]
  google2fa_secret text
  remember_token varchar
  last_login_at timestamp [note: "Thời gian đăng nhập cuối"]
  login_attempts int [default: 0, note: "Số lần đăng nhập sai"]
  locked_until timestamp [note: "Khóa tài khoản đến thời gian này"]
  created_at timestamp
  updated_at timestamp
}

-- 2. Table password_resets (giữ nguyên)
Table password_resets {
  email varchar
  token varchar
  created_at timestamp

  Indexes {
    (email)
  }
}

-- 3. Table roles (bổ sung thêm cột)
Table roles {
  id serial [pk]
  name varchar [unique]
  display_name varchar [note: "Tên hiển thị"]
  description text [note: "Mô tả vai trò"]
  is_default boolean [default: false, note: "Vai trò mặc định khi đăng ký"]
  created_at timestamp
  updated_at timestamp
}

-- 4. Table role_user (giữ nguyên)
Table role_user {
  id serial [pk]
  role_id bigint
  user_id bigint
  created_at timestamp [note: "Thời gian gán vai trò"]
}

-- 5. Table permissions (mới thêm)
Table permissions {
  id serial [pk]
  name varchar [unique, note: "Tên quyền (vd: user.create, user.edit)"]
  display_name varchar [note: "Tên hiển thị"]
  description text [note: "Mô tả quyền"]
  group_name varchar [note: "Nhóm quyền (vd: user, article, ticket)"]
  created_at timestamp
  updated_at timestamp
}

-- 6. Table role_permissions (mới thêm)
Table role_permissions {
  id serial [pk]
  role_id bigint
  permission_id bigint
  created_at timestamp
}

-- 7. Table user_permissions (mới thêm - quyền đặc biệt cho user)
Table user_permissions {
  id serial [pk]
  user_id bigint
  permission_id bigint
  type enum('grant', 'deny') [default: 'grant', note: "grant: cấp quyền, deny: từ chối quyền"]
  created_at timestamp
}

-- 8. Table user_sessions (mới thêm)
Table user_sessions {
  id varchar [pk, note: "Session ID"]
  user_id bigint
  ip_address varchar
  user_agent text
  last_activity timestamp
  created_at timestamp
}

-- 9. Table user_login_logs (mới thêm)
Table user_login_logs {
  id bigint [pk, increment]
  user_id bigint
  ip_address varchar
  user_agent text
  login_at timestamp
  logout_at timestamp
  status enum('success', 'failed') [default: 'success']
  failure_reason varchar [note: "Lý do đăng nhập thất bại"]
  created_at timestamp
}

-- 10. Table oauth_providers (giữ nguyên)
Table oauth_providers {
  id serial [pk]
  name varchar
  alias varchar
  icon varchar
  credentials longtext
  instructions text
  status boolean [default: false]
  created_at timestamp
  updated_at timestamp
}

-- 11. Table departments (giữ nguyên)
Table departments {
  id serial [pk]
  name varchar
  created_at timestamp
  updated_at timestamp
}

-- 12. Table department_user (giữ nguyên)
Table department_user {
  id serial [pk]
  department_id bigint
  user_id bigint
}

-- ==================== RELATIONSHIPS ====================

-- User relationships
Ref: role_user.role_id > roles.id [delete: cascade]
Ref: role_user.user_id > users.id [delete: cascade]
Ref: department_user.department_id > departments.id [delete: cascade]
Ref: department_user.user_id > users.id [delete: cascade]

-- Permission relationships
Ref: role_permissions.role_id > roles.id [delete: cascade]
Ref: role_permissions.permission_id > permissions.id [delete: cascade]
Ref: user_permissions.user_id > users.id [delete: cascade]
Ref: user_permissions.permission_id > permissions.id [delete: cascade]

-- Session & Log relationships
Ref: user_sessions.user_id > users.id [delete: cascade]
Ref: user_login_logs.user_id > users.id [delete: cascade]

-- ==================== INDEXES ====================

Table users {
  Indexes {
    (email)
    (status)
    (created_at)
  }
}

Table permissions {
  Indexes {
    (name)
    (group_name)
  }
}

Table role_permissions {
  Indexes {
    (role_id, permission_id) [unique]
  }
}

Table user_permissions {
  Indexes {
    (user_id, permission_id) [unique]
  }
}

Table user_sessions {
  Indexes {
    (user_id)
    (last_activity)
  }
}

Table user_login_logs {
  Indexes {
    (user_id)
    (login_at)
    (status)
  }
}

-- ==================== SAMPLE DATA ====================

-- Dữ liệu mẫu cho roles
/* 
INSERT INTO roles (name, display_name, description, is_default) VALUES
('super_admin', 'Super Admin', 'Quản trị viên tối cao', false),
('admin', 'Admin', 'Quản trị viên', false),
('user', 'User', 'Người dùng thường', true);
*/

-- Dữ liệu mẫu cho permissions
/*
INSERT INTO permissions (name, display_name, description, group_name) VALUES
-- User management
('user.view', 'Xem người dùng', 'Xem danh sách người dùng', 'user'),
('user.create', 'Tạo người dùng', 'Tạo người dùng mới', 'user'),
('user.edit', 'Sửa người dùng', 'Chỉnh sửa thông tin người dùng', 'user'),
('user.delete', 'Xóa người dùng', 'Xóa người dùng', 'user'),
('user.ban', 'Khóa người dùng', 'Khóa/mở khóa người dùng', 'user'),

-- Role management
('role.view', 'Xem vai trò', 'Xem danh sách vai trò', 'role'),
('role.create', 'Tạo vai trò', 'Tạo vai trò mới', 'role'),
('role.edit', 'Sửa vai trò', 'Chỉnh sửa vai trò', 'role'),
('role.delete', 'Xóa vai trò', 'Xóa vai trò', 'role'),
('role.assign', 'Gán vai trò', 'Gán vai trò cho người dùng', 'role'),

-- Permission management
('permission.view', 'Xem quyền', 'Xem danh sách quyền', 'permission'),
('permission.assign', 'Gán quyền', 'Gán quyền cho vai trò', 'permission'),

-- Dashboard
('dashboard.view', 'Xem dashboard', 'Truy cập trang quản trị', 'dashboard');
*/

---

## DB Diagram (ver 0.2) Note

// Bảng người dùng chính
Table users {
  id bigint [pk, increment]
  firstname varchar(100)
  lastname varchar(100)
  email varchar(255) [unique, not null]
  avatar varchar(255)
  password varchar(255) [not null]
  phone varchar(20)
  date_of_birth date
  gender enum('male', 'female', 'other')
  ip_address varchar(45)
  facebook_id varchar(255) [unique]
  google_id varchar(255) [unique]
  email_verified_at timestamp
  phone_verified_at timestamp
  last_login_at timestamp
  status boolean [default: true, note: "0:Banned,1:Active"]
  google2fa_status boolean [default: false, note: "0:Disabled,1:Active"]
  google2fa_secret text
  remember_token varchar(100)
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng reset mật khẩu
Table password_resets {
  id bigint [pk, increment]
  email varchar(255) [not null]
  token varchar(255) [not null]
  expires_at timestamp [not null]
  created_at timestamp [default: `now()`]

  Indexes {
    (email)
    (token)
  }
}

// Bảng xác thực email
Table email_verifications {
  id bigint [pk, increment]
  user_id bigint [not null]
  token varchar(255) [not null]
  expires_at timestamp [not null]
  created_at timestamp [default: `now()`]

  Indexes {
    (user_id)
    (token)
  }
}

// Bảng vai trò
Table roles {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  is_default boolean [default: false]
  status boolean [default: true]
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng quyền
Table permissions {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  group_name varchar(50) [note: "Nhóm quyền: users, roles, dashboard, etc."]
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng trung gian vai trò - người dùng
Table role_user {
  id bigint [pk, increment]
  role_id bigint [not null]
  user_id bigint [not null]
  assigned_by bigint [note: "ID của người gán quyền"]
  assigned_at timestamp [default: `now()`]

  Indexes {
    (role_id, user_id) [unique]
  }
}

// Bảng trung gian vai trò - quyền
Table role_permissions {
  id bigint [pk, increment]
  role_id bigint [not null]
  permission_id bigint [not null]
  created_at timestamp [default: `now()`]

  Indexes {
    (role_id, permission_id) [unique]
  }
}

// Bảng quyền trực tiếp cho người dùng (optional)
Table user_permissions {
  id bigint [pk, increment]
  user_id bigint [not null]
  permission_id bigint [not null]
  granted_by bigint [note: "ID của người cấp quyền"]
  granted_at timestamp [default: `now()`]

  Indexes {
    (user_id, permission_id) [unique]
  }
}

// Bảng phiên đăng nhập
Table user_sessions {
  id bigint [pk, increment]
  user_id bigint [not null]
  session_token varchar(255) [unique, not null]
  ip_address varchar(45)
  user_agent text
  expires_at timestamp [not null]
  last_activity timestamp [default: `now()`]
  created_at timestamp [default: `now()`]

  Indexes {
    (user_id)
    (session_token)
    (expires_at)
  }
}

// Bảng log hoạt động
Table activity_logs {
  id bigint [pk, increment]
  user_id bigint
  action varchar(100) [not null]
  description text
  ip_address varchar(45)
  user_agent text
  created_at timestamp [default: `now()`]

  Indexes {
    (user_id)
    (action)
    (created_at)
  }
}

// Bảng nhà cung cấp OAuth
Table oauth_providers {
  id bigint [pk, increment]
  name varchar(50) [unique, not null]
  display_name varchar(100) [not null]
  client_id varchar(255) [not null]
  client_secret varchar(255) [not null]
  redirect_url varchar(255) [not null]
  scopes text
  status boolean [default: true]
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Định nghĩa các mối quan hệ
Ref: role_user.role_id > roles.id [delete: cascade]
Ref: role_user.user_id > users.id [delete: cascade]
Ref: role_user.assigned_by > users.id [delete: set null]

Ref: role_permissions.role_id > roles.id [delete: cascade]
Ref: role_permissions.permission_id > permissions.id [delete: cascade]

Ref: user_permissions.user_id > users.id [delete: cascade]
Ref: user_permissions.permission_id > permissions.id [delete: cascade]
Ref: user_permissions.granted_by > users.id [delete: set null]

Ref: user_sessions.user_id > users.id [delete: cascade]
Ref: activity_logs.user_id > users.id [delete: cascade]
Ref: email_verifications.user_id > users.id [delete: cascade]

---

## DB Diagram (ver 0.3) Note

// Bảng người dùng
Table users {
  id bigint [pk, increment]
  firstname varchar(100)
  lastname varchar(100)
  email varchar(255) [unique, not null]
  password varchar(255) [not null]
  avatar varchar(255)
  phone varchar(20)
  email_verified_at timestamp
  last_login_at timestamp
  status boolean [default: true, note: "0:Banned,1:Active,2:deleted"]
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng reset mật khẩu
Table password_resets {
  id bigint [pk, increment]
  email varchar(255) [not null]
  token varchar(255) [not null]
  expires_at timestamp [not null]
  created_at timestamp [default: `now()`]
}

// Bảng vai trò
Table roles {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng quyền
Table permissions {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng gán vai trò cho người dùng
Table role_user {
  id bigint [pk, increment]
  role_id bigint [not null]
  user_id bigint [not null]
  created_at timestamp [default: `now()`]
}

// Bảng gán quyền cho vai trò
Table role_permissions {
  id bigint [pk, increment]
  role_id bigint [not null]
  permission_id bigint [not null]
  created_at timestamp [default: `now()`]
}

// Mối quan hệ giữa các bảng
Ref: role_user.role_id > roles.id [delete: cascade]
Ref: role_user.user_id > users.id [delete: cascade]
Ref: role_permissions.role_id > roles.id [delete: cascade]
Ref: role_permissions.permission_id > permissions.id [delete: cascade]

---

## DB Diagram (ver 0.4) Note

// Bảng người dùng
Table users {
  id bigint [pk, increment]
  firstname varchar(100)
  lastname varchar(100)
  email varchar(255) [unique, not null]
  password varchar(255) [not null]
  avatar varchar(255)
  phone varchar(20)
  email_verified_at timestamp
  last_login_at timestamp
  status boolean [default: true, note: "0:Banned,1:Active"]
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng reset mật khẩu - BỎ QUA cho giai đoạn đầu
// Table password_resets {
//   id bigint [pk, increment]
//   email varchar(255) [not null]
//   token varchar(255) [not null]
//   expires_at timestamp [not null]
//   created_at timestamp [default: `now()`]
// }

// Bảng vai trò
Table roles {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng quyền
Table permissions {
  id bigint [pk, increment]
  name varchar(100) [unique, not null]
  display_name varchar(100) [not null]
  description text
  created_at timestamp [default: `now()`]
  updated_at timestamp [default: `now()`]
}

// Bảng gán vai trò cho người dùng
Table role_user {
  id bigint [pk, increment]
  role_id bigint [not null]
  user_id bigint [not null]
  created_at timestamp [default: `now()`]
}

// Bảng gán quyền cho vai trò
Table role_permissions {
  id bigint [pk, increment]
  role_id bigint [not null]
  permission_id bigint [not null]
  created_at timestamp [default: `now()`]
}

// Mối quan hệ giữa các bảng
Ref: role_user.role_id > roles.id [delete: cascade]
Ref: role_user.user_id > users.id [delete: cascade]
Ref: role_permissions.role_id > roles.id [delete: cascade]
Ref: role_permissions.permission_id > permissions.id [delete: cascade]

---

## DB Diagram (ver 0.5) Note

// Bảng người dùng

Table users {

  id bigint [pk, increment]

  firstname varchar(100)

  lastname varchar(100)

  email varchar(255) [unique, not null]

  password varchar(255) [not null]

  avatar varchar(255)

  phone varchar(20)

  email_verified_at timestamp

  last_login_at timestamp

  status tinyint [default: 1, note: "0:Banned,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng vai trò

Table roles {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  status tinyint [default: 1, note: "0:Disabled,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng quyền

Table permissions {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng gán vai trò cho người dùng

Table role_user {

  id bigint [pk, increment]

  role_id bigint [not null]

  user_id bigint [not null]

  status tinyint [default: 1, note: "0:Disabled,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

}

// Bảng gán quyền cho vai trò

Table role_permissions {

  id bigint [pk, increment]

  role_id bigint [not null]

  permission_id bigint [not null]

  created_at timestamp [default: `now()`]

}

// Mối quan hệ giữa các bảng

Ref: role_user.role_id > roles.id [delete: cascade]

Ref: role_user.user_id > users.id [delete: cascade]

Ref: role_permissions.role_id > roles.id [delete: cascade]

Ref: role_permissions.permission_id > permissions.id [delete: cascade]

---

## DB Diagram (ver 0.5) Note

// Bảng người dùng

Table users {

  id bigint [pk, increment]

  firstname varchar(100)

  lastname varchar(100)

  email varchar(255) [unique, not null]

  password varchar(255) [not null]

  avatar varchar(255)

  phone varchar(20)

  role_id bigint [not null]

  email_verified_at timestamp

  last_login_at timestamp

  status tinyint [default: 1, note: "0:Banned,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng vai trò

Table roles {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  status tinyint [default: 1, note: "0:Disabled,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng quyền

Table permissions {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng gán quyền cho vai trò

Table role_permissions {

  id bigint [pk, increment]

  role_id bigint [not null]

  permission_id bigint [not null]

  created_at timestamp [default: `now()`]

}

// Mối quan hệ giữa các bảng

Ref: users.role_id > roles.id [delete: restrict]

Ref: role_permissions.role_id > roles.id [delete: cascade]

Ref: role_permissions.permission_id > permissions.id [delete: cascade]

---

## DB Diagram (ver 0.6) Note

// Bảng người dùng

Table users {

  id bigint [pk, increment]

  firstname varchar(100)

  lastname varchar(100)

  email varchar(255) [unique, not null]

  password varchar(255) [not null]

  avatar varchar(255)

  phone varchar(20)

  role_id bigint [not null]

  email_verified_at timestamp

  last_login_at timestamp

  status tinyint [default: 1, note: "0:Banned,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng vai trò

Table roles {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  status tinyint [default: 1, note: "0:Disabled,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng quyền

Table permissions {

  id bigint [pk, increment]

  name varchar(100) [unique, not null]

  display_name varchar(100) [not null]

  description text

  created_at timestamp [default: `now()`]

  updated_at timestamp [default: `now()`]

}

// Bảng gán quyền cho người dùng

Table user_permissions {

  id bigint [pk, increment]

  user_id bigint [not null]

  permission_id bigint [not null]

  status tinyint [default: 1, note: "0:Disabled,1:Active,2:Deleted"]

  created_at timestamp [default: `now()`]

}

// Mối quan hệ giữa các bảng

Ref: users.role_id > roles.id [delete: restrict]

Ref: user_permissions.user_id > users.id [delete: cascade]

Ref: user_permissions.permission_id > permissions.id [delete: cascade]
