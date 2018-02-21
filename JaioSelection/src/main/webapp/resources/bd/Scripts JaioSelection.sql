insert into usuario values(null,'favio.flores.olaza@gmail.com','Favio Flores Olaza',sysdate(),1,'frozen4play',6);

ALTER TABLE usuario ADD CONSTRAINT USUARIO_C_CORREO UNIQUE (CORREO);
CREATE INDEX USUARIO_IDX1_CORREO ON usuario (CORREO);


insert into definicion values (1,'Estados de usuario');
insert into definicion values (2,'Estados de empresa');
insert into definicion values (3,'Sexo');
insert into definicion values (4,'Estado area');
insert into definicion values (5,'Estado perfil');
insert into definicion values (6,'Estado evaluación');
insert into definicion values (7,'Tipo de pregunta');
insert into definicion values (8,'Tipo de respuesta');
insert into definicion values (9,'Estado del proceso');
insert into definicion values (10,'Tipos de ajustes en evaluación');

select * from elemento;
insert into elemento values (1,'Vigente',0,true,1);
insert into elemento values (2,'Caducado',1,true,1);
insert into elemento values (3,'Registrado',1,true,2);
insert into elemento values (5,'Eliminado',1,true,2);
insert into elemento values (6,'Masculino',1,true,3);
insert into elemento values (7,'Femenino',1,true,3);

insert into elemento values (8,'Registrado',1,true,4);
insert into elemento values (9,'Eliminado',1,true,4);

insert into elemento values (10,'Registrado',1,true,5);
insert into elemento values (11,'Eliminado',1,true,5);

insert into elemento values (12,'Activo',1,true,6);
insert into elemento values (13,'Inactivo',1,true,6);

insert into elemento values (14,'Texto',1,true,7);
insert into elemento values (15,'Gráfico',1,true,7);
insert into elemento values (16,'Numérico',1,true,7);

insert into elemento values (17,'Texto',1,true,8);
insert into elemento values (18,'Gráfico',1,true,8);
insert into elemento values (19,'Numérico',1,true,8);

insert into elemento values (20,'Registrado',1,true,9);
insert into elemento values (21,'Eliminado',1,true,9);

insert into elemento values (22,'Por sexo',1,true,10);
insert into elemento values (23,'Por nivel educativo',1,true,10);
insert into elemento values (24,'Por edad',1,true,10);

commit;







