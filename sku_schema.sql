DROP TABLE sku;
CREATE TABLE  sku
   (sku_id INT,
    product_name VARCHAR(25),
    product_size VARCHAR(25),
    product_color VARCHAR(25),
    product_price INT,
    product_quantity INT
   );
   
INSERT INTO sku (sku_id,product_name,product_size,product_color,product_price,product_quantity)
Values(1,'NEW BALANCE Foam X-70','S','Black',1000,200);
INSERT INTO sku (sku_id,product_name,product_size,product_color,product_price,product_quantity)
Values(2,'NEW BALANCE Foam X-70','M','White',1100,200);
INSERT INTO sku (sku_id,product_name,product_size,product_color,product_price,product_quantity)
Values(3,'NEW BALANCE Foam X-70','L','Pink',1200,200);
INSERT INTO sku (sku_id,product_name,product_size,product_color,product_price,product_quantity)
Values(4,'NEW BALANCE Foam X-70','XL','Black',1300,200);

commit;