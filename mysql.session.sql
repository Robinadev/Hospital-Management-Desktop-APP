-- create table staff (
--     staffid INT,
--     fname VARCHAR(50),
--     lname VARCHAR(50),
--     position VARCHAR(50),
--     phonenumber VARCHAR(50),
--     email VARCHAR(50)
-- ) ;

-- SELECT * FROM staff;

ALTER TABLE staff
ADD CONSTRAINT pk_your_table PRIMARY KEY (staffid);

-- SELECT * FROM users;