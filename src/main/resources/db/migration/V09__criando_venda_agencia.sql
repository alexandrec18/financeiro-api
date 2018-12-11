


CREATE TABLE venda (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  data_venda DATE NOT NULL,
  numero BIGINT(20) NOT NULL,
  situacao VARCHAR(30) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  codigo_vendedor BIGINT(20) NOT NULL,
  codigo_pagante BIGINT(20),
  periodo_inicial DATE,
  periodo_final DATE,
  codigo_intermediario BIGINT(20),
  codigo_solicitante BIGINT(20),
  observacao TEXT,
  data_cadastro DATE,
  usuario_cadastro BIGINT(20),
  total_produtos DECIMAL(12,2),
  total_final DECIMAL(12,2),
  
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo),
  FOREIGN KEY (codigo_vendedor) REFERENCES usuario(codigo),
  FOREIGN KEY (codigo_pagante) REFERENCES pessoa(codigo),
  FOREIGN KEY (codigo_intermediario) REFERENCES pessoa(codigo),
  FOREIGN KEY (codigo_solicitante) REFERENCES pessoa(codigo),
  FOREIGN KEY (usuario_cadastro) REFERENCES usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE moeda (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  sigla VARCHAR(10) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  simbolo VARCHAR(10) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE acomodacao (
  codigo  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tipo_acomodacao (
  codigo  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE regime (
  codigo  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE pacote (
  codigo  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE transporte (
  codigo  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE aeroporto (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  iata VARCHAR(10),
  nome VARCHAR(100),
  cidade VARCHAR(100),
  pais VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE venda_produto( 
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_venda BIGINT(20) NOT NULL,
  produto VARCHAR(70) NOT NULL,
  codigo_fornecedor  BIGINT(20) NOT NULL,
  codigo_representante  BIGINT(20),
  
  data_inicio DATE,
  hora_inicio TIME,
  data_fim DATE,
  hora_fim TIME,
  codigo_acomodacao BIGINT(20),
  codigo_tipo_acomodacao BIGINT(20),
  codigo_regime BIGINT(20),
  codigo_pacote BIGINT(20),
  codigo_transporte BIGINT(20),
  servicos_inclusos TEXT,
  
  documento VARCHAR(50),
  destino VARCHAR(50),

  comissao_porcentagem DECIMAL(10,4),
  comissao_valor DECIMAL(12,2),
  over_porcentagem DECIMAL(10,4),
  over_sobre DECIMAL(10,4),
  over_valor DECIMAL(12,2),
  codigo_moeda BIGINT(20),
  cambio_valor DECIMAL(10,4),
  operadora_abatimentos DECIMAL(12,2),
  operadora_taxa_ccrav DECIMAL(12,2),
  agencia_taxa_serv_destac DECIMAL(12,2),
  agencia_desconto DECIMAL(12,2),
  total_produto DECIMAL(12,2),
  total_produto_brl DECIMAL(12,2),
  valor_total DECIMAL(12,2),
  valor_total_brl DECIMAL(12,2),
  saldo DECIMAL(12,2),
  saldo_brl DECIMAL(12,2),


  numero_nf VARCHAR(50),
  numero_externo VARCHAR(50),
  recibo_operadora VARCHAR(50),
  observacao TEXT,
  FOREIGN KEY (codigo_venda) REFERENCES venda(codigo),
  FOREIGN KEY (codigo_fornecedor) REFERENCES pessoa(codigo),
  FOREIGN KEY (codigo_representante) REFERENCES pessoa(codigo),  
  FOREIGN KEY (codigo_acomodacao) REFERENCES acomodacao(codigo),
  FOREIGN KEY (codigo_tipo_acomodacao) REFERENCES tipo_acomodacao(codigo),
  FOREIGN KEY (codigo_regime) REFERENCES regime(codigo),
  FOREIGN KEY (codigo_pacote) REFERENCES pacote(codigo),
  FOREIGN KEY (codigo_transporte) REFERENCES transporte(codigo),  
  FOREIGN KEY (codigo_moeda) REFERENCES moeda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE venda_produto_passageiro (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_venda_produto BIGINT(20) NOT NULL,
  codigo_passageiro BIGINT(20) NOT NULL,
  nome_emissao VARCHAR(100),
  codigo_moeda_origem  BIGINT(20) NOT NULL,
  cambio_valor DECIMAL(10,4), 
  valor_produto DECIMAL(12,2),
  valor_produto_brl DECIMAL(12,2),
  taxas DECIMAL(12,2),
  taxas_brl DECIMAL(12,2),
  outras_taxas DECIMAL(12,2),
  outras_taxas_brl DECIMAL(12,2),
  taxa_rav DECIMAL(12,2),
  taxa_rav_brl DECIMAL(12,2),
  taxa_du DECIMAL(12,2),
  taxa_du_brl DECIMAL(12,2),
  taxa_servico_oculta DECIMAL(12,2),
  taxa_servico_oculta_brl DECIMAL(12,2),
  valor_total DECIMAL(12,2),
  valor_total_brl DECIMAL(12,2),
  centro_custo VARCHAR(100),
  documento VARCHAR(50),
  FOREIGN KEY (codigo_venda_produto) REFERENCES venda_produto(codigo),
  FOREIGN KEY (codigo_passageiro) REFERENCES pessoa(codigo),
  FOREIGN KEY (codigo_moeda_origem) REFERENCES moeda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE venda_produto_trecho ( 
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_venda_produto BIGINT(20) NOT NULL,
  cia_aerea VARCHAR(50),
  voo VARCHAR(50),
  classe VARCHAR(50),
  codigo_aeroporto_origem BIGINT(20),
  data_saida DATE,
  hora_saida TIME,
  codigo_aeroporto_destino BIGINT(20),
  data_chegada DATE,
  hora_chegada TIME,
  FOREIGN KEY (codigo_venda_produto) REFERENCES venda_produto(codigo),
  FOREIGN KEY (codigo_aeroporto_origem) REFERENCES aeroporto(codigo),
  FOREIGN KEY (codigo_aeroporto_destino) REFERENCES aeroporto(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE venda_forma_pagamento ( 
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_venda BIGINT(20) NOT NULL,
  forma_pagamento VARCHAR(50) NOT NULL,
  parcelas INTEGER,
  autorizacao VARCHAR(50),
  numero VARCHAR(50),
  data DATE,
  banco VARCHAR(50),
  agencia VARCHAR(50),
  conta_corrente VARCHAR(50),
  valor_total DECIMAL(12,2),
  FOREIGN KEY (codigo_venda) REFERENCES venda(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE venda_forma_pagamento_produto ( 
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  codigo_venda_forma_pagamento BIGINT(20) NOT NULL,
  produto VARCHAR(70) NOT NULL,
  valor DECIMAL(12,2),
  FOREIGN KEY (codigo_venda_forma_pagamento) REFERENCES venda_forma_pagamento(codigo)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE venda_numero (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  numero BIGINT(20) NOT NULL,
  codigo_empresa BIGINT(20) NOT NULL,
  FOREIGN KEY (codigo_empresa) REFERENCES empresa(codigo) 
);


INSERT INTO permissao (codigo, descricao) values (17, 'ROLE_PESQUISAR_VENDA');
INSERT INTO permissao (codigo, descricao) values (18, 'ROLE_CADASTRAR_VENDA');
INSERT INTO permissao (codigo, descricao) values (19, 'ROLE_REMOVER_VENDA');
INSERT INTO permissao (codigo, descricao) values (20, 'ROLE_PESQUISAR_MOEDA');
INSERT INTO permissao (codigo, descricao) values (21, 'ROLE_CADASTRAR_MOEDA');
INSERT INTO permissao (codigo, descricao) values (22, 'ROLE_REMOVER_MOEDA');
INSERT INTO permissao (codigo, descricao) values (23, 'ROLE_PESQUISAR_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (24, 'ROLE_CADASTRAR_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (25, 'ROLE_REMOVER_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (26, 'ROLE_PESQUISAR_TIPO_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (27, 'ROLE_CADASTRAR_TIPO_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (28, 'ROLE_REMOVER_TIPO_ACOMODACAO');
INSERT INTO permissao (codigo, descricao) values (29, 'ROLE_PESQUISAR_REGIME');
INSERT INTO permissao (codigo, descricao) values (30, 'ROLE_CADASTRAR_REGIME');
INSERT INTO permissao (codigo, descricao) values (31, 'ROLE_REMOVER_REGIME');
INSERT INTO permissao (codigo, descricao) values (32, 'ROLE_PESQUISAR_PACOTE');
INSERT INTO permissao (codigo, descricao) values (33, 'ROLE_CADASTRAR_PACOTE');
INSERT INTO permissao (codigo, descricao) values (34, 'ROLE_REMOVER_PACOTE');
INSERT INTO permissao (codigo, descricao) values (35, 'ROLE_PESQUISAR_TRANSPORTE');
INSERT INTO permissao (codigo, descricao) values (36, 'ROLE_CADASTRAR_TRANSPORTE');
INSERT INTO permissao (codigo, descricao) values (37, 'ROLE_REMOVER_TRANSPORTE');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 17);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 18);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 19);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 20);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 21);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 22);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 23);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 24);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 25);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 26);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 27);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 28);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 29);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 30);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 31);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 32);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 33);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 34);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 35);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 36);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 37);