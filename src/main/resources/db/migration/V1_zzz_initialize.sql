create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);




insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('DELETE_USERS_PERMISSION');

insert into users (username, password, email)
values
('Bob Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
('John Johnson', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com'),
('admin', '$2y$12$2PUgtsFnfp3Ok6fYlIcmluoIsFrApKjNhPGjuE9kmAz6N5w/DDBei', 'admin@');

insert into users_roles (user_id, role_id) values (1, 1), (1, 3), (3, 2);

create table books (
  id bigserial primary key,
  title varchar(255),
  description varchar(5000),
  price numeric(8, 2),
  publish_year int,
  genre varchar(255)
);
insert into books (title, description, price, publish_year, genre) values
('Harry Potter 1', 'Description 1', 300.0, 2000, 'FANTASY'),
('Harry Potter 2', 'Description 2', 400.0, 2001, 'FANTASY'),
('Harry Potter 3', 'Description 3', 500.0, 2002, 'DETECTIVE'),
('Harry Potter 4', 'Description 4', 700.0, 2007, 'DETECTIVE'),
('Harry Potter 5', 'Description 5', 440.0, 2004, 'FICTION'),
('Harry Potter 6', 'Description 6', 650.0, 2007, 'FICTION'),
('Harry Potter 7', 'Description 7', 200.0, 2006, 'FICTION'),
('LOTR 1', 'Description 8', 1200.0, 2006, 'FICTION'),
('LOTR 2', 'Description 9', 900.0, 2004, 'FICTION'),
('LOTR 3', 'Description 10', 600.0, 2001, 'FICTION'),
('Hobbit', 'Description 11', 500.0, 2001, 'FICTION');



create table orders (
  id                    bigserial,
  user_id               bigint,
  primary key (id)
--   foreign key (user_id) references users (id)
);

create table orderitems (
  id                    bigserial,
  book_id              bigint,
  count                 int,
  order_id              bigint,
  primary key (id),
  foreign key (order_id) references orders (id)
);

CREATE TABLE orders_orderitems (
  order_id               bigint,
  orderitem_id               bigint,
  primary key (order_id, orderitem_id),
  foreign key (orderitem_id) references orderitems (id),
  foreign key (order_id) references orders (id)
);

-- CREATE TABLE users_orders (
--   user_id               bigint,
--   order_id               bigint,
--   primary key (order_id, user_id),
--   foreign key (order_id) references orders (id),
--   foreign key (user_id) references users (id)
-- );