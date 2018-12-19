ALTER TABLE pessoa MODIFY nome VARCHAR(100);
ALTER TABLE pessoa MODIFY logradouro VARCHAR(100);
ALTER TABLE pessoa MODIFY complemento VARCHAR(100);
ALTER TABLE pessoa MODIFY bairro VARCHAR(100);
ALTER TABLE pessoa ADD FOREIGN KEY (usuario_cadastro) REFERENCES usuario(codigo);

ALTER TABLE venda_produto ADD COLUMN retirada VARCHAR(100);
ALTER TABLE venda_produto ADD COLUMN devolucao VARCHAR(100);
ALTER TABLE venda_produto ADD COLUMN categoria VARCHAR(100);
ALTER TABLE venda_produto ADD COLUMN valor_total_original DECIMAL(12,2);
ALTER TABLE venda_produto ADD COLUMN valor_total_original_brl DECIMAL(12,2); 
