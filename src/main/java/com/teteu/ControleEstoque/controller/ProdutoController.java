package com.teteu.ControleEstoque.controller;

import com.teteu.ControleEstoque.dto.IdDto;
import com.teteu.ControleEstoque.dto.ProdutoDtoResponse;
import com.teteu.ControleEstoque.dto.ProdutoDtoSaveRequest;
import com.teteu.ControleEstoque.dto.ProdutoDtoUpdateRequest;
import com.teteu.ControleEstoque.entity.Produto;
import com.teteu.ControleEstoque.service.seviceImp.ProdutoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImp produtoService;

    @PostMapping
    public Produto criarProduto(@RequestBody ProdutoDtoSaveRequest produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable String id) {
        return produtoService.buscarProduto(id);
    }

    @GetMapping("/nome")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarProdutoPorNome(nome);
    }

    @PutMapping
    public ProdutoDtoResponse atualizarProduto(@RequestBody ProdutoDtoUpdateRequest produtoAtualizado) {
        return produtoService.atualizarProduto(produtoAtualizado);
    }

    @DeleteMapping
    public void deletarProduto(@RequestBody IdDto id) {
        produtoService.deletarProduto(id);
    }
}
