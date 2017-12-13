insert into usuario values(null,'favio.flores.olaza@gmail.com','Favio Flores Olaza',sysdate(),1,'frozen4play',6);

ALTER TABLE usuario ADD CONSTRAINT USUARIO_C_CORREO UNIQUE (CORREO);
CREATE INDEX USUARIO_IDX1_CORREO ON USUARIO (CORREO);


insert into definicion values (1,'Estados de usuario');
insert into definicion values (2,'Estados de empresa');
insert into definicion values (3,'Sexo');
insert into definicion values (4,'Estado area');
insert into definicion values (5,'Estado perfil');
insert into definicion values (6,'Estado evaluación');
insert into definicion values (7,'Tipo de pregunta');
insert into definicion values (8,'Tipo de respuesta');

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

commit;



delete from perfil;
delete b from area as b 
where b.id in (17);

commit;

select * from area a order by a.area_herarquia_id, a.id;
select * from perfil;
select a.id, a.descripcion, a.area_herarquia_id from area a where a.empresa_id = 1 and a.estado = 8;
select p.id, p.nombre, p.area_id from perfil p where p.empresa_id = 1 and p.estado = 10;


update area set area_herarquia_id = 0 where area_herarquia_id = null and empresa_id = 0;

update perfil set area_id = 0 where id = 0 and empresa_id = 0;

update area set estado = 9 where id = 0 and empresa_id = 0 and empresa_id = 0;



      select * from area;
      select * from perfil;
      commit;
      
insert into modelo_libro values (1,"Ejemplo Libro 1","Favio Flores","Ylene Sanchez","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse lacinia vitae magna quis lacinia. Nullam libero orci, malesuada ut aliquet nec, tempor et tellus. Sed in blandit purus. Donec id lacus pulvinar, rhoncus tortor quis, laoreet ipsum. Pellentesque aliquet orci vitae purus imperdiet hendrerit. Nulla iaculis erat ac orci varius placerat. Curabitur eleifend quis nisl in semper. Proin nec cursus enim. Quisque id libero quis est gravida suscipit id at libero. Etiam non sodales urna. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris ultrices dui ac erat pretium, sit amet congue dolor maximus.");
