create database calculadora;

use calculadora;

CREATE TABLE operacoes (
	id INT PRIMARY KEY AUTO_INCREMENT,
	num DOUBLE NOT NULL,
	num2 DOUBLE NOT NULL,
	resultado DOUBLE NOT NULL,
	operacao VARCHAR(30)
);