package com.eryck.estoque.model;

public class Produto {


    public Produto(int idproduto, String nome, double valor, int quantidade) {
        this.idproduto = idproduto;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    private int idproduto;
    private String nome;
    private double valor;
    private int quantidade;


    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
