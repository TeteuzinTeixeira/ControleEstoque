package com.teteu.ControleEstoque.service;

import com.teteu.ControleEstoque.dto.IdDto;
import com.teteu.ControleEstoque.dto.ProdutoDtoResponse;
import com.teteu.ControleEstoque.dto.ProdutoDtoSaveRequest;
import com.teteu.ControleEstoque.dto.ProdutoDtoUpdateRequest;
import com.teteu.ControleEstoque.entity.Produto;

import java.util.List;

public interface IProdutoService {
    Boolean validarProduto(ProdutoDtoSaveRequest produto);
    Boolean validarProdutoUpdate(ProdutoDtoUpdateRequest produto);
    Produto salvarProduto(ProdutoDtoSaveRequest produto);
    List<Produto> listarProdutos();
    Produto buscarProduto(String id);
    List<Produto> buscarProdutoPorNome(String nome);
    ProdutoDtoResponse atualizarProduto(ProdutoDtoUpdateRequest produtoAtualizado);
    void deletarProduto(IdDto id);
}
