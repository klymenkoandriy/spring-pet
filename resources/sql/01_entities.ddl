CREATE TABLE petdata.material_request (
  id                BIGINT      NOT NULL AUTO_INCREMENT,
  request_Number    INT         NOT NULL,
  customer_Name     VARCHAR(63) NOT NULL,
  priority          INT         NOT NULL,
  invoice           VARCHAR(63) NOT NULL,
  PRIMARY KEY (id)
);
