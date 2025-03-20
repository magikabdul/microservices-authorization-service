CREATE TABLE "users" (
    id serial primary key,
    uuid varchar not null,
    username varchar not null,
    password varchar not null,
    email varchar not null,
    role varchar not null,
    isLocked boolean default true,
    isEnabled boolean default false
);