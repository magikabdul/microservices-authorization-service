CREATE TABLE "users" (
    id serial primary key,
    uuid varchar not null,
    username varchar not null,
    password varchar not null,
    email varchar not null,
    role varchar not null,
    is_locked boolean default true,
    is_enabled boolean default false
);