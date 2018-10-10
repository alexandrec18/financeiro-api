CREATE TABLE empresa(
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO empresa(nome) VALUES ('I.sis');

ALTER TABLE categoria ADD codigo_empresa BIGINT(20);
ALTER TABLE categoria ADD FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo);

ALTER TABLE lancamento ADD codigo_empresa BIGINT(20);
ALTER TABLE lancamento ADD FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo);

ALTER TABLE pessoa ADD codigo_empresa BIGINT(20);
ALTER TABLE pessoa ADD FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo);

ALTER TABLE usuario ADD codigo_empresa BIGINT(20);
ALTER TABLE usuario ADD FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo);