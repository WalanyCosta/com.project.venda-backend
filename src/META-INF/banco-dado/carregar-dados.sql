/*Dados tb_cliente*/

insert into tb_cliente(id,nome,telefone,TIPO_CLIENTE) value(1,'joao','9939393','CLIENTE_FISICO');
insert into tb_cliente(id,nome,telefone,TIPO_CLIENTE) value(2,'bagunla','9393933','CLIENTE_FISICO');

/* Dados tb_funcionario */

insert into tb_funcionario (id,nome,status,email,versao) value(1,'Alvares','ACTIVO','Alvares@gmail.com',0);
insert into tb_funcionario (id,nome,status,email,senha,perfil,versao) value(2,'Eunice','ACTIVO','Eunice@gmail.com','123','ADM',0);
insert into tb_funcionario (id,nome,status,email,senha,versao) value(3,'Victor','ACTIVO','Victor@gmail.com','1235',0);
insert into tb_funcionario (id,nome,status,email,perfil,senha,versao) value(4,'Elsa','ACTIVO','Elsa@gmail.com','FUNCIONARIO_COMUM','',0);

/*  Dados tb_fornecedor */

insert into tb_fornecedor (id,nome,versao) value(1,'teichana',0);
insert into tb_fornecedor (id,nome,versao) value(2,'Tio Lucas',0);
insert into tb_fornecedor (id,nome,versao) value(3,'Lateagon',0);
insert into tb_fornecedor (id,nome,versao) value(4,'angoMart',0);

/*  Dados tb_caixa  */

insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(1,1000.00,'2020-09-19',4);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(2,1000.00,'2020-09-19',3);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(3,1000.00,'2020-09-18',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(4,1000.00,'2020-09-17',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(5,1000.00,'2020-09-21',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(6,1000.00,'2020-09-17',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(7,1000.00,'2020-09-16',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(8,1000.00,'2020-09-15',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(9,1000.00,'2020-09-16',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(10,1000.00,'2020-09-15',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(11,1000.00,'2020-09-15',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(12,1000.00,'2020-09-14',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(13,1000.00,'2020-09-14',2);
insert into tb_caixa (id,saldo_final,data_caixa,funcionario_id) value(14,1000.00,'2020-09-13',2);

/*  Dados tb_categoria*/

insert into tb_categoria (id,categoria) value(1,'Bira');
insert into tb_categoria (id,categoria) value(2,'papuxo');
insert into tb_categoria (id,categoria) value(3,'desporto');

/*  Dados tb_produto*/

insert into tb_produto (id,nome,cod_barra,categoria_id,preco,versao) value(1,'massa-espaguete','574747',2,250.0,0);
insert into tb_produto (id,nome,cod_barra,categoria_id,preco,versao) value(2,'eka','339393',1,150.0,0);
insert into tb_produto (id,nome,cod_barra,categoria_id,preco,versao) value(3,'tigra','223456',1,150.0,0);
insert into tb_produto (id,nome,cod_barra,categoria_id,preco,data_validade,versao) value(4,'himpopo','574753',2,250.0,'2020-08-20',0);

/*  Dados tb_stock*/

insert into tb_stock (id,qtd_minimo,qtd_real,data_stock,produto_id,fornecedor_id) value(1, 30, 100, '2020-08-10',1, 2);
insert into tb_stock (id,qtd_minimo,qtd_real,data_stock,produto_id,fornecedor_id) value(2, 30, 100, '2020-08-10',2, 2);
insert into tb_stock (id,qtd_minimo,qtd_real,data_stock,produto_id,fornecedor_id) value(3, 30, 100,'2020-09-18',3, 4);

/*  Dados tb_historico  */

insert into tb_historico (id,operacao,data_hist,funcionario_id) value(1,'registro novo de cliente','2020-08-20',4);
insert into tb_historico (id,operacao,data_hist,funcionario_id) value(2,'registro novo de cliente','2020-08-20',2);

/*----------------------------------Dados tb_vendas--------------------------------------*/

insert into tb_venda (id,serie,total,valorPago,funcionario_id,cliente_id,data_venda) value(1,'00405050LA',500.00,500.00,3,2,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,funcionario_id,cliente_id,data_venda) value(2,'00355050LA',500.00,500.00,4,1,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,funcionario_id,cliente_id,data_venda) value(3,'00225050LA',500.00,500.00,3,2,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(4,'0sslslslsl0LA',500.00,500.00,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(5,'0357648422LA',500.00,500.00,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(6,'558393010LA',500.00,500.00,'2020-09-24');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(7,'5939301020LA',500.00,500.00,'2020-09-19');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(8,'5831020303LA',500.00,500.00,'2020-09-18');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(9,'2939393910LA',500.00,500.00,'2020-09-17');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(10,'19191919199LA',500.00,500.00,'2020-09-17');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(11,'98523729299LA',500.00,500.00,'2020-09-17');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(12,'92902201010LA',500.00,500.00,'2020-09-16');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(13,'949494922LA',500.00,500.00,'2020-09-15');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(14,'822810101LA',500.00,500.00,'2020-09-15');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(15,'002250LA',500.00,500.00,'2020-09-03');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(16,'00250LA',500.00,500.00,'2020-09-05');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(17,'002250505LA',500.00,500.00,'2020-09-09');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(18,'00225051LA',500.00,500.00,'2020-09-04');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(19,'00225052LA',500.00,500.00,'2020-09-01');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(20,'00225053LA',500.00,500.00,'2020-09-07');
insert into tb_venda (id,serie,total,valorPago,data_venda) value(21,'00225054LA',500.00,500.00,'2020-09-08');

/*  Dados tb_itemVenda*/

insert into tb_itemvenda(id,quant,sub_total,venda_id,produto_id) value(1,2,150,2,4);
insert into tb_itemvenda(id,quant,sub_total,venda_id,produto_id) value(2,1,250,2,2);
insert into tb_itemvenda(id,quant,sub_total) value(3,15,35);