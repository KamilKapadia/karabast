drop table users;
drop table authorities;

create table users(
    username varchar(50) not null primary key,
    password varchar(200) not null,
    enabled boolean not null
);

create table authorities (
   username varchar(50) not null,
   authority varchar(50) not null,
   constraint fk_authorities_users foreign key(username) references users(username)
); 

-- P@ssword99!
-- Change this
insert into users values ('admin', '{bcrypt}$2a$10$8CmJk3w/2D40U80niFezqOfSwlsI4CqC8kEZyX8IQ11RLCX/JFjDq', true);
insert into users values ('employee', '{bcrypt}$2a$10$8CmJk3w/2D40U80niFezqOfSwlsI4CqC8kEZyX8IQ11RLCX/JFjDq', true);
insert into users values ('user', '{bcrypt}$2a$10$8CmJk3w/2D40U80niFezqOfSwlsI4CqC8kEZyX8IQ11RLCX/JFjDq', true);

insert into authorities values('admin', 'ROLE_ADMIN');
insert into authorities values('admin', 'ROLE_EMPLOYEE');
insert into authorities values('admin', 'ROLE_USER');

insert into authorities values('employee', 'ROLE_EMPLOYEE');
insert into authorities values('employee', 'ROLE_USER');

insert into authorities values('user', 'ROLE_USER');
