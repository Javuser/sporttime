INSERT INTO member ( age, name) VALUES ( 20, 'Berik');
INSERT INTO member ( age, name) VALUES ( 22, 'Nurbakyt');
INSERT INTO member ( age, name) VALUES ( 18, 'Ruslan');
INSERT INTO member ( age, name) VALUES ( 25, 'Yerlan');

INSERT INTO role (name) values ('ROLE_USER'),
                               ('ROLE_ADMIN');

insert into users (role_id, username,
                   password, first_name, last_name, email, personal_id, phone_number)
values ((select id from role where name = 'ROLE_ADMIN' limit 1), 'admin', '$2a$10$QOsLD0BrZb7wxvyXcIwHY.gxugRPBQIsK3pKVCVMshTGnoLHEdf5.', 'YY', 'NN', 'yy@gmail.com', '1', '88');


-- insert into userdemo (fname, lname,
--                       uname, registeredat)
-- VALUES ('Aya', 'Zhalgasova', 'baga', localtimestamp)