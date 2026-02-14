DROP DATABASE IF EXISTS cadastro;

CREATE DATABASE cadastro;
USE cadastro;

CREATE TABLE frota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    placa VARCHAR(50) NOT NULL,
    ano INT NOT NULL
);

CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL
);

INSERT INTO frota (id, modelo, placa, ano)
VALUES ('1', 'Fox', 'DUO8G93', '2009'), ('2', 'Onix', 'ABC1D23', '2022');

INSERT INTO funcionario (id, nome, cargo, salario)
VALUES ('1', 'Ana Costa', 'Gerente', 4500.00), ('2', 'Carlos Lima', 'Motorista', 2500.50);

SELECT * FROM frota;
SELECT * FROM funcionario;