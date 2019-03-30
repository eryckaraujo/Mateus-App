package com.eryck.estoque.DAO;

import com.eryck.estoque.conexao.Conexao;
import com.eryck.estoque.model.Produto;

import java.util.ArrayList;

public class DaoProdutos extends Conexao {
    /**
     * Cadastrar um produto no banco
     *
     * @param pModelProdutos
     * @return
     */
    public int salvarProdutosDAO(Produto pModelProdutos) {
        try {
            this.conectar();
            return this.insertSQL("INSERT INTO tbl_produto ("
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque"
                    + ") VALUES ("
                    + "'" + pModelProdutos.getNome() + "',"
                    + "'" + pModelProdutos.getValor() + "',"
                    + "'" + pModelProdutos.getQuantidade() + "'"
                    + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Excluir um produto do banco
     *
     * @param pIdProduto
     * @return boolean
     */
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_produto WHERE pk_id_produto = '" + pIdProduto + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

    /**
     * Alterar dados do produtos
     *
     * @param pModelProdutos
     * @return boolean
     */
    public boolean alterarProdutoDAO(Produto pModelProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_produto SET "
                            + "pro_nome = '" + pModelProdutos.getNome() + "',"
                            + "pro_valor = '" + pModelProdutos.getValor() + "',"
                            + "pro_estoque = '" + pModelProdutos.getQuantidade() + "'"
                            + " WHERE pk_id_produto = '" + pModelProdutos.getIdproduto() + "'"
            );

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }

    }

    /**
     * retornar um produto pelo codigo
     *
     * @param pIdProduto
     * @return modelproduto
     */
    public Produto retornarProdutoDAO(int pIdProduto) {
        Produto produto = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque "
                    + "FROM tbl_produto WHERE pk_id_produto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                produto.setIdproduto(this.getResultSet().getInt(1));
                produto.setNome(this.getResultSet().getString(2));
                produto.setValor(this.getResultSet().getDouble(3));
                produto.setQuantidade(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return produto;
    }

    /**
     * retornar um produto pelo codigo
     *
     * @param pNomeProduto
     * @return modelproduto
     */
    public Produto retornarProdutoDAO(String pNomeProduto) {
        Produto produto = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque "
                    + "FROM tbl_produto WHERE pro_nome = '" + pNomeProduto + "';");
            while (this.getResultSet().next()) {
                produto.setIdproduto(this.getResultSet().getInt(1));
                produto.setNome(this.getResultSet().getString(2));
                produto.setValor(this.getResultSet().getDouble(3));
                produto.setQuantidade(this.getResultSet().getInt(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return produto;
    }

    /**
     * Retornar uma lista de completa produtos
     *
     * @return listaModelProdutos
     */
    public ArrayList<Produto> retornarListaProdutosDAO() {
        ArrayList<Produto> listaModelProdutos = new ArrayList<>();
        Produto produto = new Produto();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque "
                    + "FROM Produto;");

            while (this.getResultSet().next()) {
                produto = new Produto();
                produto.setIdproduto(this.getResultSet().getInt(1));
                produto.setNome(this.getResultSet().getString(2));
                produto.setValor(this.getResultSet().getDouble(3));
                produto.setQuantidade(this.getResultSet().getInt(4));
                listaModelProdutos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelProdutos;
    }

    /**
     * Alterar estoque de produtos no banco
     * @param pListaModelProdutoses
     * @return
     */
    public boolean alterarEstoqueProdutosDAO(ArrayList<Produto> pListaModelProdutoses) {
        try {
            this.conectar();
            for (int i = 0; i < pListaModelProdutoses.size(); i++) {
                this.executarUpdateDeleteSQL(
                        "UPDATE tbl_produto SET "
                                + "pro_estoque = '" + pListaModelProdutoses.get(i).getQuantidade()+ "'"
                                + " WHERE pk_id_produto = '" + pListaModelProdutoses.get(i).getIdproduto() + "'"
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }

}
