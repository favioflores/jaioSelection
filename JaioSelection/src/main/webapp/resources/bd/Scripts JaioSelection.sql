insert into usuario values(null,'favio.flores.olaza@gmail.com','Favio Flores Olaza',sysdate(),1,'frozen4play',6);

ALTER TABLE usuario ADD CONSTRAINT USUARIO_C_CORREO UNIQUE (CORREO);
CREATE INDEX USUARIO_IDX1_CORREO ON USUARIO (CORREO);


insert into definicion values (1,'Estados de usuario');
insert into definicion values (2,'Estados de empresa');
insert into definicion values (3,'Sexo');
insert into definicion values (4,'Estado area');
insert into definicion values (5,'Estado perfil');

select * from elemento;
insert into elemento values (1,'Vigente',0,true,1);
insert into elemento values (2,'Caducado',1,true,1);
insert into elemento values (3,'Registrado',1,true,2);
insert into elemento values (4,'Caducado',1,true,2);
insert into elemento values (5,'Eliminado',1,true,2);
insert into elemento values (6,'Masculino',1,true,3);
insert into elemento values (7,'Femenino',1,true,3);

insert into elemento values (8,'Registrado',1,true,4);
insert into elemento values (9,'Eliminado',1,true,4);

insert into elemento values (10,'Registrado',1,true,5);
insert into elemento values (11,'Eliminado',1,true,5);

commit;



delete from perfil;
delete from area;
commit;
