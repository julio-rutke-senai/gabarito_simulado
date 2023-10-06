package br.senai.dswe.gabarito_simulado.controller;

import br.senai.dswe.gabarito_simulado.entity.Pedido;
import br.senai.dswe.gabarito_simulado.entity.Produto;
import br.senai.dswe.gabarito_simulado.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("todos")
    public ResponseEntity<?> buscarPedidos(){
        try {
            List<Pedido> lista =
                    pedidoService.buscarPedidos();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Erro na requisição",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido){
        try{
            pedido = pedidoService.criarPedido(pedido);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPedido(@PathVariable("codigo") Long codigo){
        try {
            Pedido pedido = pedidoService.buscarPedido(codigo);
            return new ResponseEntity(pedido, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("alterar/{codigo}")
    public ResponseEntity<?> alterarPedido(@RequestBody Pedido pedido, @PathVariable("codigo") Long codigo){
        try{
            pedido = pedidoService.alterarPedido(codigo, pedido);
            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("remover/{codigo}")
    public ResponseEntity<?> removerPedido(@PathVariable("codigo") Long codigo){
        try {
            pedidoService.removerPedido(codigo);
            return new ResponseEntity("Pedido Removido com Sucesso", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("addproduto/{codigo}")
    public ResponseEntity<?> adicionarProdutoPedido(@RequestBody List<Produto> produtos, @PathVariable("codigo") Long codigo){
        try{
            pedidoService.inserirProdutosPedido(codigo, produtos);
            return new ResponseEntity<>("Produto(s) adicionados com sucesso!", HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("removeproduto/{codigo}")
    public ResponseEntity<?> removerProdutoPedido(@RequestBody List<Produto> produtos, @PathVariable("codigo") Long codigo){
        try{
            pedidoService.removerProdutosPedido(codigo, produtos);
            return new ResponseEntity<>("Produto(s) removidos com sucesso!", HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }


}
