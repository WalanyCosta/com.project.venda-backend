create trigger del_fornecedor_bf before delete on tb_fornecedor for each row begin delete from tb_stock where fornecedor_id = old.id;end;
create trigger del_produto_bf before delete on tb_produto for each row begin delete from tb_itemVenda where produto_id = old.id;end;
create trigger del_funcionario_bf before delete on tb_funcionario for each row begin delete from tb_historico where funcionario_id = old.id; delete from tb_caixa where funcionario_id = old.id;delete from tb_venda where funcionario_id = old.id;end;
create trigger del_venda_bf before delete on tb_venda for each row begin delete from tb_itemVenda where venda_id = old.id;end;
create trigger del_cliente_bf before delete on tb_cliente for each row begin delete from tb_Venda where cliente_id = old.id;end;