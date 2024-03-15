INSERT INTO member ( age, name, iin) VALUES ( 20, 'Berik', '030423601189');
INSERT INTO member ( age, name, iin) VALUES ( 22, 'Nurbakyt', '010623501189');
INSERT INTO member ( age, name, iin) VALUES ( 18, 'Ruslan', '050105651243');
INSERT INTO member ( age, name, iin) VALUES ( 25, 'Yerlan', '990623987654');

INSERT INTO role (name) values ('ROLE_USER'),
                               ('ROLE_ADMIN');

insert into users (role_id, username,
                   password, first_name, last_name, email, personal_id, phone_number)
values ((select id from role where name = 'ROLE_ADMIN' limit 1), 'admin', '$2a$10$QOsLD0BrZb7wxvyXcIwHY.gxugRPBQIsK3pKVCVMshTGnoLHEdf5.', 'YY', 'NN', 'yy@gmail.com', '1', '88');


-- insert into userdemo (fname, lname,
--                       uname, registeredat)
-- VALUES ('Aya', 'Zhalgasova', 'baga', localtimestamp)