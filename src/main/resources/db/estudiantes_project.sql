create database estudiantes_project;

use estudiantes_project;

create table estudiantes(
	id bigint auto_increment not null,
	cedula varchar(10) not null unique,
    nombres varchar(255) not null,
    apellidos varchar(255) not null,
    
    primary key(id)
);

create table ubicaciones(
	id bigint auto_increment not null,
    coordenadas point not null,
    
    primary key(id)
);

create table direcciones(
	id bigint auto_increment not null,
    estudiante_id bigint not null,
    ubicacion_id bigint not null,
    categoria varchar(25) not null,
    
    primary key(id),
    
    constraint fk_direcciones_estudiante_id foreign key(estudiante_id) references estudiantes(id),
    constraint fk_direcciones_ubicacion_id foreign key(ubicacion_id) references ubicaciones(id)
);

alter table direcciones add principal boolean not null default false;

