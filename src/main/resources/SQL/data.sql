drop table if exists grocery_item cascade
drop table if exists order_table cascade
drop sequence if exists hibernate_sequence

create table grocery_item (id int8 not null, name varchar(255), price float8 not null, quantity int4 not null, primary key (id))
create table order_table (id int8 not null, item_id int8, quantity int4 not null, user_id int8, primary key (id))
alter table grocery_item add constraint UK_21fq12g0kj9ex9o7vsi34fdbj unique (name)


INSERT INTO grocery_item (id, name, price, quantity) VALUES (1, 'Bananas', 0.5, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (2, 'Milk (1 gallon)', 2.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (3, 'Eggs (dozen)', 1.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (4, 'Bread (loaf)', 2.49, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (5, 'Chicken Breast (per pound)', 3.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (6, 'Rice (1 lb)', 1.25, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (7, 'Tomatoes (per pound)', 1.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (8, 'Potatoes (per pound)', 0.79, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (9, 'Spinach (per bunch)', 1.49, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (10, 'Apples (per pound)', 1.75, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (11, 'Cheese (8 oz)', 3.49, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (12, 'Pasta (1 lb)', 0.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (13, 'Olive Oil (16 oz)', 5.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (14, 'Ground Beef (per pound)', 4.49, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (15, 'Oranges (per pound)', 1.29, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (16, 'Onions (per pound)', 0.69, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (17, 'Carrots (per pound)', 0.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (18, 'Cereal (box)', 3.29, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (19, 'Salmon Fillet (per pound)', 7.99, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (20, 'Broccoli (per bunch)', 1.79, 100);
INSERT INTO grocery_item (id, name, price, quantity) VALUES (21, 'Orange Juice (64 oz)', 2.79, 100);
