create table blog (
    id uuid primary key,
    title varchar(255) not null,
    content text not null,
    created_on timestamp with time zone not null
);

create table users(
    id uuid primary key,
    email varchar(50) not null,
    user_name varchar(25) not null
);

create table comment(
    id uuid primary key,
    blog_id uuid not null,
    user_id uuid not null,
    content text not null,
    created_on timestamp with time zone not null,
    foreign key (blog_id) references blog(id),
    foreign key (user_id) references users(id)
);