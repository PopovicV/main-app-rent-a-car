insert into bundle(id) values (1);

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id, bundle_id)
values (DATE('2020-06-10'), DATE('2020-06-13'), 0, 250, 4, 2, 8, 1);

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id, bundle_id)
values (DATE('2020-06-10'), DATE('2020-06-13'), 0, 250, 4, 2, 7, 1);

insert into request(start_date, end_date, status, total_cost, user_id, owner_id, vehicle_id)
values (DATE('2020-06-12'), DATE('2020-06-20'), 0, 250, 4, 2, 7);
