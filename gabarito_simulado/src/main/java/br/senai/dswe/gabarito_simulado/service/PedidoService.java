package br.senai.dswe.gabarito_simulado.service;

import br.senai.dswe.gabarito_simulado.entity.Pedido;
import br.senai.dswe.gabarito_simulado.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private List<Pedido> pedidos;

    public PedidoService(){
        pedidos = new ArrayList<>();
    }

    public List<Pedido> buscarPedidos(){
        return pedidos;
    }

    public Pedido criarPedido(Pedido pedido) throws Exception {
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido buscarPedido(Long codigo) throws Exception {
        Optional<Pedido> pedido = pedidos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(pedido.isPresent()){
            return pedido.get();
        } else {
            throw new Exception("Pedido não encontrado!");
        }
    }

    public Pedido alterarPedido(Long codigo, Pedido pedido) throws Exception {
        Optional<Pedido> pedidoDaBase = pedidos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(pedidoDaBase.isPresent()){
            pedidoDaBase.get().setProdutos(pedido.getProdutos());
            pedidoDaBase.get().setQuantidade(pedido.getQuantidade());
            pedidoDaBase.get().setTotal(pedido.getTotal());
            return pedido;
        } else {
            throw new Exception("Pedido não encontrado!");
        }
    }

    public void inserirProdutosPedido(Long codigo, List<Produto> produtos) throws Exception {
        Optional<Pedido> pedidoDaBase = pedidos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(pedidoDaBase.isPresent()){
            pedidoDaBase.get().addProduto(produtos);
        } else {
            throw new Exception("Pedido não encontrado!");
        }
    }

    public void removerProdutosPedido(Long codigo, List<Produto> produtos) throws Exception {
        Optional<Pedido> pedidoDaBase = pedidos.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(pedidoDaBase.isPresent()){
            produtos.stream().forEach(produto -> {
                pedidoDaBase.get().getProdutos().removeIf(p -> p.getCodigo() == produto.getCodigo());
            });
        } else {
            throw new Exception("Pedido não encontrado!");
        }
    }

    public void removerPedido(Long codigo) {
        pedidos.removeIf(pedido -> pedido.getCodigo() == codigo);
    }
    
}
