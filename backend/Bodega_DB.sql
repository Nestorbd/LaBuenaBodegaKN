drop database if exists Cellar_DB;
create database Cellar_DB;
use Cellar_DB;


drop table if exists wines;
create table wines (
id bigint primary key auto_increment,
name varchar(40) not null,
type varchar(40) not null,
description varchar(500) ,
price float,
quantity int 

);


insert into wines (name, type, description, price, quantity)
values ("Gran Selección GRAN RESERVA MASET DEL LLEÓ", "Reserva", "Magnífico vino tinto procedente de la excepcional cosecha 2015 de nuestros viñedos de cabernet sauvignon que nos proporcionaron unos vinos intensos y muy expresivos. Después de una crianza de 24 meses en barrica de roble francés hemos seleccionado, una a una, solo las mejores, obteniendo 112 barricas de extraordinaria calidad.",7.15,50);

insert into wines (name, type, description, price, quantity)
values ("1777 MASET DEL LLEÓ", "Reserva", "Como homenaje a los fundadores de la dinastía Massana en 1777, hemos aplicado las técnicas más tradicionales a nuestra mejor uva tempranillo. Todo un legado de elaboración que convierte a este vino en nuestra insignia.",19.90,100);

