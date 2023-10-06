package br.senai.dswe.gabarito_simulado.controller;

import br.senai.dswe.gabarito_simulado.entity.Produto;
import br.senai.dswe.gabarito_simulado.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("todos")
    public ResponseEntity<?> buscarProdutos(){
        try {
            List<Produto> lista =
                    produtoService.buscarProdutos();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Erro na requisição",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto){
        try{
            produto = produtoService.criarProduto(produto);
            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarProduto(@PathVariable("codigo") Long codigo){
        try {
            Produto produto = produtoService.buscarProduto(codigo);
            return new ResponseEntity(produto, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("alterar/{codigo}")
    public ResponseEntity<?> alterarProduto(@RequestBody Produto produto, @PathVariable("codigo") Long codigo){
        try{
            produto = produtoService.alterarProduto(codigo, produto);
            return new ResponseEntity<>(produto, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("remover/{codigo}")
    public ResponseEntity<?> removerProduto(@PathVariable("codigo") Long codigo){
        try {
            produtoService.removerProduto(codigo);
            return new ResponseEntity("Produto Removido com Sucesso", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
