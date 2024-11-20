CREATE TABLE members (
    member_id binary(16) not null,
    email varchar(255) not null,
    name varchar(255) not null,
    profileURL varchar(500),
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,
    primary key (member_id)
);

CREATE TABLE permissions (
    action_id binary(16) not null,
    name ENUM(
        'CREATE_FLOW',
        'EDIT_FLOW',
        'VIEW_FLOW',
        'COMMENT_FLOW',
        'DELETE_FLOW'
        'SHARE_FLOW',
        'CHANGE_VISIBILITY',
        'CONFIGURE_COMMENTS'
    ) not null,

    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,
    primary key (action_id)
);

CREATE TABLE flows (
    flow_id binary(16) not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,

    primary key (flow_id)
);

CREATE TABLE roles (
    role_id binary(16) not null,
    name ENUM(
        'VIEWER',
        'EDITOR',
        'COMMENTER',
        'OWNER'
    ) not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,

    primary key (role_id)
);

CREATE TABLE role_actions (
    role_action_id binary(16) not null,
    role_id binary(16) not null,
    action_id binary(16) not null,

    primary key (role_action_id)
);

CREATE TABLE member_flow_roles (
    member_flow_role_id binary(16) not null,
    member_id binary(16) not null,
    flow_id binary(16) not null,
    role_id binary(16) not null,

    primary key (member_flow_role_id)
);

CREATE TABLE comments
(
    comment_id binary(16) NOT NULL,
    member_id  binary(16) NOT NULL,
    node_id    binary(16) NOT NULL,
    parent_id  binary(16),
    content    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    comment_type ENUM(
        'gpt-3',
        'gpt-4',
        'gpt-4o',
        'gpt-4o-mini',
        'user'
    ),

    primary key (comment_id)
);

CREATE TABLE flow_access_tokens (
    flow_access_token_id binary(16) not null,
    flow_id binary(16) not null,
    token binary(16) not null,
    role_id binary(16) not null,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null,

    primary key (flow_access_token_id)
);