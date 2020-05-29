use MUNKEY;

INSERT into usuario VALUES ('froga', 'froga', 'froga@gmail.com', 'usuario normal', 'foto.png', 'froga');
INSERT into usuario VALUES ('frogo', 'frogo', 'frogo@gmail.com', 'usuario verificado', 'fotoo.png', 'frogo');
INSERT into usuario VALUES ('froge', 'froge', 'froge@gmail.com', 'usuario normal', 'fotooo.png', 'froge');
INSERT into usuario VALUES ('frogi', 'frogi', 'frogi@gmail.com', 'usuario verificado', 'fotooooo.png', 'frogi');
INSERT into usuario VALUES ('frogu', 'frogu', 'frogu@gmail.com', 'administrador', 'fotoooooo.png', 'frogu');

INSERT INTO obra VALUES (5,'Bad Miguel', '2020-05-17', 'portada.png', 'obra.pdf', 'terror', 'Español','frogi');  

INSERT INTO obra VALUES (1, 'TITULO', '1981-11-17', 'foto.png', 'obra.pdf', 'terror', 'Swahili', 'froga');
INSERT INTO obra VALUES (2, 'El viaje de Daniel', '1981-11-19', 'foto.png',  'obra.pdf','comedia', 'Español', 'froge');
INSERT INTO obra VALUES (3, 'El viaje de Unai', '1981-11-12', 'foto.png',  'obra.pdf','aventuras', 'Euskera', 'frogi');
INSERT INTO obra VALUES (4, 'La increible historia de Pepito', '1981-11-11', 'foto.png', 'obra.pdf','superación', 'Inglés', 'frogu');

INSERT INTO comentario VALUES ('1981-11-18', '¿Para cuando la segunda parte?', 'frogi', 4);
INSERT INTO meGusta VALUES ('1981-11-18', 'frogi', 4);

INSERT INTO conversacion VALUES (1, 'frogu', 'frogo');
INSERT INTO mensaje VALUES('1981-11-17 20:35:12', 'frogo', 1, 'Me ha gustado mucho tu historia. ¡Que grande Pepito!');
INSERT INTO mensaje VALUES('1981-11-17 20:47:53', 'frogu', 1, 'Muchas gracias amigo.');
