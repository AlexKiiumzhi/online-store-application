INSERT INTO category (id, name) VALUES
  (1, 'Clothing'),
  (2, 'Electronics'),
  (3, 'Software');

INSERT INTO product (id,name, brand_name, category_id, price) VALUES
  (1, 'Jordan 1 Retro High', 'Nike', 1,'89.99'),
  (2, 'Macbook pro', 'Apple', 2,'1329.99'),
  (3, 'Office 2019', 'Microsoft', 3,'119.00');

INSERT INTO user (id,role, first_name, last_name, email, phone_number, password,created_at, is_Blocked) VALUES
  (1,'ROLE_ADMIN','Josh', 'Clark', 'Josh.Clack@gmail.com','(541) 754-3010','josh-josh',ts '2020-08-24 18:47:52.690', 0),
  (2,'ROLE_USER', 'Mary', 'Smith', 'Mary-Smith@hotmail.com','(792) 148-9634','mary.mary',ts '2020-08-23 18:47:52.690', 0),
  (3,'ROLE_USER', 'Alfonso', 'Hernandez', 'Alfonso-Hernandez@hotmail.com','(369) 871-9357','alfonso--alfonso',ts '2020-08-22 18:47:52.690', 0);



