ALTER TABLE pessoa ADD observacao TEXT;
ALTER TABLE pessoa ADD email VARCHAR(100);
ALTER TABLE pessoa ADD tipo CHAR(1);
ALTER TABLE pessoa ADD data_cadastro DATE;
ALTER TABLE pessoa ADD usuario_cadastro BIGINT(20);
ALTER TABLE pessoa ADD inscricao_municipal VARCHAR(50);

ALTER TABLE pessoa ADD data_nascimento DATE;
ALTER TABLE pessoa ADD sexo CHAR(1);
ALTER TABLE pessoa ADD cpf VARCHAR(50);
ALTER TABLE pessoa ADD rg VARCHAR(50);
ALTER TABLE pessoa ADD numero_passaporte VARCHAR(50);
ALTER TABLE pessoa ADD validade_passaporte DATE;
ALTER TABLE pessoa ADD telefone_residencial VARCHAR(50);
ALTER TABLE pessoa ADD telefone_celular VARCHAR(50);
ALTER TABLE pessoa ADD telefone_comercial VARCHAR(50);

ALTER TABLE pessoa ADD razao_social VARCHAR(100);
ALTER TABLE pessoa ADD cnpj VARCHAR(50);
ALTER TABLE pessoa ADD inscricao_estadual VARCHAR(50);
ALTER TABLE pessoa ADD website VARCHAR(100);
ALTER TABLE pessoa ADD telefone VARCHAR(50);