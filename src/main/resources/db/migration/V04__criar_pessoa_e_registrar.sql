CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	ativo BOOLEAN NOT NULL,
    codigo_cidade BIGINT(20) NOT NULL,
    codigo_empresa BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_cidade) REFERENCES cidade(codigo),
    FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('João Silva', 'Rua do Abacaxi', '10', null, 'Brasil', '38.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Maria Rita', 'Rua do Sabiá', '110', 'Apto 101', 'Colina', '11.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Pedro Santos', 'Rua da Bateria', '23', null, 'Morumbi', '54.212-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Ricardo Pereira', 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Josué Mariano', 'Av Rio Branco', '321', null, 'Jardins', '56.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Pedro Barbosa', 'Av Brasil', '100', null, 'Tubalina', '77.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Henrique Medeiros', 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Carlos Santana', 'Rua da Manga', '433', null, 'Centro', '31.400-121', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Leonardo Oliveira', 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-001', true, 5270, 1);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, ativo, codigo_cidade, codigo_empresa) values ('Isabela Martins', 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-121', true, 5270, 1);

