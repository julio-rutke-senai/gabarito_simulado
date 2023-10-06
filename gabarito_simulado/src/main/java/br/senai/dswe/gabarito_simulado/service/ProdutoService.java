package br.senai.dswe.gabarito_simulado.service;

import br.senai.dswe.gabarito_simulado.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos;

    public ProdutoService(){
        produtos = new ArrayList<>();
    }

    public List<Produto> buscarProdutos(){
        return produtos;
    }

    public Produto criarProduto(Produto produto) throws Exception {
        produtos.add(produto);
        return produto;
    }

    public Produto buscarProduto(Long codigo) throws Exception {
        Optional<Produto> produto = produtos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(produto.isPresent()){
            return produto.get();
        } else {
            throw new Exception("Produto não encontrado!");
        }
    }

    public Produto alterarProduto(Long codigo, Produto produto) throws Exception {
        Optional<Produto> produtoDaBase = produtos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(produtoDaBase.isPresent()){
            produtoDaBase.get().setNome(produto.getNome());
            produtoDaBase.get().setPreco(produto.getPreco());
            return produto;
        } else {
            throw new Exception("Produto não encontrado!");
        }
    }

    public void removerProduto(Long codigo) {
        produtos.removeIf(produto -> produto.getCodigo() == codigo);
    }
}
