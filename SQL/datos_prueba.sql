use MUNKEY;

INSERT into usuario VALUES ('daniel', 'daniel', 'daniel12@gmail.com', 'normal', 'art/mono.png', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=');
INSERT into usuario VALUES ('Planeta', 'Planeta', 'planeta@gmail.com', 'editorial', 'art/mono.png', 'x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=');


INSERT INTO obra VALUES (1, 'La increible historia de pepito', '2020-06-27', 'La increible historia de pepito.png', 'La increible historia de pepito.pdf', 'Cómic: terror', 'español', 'daniel');

INSERT INTO comentario VALUES ('2020-06-27', '¿Para cuando la segunda parte?', 'Planeta', 1);
INSERT INTO meGusta VALUES ('2020-06-27', 'Planeta', 1);

INSERT INTO conversacion VALUES (1, 'Planeta', 'daniel');
INSERT INTO mensaje VALUES('2020-06-27 20:35:12', 'Planeta', 1, 'Me ha gustado mucho tu historia. ¡Que grande Pepito!');
INSERT INTO mensaje VALUES('2020-06-27 20:47:53', 'daniel', 1, 'Muchas gracias amigo.');
