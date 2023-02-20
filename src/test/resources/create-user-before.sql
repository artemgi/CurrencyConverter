delete from user_role;
delete from user_account;

insert into user_account(id,email,password,username) values
(1, 'testmail@gmail.com', '$2a$08$E2/Q7uVdGOGADUQNffbA3eMtfhyisZBuIjWxs2QxWBe9JEx5eelhG', 'testuser'),
(2, 'testmail2@gmail.com', '$2a$08$E2/Q7uVdGOGADUQNffbA3eMtfhyisZBuIjWxs2QxWBe9JEx5eelhG', 'testuser2');

insert into user_role(user_id, roles) values
(1, 'ROLE_USER'), (1, 'ROLE_ADMIN'),
(2, 'ROLE_ADMIN');