CREATE DATABASE IF NOT EXISTS FitLifeDB DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE FitLifeDB;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    genero ENUM('M', 'F', 'Outro'),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Tabela para histórico de IMC
CREATE TABLE IF NOT EXISTS historico_imc (
    id INT NOT NULL AUTO_INCREMENT,
    usuario_id INT NOT NULL,
    altura DECIMAL(3,2) NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    imc DECIMAL(4,2) NOT NULL,
    classificacao VARCHAR(50),
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
