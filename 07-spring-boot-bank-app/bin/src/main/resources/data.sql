insert into account_types(id, name) values (1, 'SAVINGS');
insert into account_types(id, name) values (2, 'CHECKING');

insert into specialties (id, name) values (1, 'Front Desk');
insert into specialties (id, name) values (2, 'Relations');
insert into specialties (id, name) values (3, 'Teller');

insert into bankers (id, first_name, last_name) values (1, 'John', 'Leva');
insert into bankers (id, first_name, last_name) values (2, 'Kathy', 'Sierra');
insert into bankers (id, first_name, last_name) values (3, 'Larry', 'Nyuwell');

insert into customers (id, first_name, last_name, address, city, telephone) values (1, 'Cust_first_1', 'Cust_last_1', '1 River Rd', 'Edison', 1231231234);
insert into customers (id, first_name, last_name, address, city, telephone) values (2, 'Cust_first_2', 'Cust_last_2', '2 River Rd', 'Edison', 2231231235);
insert into customers (id, first_name, last_name, address, city, telephone) values (3, 'Cust_first_3', 'Cust_last_3', '3 River Rd', 'Edison', 3231231236);

insert into banker_specialties (banker_id, specialty_id) values (1, 1);
insert into banker_specialties (banker_id, specialty_id) values (2, 2);
insert into banker_specialties (banker_id, specialty_id) values (3, 3);

insert into accounts (id, name, opening_date, customer_id, type_id) values(1, 'Savings', sysdate(), 1, 1);
insert into accounts (id, name, opening_date, customer_id, type_id) values(2, 'Checking', sysdate(), 2, 2);
insert into accounts (id, name, opening_date, customer_id, type_id) values(3, 'Checking', sysdate(), 3, 1);

insert into requests (id, description, request_date, account_id) values(1, 'This is first request', sysdate(), 1);
insert into requests (id, description, request_date, account_id) values(2, 'This is Second request', sysdate(), 1);
insert into requests (id, description, request_date, account_id) values(3, 'This is Third request', sysdate(), 1);

