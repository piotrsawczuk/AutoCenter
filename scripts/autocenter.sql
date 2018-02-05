DROP DATABASE "AutoCenter";
CREATE DATABASE "AutoCenter"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Polish_Poland.1250'
    LC_CTYPE = 'Polish_Poland.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

CREATE SEQUENCE app_role_seq;
CREATE TABLE app_role (
  id          BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('app_role_seq'),
    role_name varchar(50),
    constraint unique_role_name unique (role_name)
);
INSERT INTO app_role (role_name) VALUES ('admin');
INSERT INTO app_role (role_name) VALUES ('user');

create sequence app_user_seq;
create table app_user(
    id bigint not null primary key default nextval('app_user_seq'),
    username varchar(50),
    password varchar(255),
    email varchar(50),
    active boolean,
    constraint unique_username unique (username),
    constraint unique_email unique (email)
);

create sequence user_role_seq;
create table user_role(
    id bigint not null primary key default nextval('user_role_seq'),
    user_id bigint,
    role_id bigint,
    constraint fk_user_id foreign key (user_id) references app_user (id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES app_role (id)
);

create sequence driving_type_seq;
create table driving_type(
    id bigint not null primary key default nextval('driving_type_seq'),
    value bigint,
    driving_type varchar(10),
    constraint unique_driving_value unique (value)
);
insert into driving_type (value, driving_type) values (1, 'City');
insert into driving_type (value, driving_type) values (2, 'Highway');
insert into driving_type (value, driving_type) values (3, 'Mixed');

create sequence fuel_type_seq;
create table fuel_type(
    id bigint not null primary key default nextval('fuel_type_seq'),
    value bigint,
    fuel_type varchar(10),
    constraint unique_fuel_value unique (value)
);
insert into fuel_type (value, fuel_type) values (1, 'Petrol');
insert into fuel_type (value, fuel_type) values (2, 'Diesel');
insert into fuel_type (value, fuel_type) values (3, 'LPG');

create sequence user_car_seq;
create table user_car(
    id bigint not null primary key default nextval('user_car_seq'),
    user_id bigint,
    car_api_id bigint,
    constraint fk_user_id foreign key (user_id) references app_user (id)
);

create sequence user_car_detail_seq;
create table user_car_detail(
    id bigint not null primary key default nextval('user_car_detail_seq'),
    user_car_id bigint,
    vin varchar(17),
    license_plate_number varchar(10),
    color varchar(30),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id),
    constraint unique_vin unique (vin),
    constraint unique_license_plate_number unique (license_plate_number)
);

create sequence fuel_economy_seq;
create table fuel_economy(
    id bigint not null primary key default nextval('fuel_economy_seq'),
    user_car_id bigint,
    date date,
    driving_type bigint,
    fuel_type bigint,
    distance numeric(7, 2),
    fuel_used numeric(6, 2),
    consumption numeric(7, 2),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id),
    constraint fk_fuel_type foreign key (fuel_type) references fuel_type (value),
    constraint fk_driving_type foreign key (driving_type) references driving_type (value)
);

create sequence repair_seq;
create table repair(
    id bigint not null primary key default nextval('repair_seq'),
    user_car_id bigint,
    date date,
    mileage bigint,
    description text,
    cost numeric(8,2),
    constraint fk_user_car_id foreign key (user_car_id) references user_car (id)
);