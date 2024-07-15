
create table topicos(

    id bigint not null auto_increment,
    titulo varchar(60) not null,
    mensaje varchar(200) not null,
    fechaDeCreacion varchar(14) not null,
    status varchar(12) not null,
    autor varchar(15) not null,


    primary key(id)

);
