insert into user_details(full_name, address, vehicle_num, user_type) values ('Ivana Brkic', 'Adresa 1', 3, 0);
insert into user_details(full_name, address, business_num, vehicle_num, user_type) values ('Tamara Lazarevic', 'Adresa 2', '12A', 1, 1);
insert into user_details(full_name, address, business_num, vehicle_num, user_type) values ('Vladimir Popovic', 'Adresa 3', '12B', 0, 1);
insert into user_details(full_name, address, vehicle_num, user_type) values ('Milan Lukic', 'Adresa 4', 2, 2);
insert into penalty(id, total, penalty_status,userdetail_id) values (1,100,0,4);

insert into sys_user(username, password, user_details_id, verified) values ('admin', 'admin', 1, true);
insert into sys_user(username, password, user_details_id, verified) values ('agent1', 'agent1', 2, true);
insert into sys_user(username, password, user_details_id, verified) values ('agent2', 'agent2', 3, true);
insert into sys_user(username, password, user_details_id, verified) values ('user', 'user', 4, true);

insert into user_privilege(user_id, privilege) values (1, 0);
insert into user_privilege(user_id, privilege) values (1, 1);
insert into user_privilege(user_id, privilege) values (1, 2);
insert into user_privilege(user_id, privilege) values (1, 3);

insert into user_privilege(user_id, privilege) values (2, 0);
insert into user_privilege(user_id, privilege) values (2, 1);
insert into user_privilege(user_id, privilege) values (2, 2);
insert into user_privilege(user_id, privilege) values (2, 3);

insert into user_privilege(user_id, privilege) values (3, 0);
insert into user_privilege(user_id, privilege) values (3, 1);
insert into user_privilege(user_id, privilege) values (3, 2);
insert into user_privilege(user_id, privilege) values (3, 3);

insert into user_privilege(user_id, privilege) values (4, 0);
insert into user_privilege(user_id, privilege) values (4, 3);


