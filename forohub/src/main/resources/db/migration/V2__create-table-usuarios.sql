
create table usuarios(

    id bigint not null auto_increment,
    nombre varchar(30) not null unique,
    contraseña varchar(200) not null,

    primary key(id)
);
