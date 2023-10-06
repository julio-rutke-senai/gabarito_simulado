package br.senai.dswe.gabarito_simulado.entity;

import java.util.List;

public class Pedido {
    private Long codigo;
    private List<Produto> produtos;
    private float quantidade;
    private float total;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(List<Produto> produtos){
        this.produtos.addAll(produtos);
    }

    public void removeProduto(List<Produto> produtos){
        this.produtos.remove(produtos);
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
