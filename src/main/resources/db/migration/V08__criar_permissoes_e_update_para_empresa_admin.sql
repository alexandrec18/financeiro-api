INSERT INTO permissao (codigo, descricao) values (15, 'ROLE_PESQUISAR_PERMISSAO');
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1, 15);


UPDATE financeiroapi.categoria   
   SET codigo_empresa = 1 
 WHERE codigo_empresa IS NULL; 

UPDATE financeiroapi.lancamento   
   SET codigo_empresa = 1
 WHERE codigo_empresa IS NULL;

UPDATE financeiroapi.pessoa
   SET codigo_empresa = 1
 WHERE codigo_empresa IS NULL;

UPDATE financeiroapi.usuario
   SET codigo_empresa = 1
 WHERE codigo_empresa IS NULL; 
