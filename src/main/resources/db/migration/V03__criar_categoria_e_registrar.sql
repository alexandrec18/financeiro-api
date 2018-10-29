CREATE TABLE categoria(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    codigo_empresa BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome, codigo_empresa) VALUES ('Laser', 1);
INSERT INTO categoria (nome, codigo_empresa) VALUES ('Alimentação', 1);
INSERT INTO categoria (nome, codigo_empresa) VALUES ('Supermercado', 1);
INSERT INTO categoria (nome, codigo_empresa) VALUES ('Farmácia', 1);
INSERT INTO categoria (nome, codigo_empresa) VALUES ('Outros', 1);