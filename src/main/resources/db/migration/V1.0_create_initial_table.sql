CREATE TABLE investor (
  investor_id UUID NOT NULL,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   age INTEGER NOT NULL,
   email VARCHAR(255),
   address VARCHAR(255),
   contact_number VARCHAR(255),
   CONSTRAINT pk_investor PRIMARY KEY (investor_id)
);
CREATE TABLE product (
  product_id UUID NOT NULL,
   product_name VARCHAR(255),
   product_type VARCHAR(255),
   current_balance numeric(10, 2),
   withdrawal_balance numeric(10, 2),
   is_active BOOLEAN,
   investor_investor_id UUID,
   CONSTRAINT pk_product PRIMARY KEY (product_id)
);

ALTER TABLE product ADD CONSTRAINT FK_PRODUCT_ON_INVESTOR_INVESTOR FOREIGN KEY (investor_investor_id) REFERENCES investor (investor_id);
CREATE TABLE withdrawal (
  withdrawal_id UUID NOT NULL,
   amount DECIMAL,
   withdrawal_date TIMESTAMP WITHOUT TIME ZONE,
   product_id UUID,
   CONSTRAINT pk_withdrawal PRIMARY KEY (withdrawal_id)
);

ALTER TABLE withdrawal ADD CONSTRAINT FK_WITHDRAWAL_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);