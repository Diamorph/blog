insert into users(id, first_name, last_name, email, age)
values (NEXT VALUE FOR USERS_SEQ, 'Vlad', 'Tymoshenko', 'diamorph1309@gmail.com', 25),
       (NEXT VALUE FOR USERS_SEQ, 'Test', 'User', 'test@gmail.com', 45);
insert into post(id, title, body, user_id)
values (NEXT VALUE FOR POST_SEQ, 'title1', 'body1', 1),
       (NEXT VALUE FOR POST_SEQ, 'title2', 'body2', 51),
       (NEXT VALUE FOR POST_SEQ, 'title3', 'body3', 1);