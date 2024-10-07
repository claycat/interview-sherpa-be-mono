CREATE TABLE members (
    member_id binary(16) not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,
    primary key (member_id)
);

CREATE TABLE actions (
    action_id binary(16) not null,
    name ENUM(
        'CREATE_FLOW',
        'EDIT_FLOW',
        'VIEW_FLOW',
        'DELETE_FLOW'
        'CREATE_FLOW_COMMENT'
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
        'ADMIN'
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