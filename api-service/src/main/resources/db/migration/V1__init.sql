CREATE TABLE members (
     member_id   binary(16) NOT NULL,
     email       varchar(255) NOT NULL,
     name        varchar(255) NOT NULL,
     profile_url varchar(1024),
     created_at  timestamp(6) NOT NULL,
     updated_at  timestamp(6) NOT NULL,

 primary key (member_id)
);

CREATE TABLE permissions (
     permission_id binary(16) NOT NULL,
     name          VARCHAR(50) NOT NULL,
     created_at    timestamp(6) NOT NULL,
     updated_at    timestamp(6) NOT NULL,

     primary key (permission_id)
);

CREATE TABLE flows (
    flow_id      binary(16) NOT NULL,
    member_id    binary(16),
    flow_title   varchar(255) NOT NULL,
    flow_content json NOT NULL,
    created_at   timestamp(6) NOT NULL,
    updated_at   timestamp(6) NOT NULL,

    primary key (flow_id)
);

CREATE TABLE roles (
    role_id    binary(16) NOT NULL,
    name       VARCHAR(50) NOT NULL,
    created_at timestamp(6) NOT NULL,
    updated_at timestamp(6) NOT NULL,

    primary key (role_id)
);

CREATE TABLE role_permissions (
    role_permission_id binary(16) NOT NULL,
    role_id            binary(16),
    permission_id      binary(16),

    primary key (role_permission_id)
);

CREATE TABLE member_flow_roles (
    member_flow_role_id binary(16) NOT NULL,
    member_id           binary(16),
    flow_id             binary(16),
    role_id             binary(16),

    primary key (member_flow_role_id)
);

CREATE TABLE comments (
    comment_id   binary(16) NOT NULL,
    comment_type varchar(50) NOT NULL,
    ai_provider  varchar(50),
    member_id    binary(16),
    node_id      binary(16) NOT NULL,
    flow_id      binary(16) NOT NULL,
    parent_id    binary(16),
    content      TEXT NOT NULL,
    created_at   timestamp(6) NOT NULL,
    updated_at   timestamp(6) NOT NULL,

    primary key (comment_id)
);

CREATE TABLE flow_access_tokens (
    flow_access_token_id binary(16) NOT NULL,
    token                binary(16) NOT NULL,
    flow_id              binary(16),
    role_id              binary(16),
    created_at           timestamp(6) NOT NULL,
    updated_at           timestamp(6) NOT NULL,

    primary key (flow_access_token_id)
);
