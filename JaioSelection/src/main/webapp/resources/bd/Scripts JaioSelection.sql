select * from usuario;
insert into usuario values(null,'favio.flores.olaza@gmail.com','Favio Flores Olaza',sysdate(),1,'frozen4play',6);

select * from definicion;
insert into definicion values (1,'Estados de usuario');
insert into definicion values (2,'Estados de empresa');
insert into definicion values (3,'Sexo');

select * from elemento;
insert into elemento values (1,'Vigente',0,true,1);
insert into elemento values (2,'Caducado',1,true,1);
insert into elemento values (3,'Registrado',1,true,2);
insert into elemento values (4,'Caducado',1,true,2);
insert into elemento values (5,'Eliminado',1,true,2);
insert into elemento values (6,'Masculino',1,true,3);
insert into elemento values (7,'Femenino',1,true,3);


commit;
select * from empresa ;