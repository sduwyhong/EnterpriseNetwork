create table enterprise(
	id int unsigned primary key auto_increment,
	name varchar(20) not null,
	address varchar(100) not null,
	description varchar(500) not null
);

create table employee(
	id varchar(32) primary key,
	worker_no varchar(6) not null,
	password varchar(32) not null,
 	name varchar(5) not null,
	gender char(1) not null,
	age tinyInt unsigned not null,
	email varchar(100) not null,
	enterprise_id int unsigned not null,
	constraint fk_enterprise_id_employee foreign key (enterprise_id) references enterprise(id)
);

create table admin(
	id varchar(32) primary key,
	admin_no varchar(6) not null,
	password varchar(32) not null,
	name varchar(5) not null,
	gender char(1) not null,
	age tinyInt unsigned not null,
	email varchar(100) not null,
	enterprise_id int unsigned not null,
	constraint fk_enterprise_id_admin foreign key (enterprise_id) references enterprise(id)
);

create table product(
	id int unsigned primary key auto_increment,
	name varchar(20) not null,
	description varchar(500) default '',
	enterprise_id int unsigned not null,
	constraint fk_enterprise_id_product foreign key (enterprise_id) references enterprise(id)
);

create table enterprise_relation(
	id int unsigned primary key auto_increment,
	enterprise_id_1 int unsigned not null,
	enterprise_id_2 int unsigned not null,
	supply bit default 0,
	stock bit default 0,
	distribution bit default 0
);

create table colleague_relation(
	id int unsigned primary key auto_increment,
	emp_id_1 varchar(32) not null,
	emp_id_2 varchar(32) not null
);

create table compose_relation(
	id int unsigned primary key auto_increment,
	product_id int unsigned not null,
	composition_id int unsigned not null
);

create table employee_enterprise(
	id int unsigned primary key auto_increment,
	employee_id varchar(32) not null,
	enterprise_id int unsigned not null	
);

create table employee_product(
	id int unsigned primary key auto_increment,
	employee_id varchar(32) not null,
	product_id int unsigned not null
);