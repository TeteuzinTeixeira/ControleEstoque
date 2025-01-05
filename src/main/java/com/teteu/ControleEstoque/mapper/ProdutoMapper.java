package com.teteu.ControleEstoque.mapper;

import com.teteu.ControleEstoque.dto.ProdutoDtoSaveRequest;
import com.teteu.ControleEstoque.dto.ProdutoDtoResponse;
import com.teteu.ControleEstoque.dto.ProdutoDtoUpdateRequest;
import com.teteu.ControleEstoque.entity.Produto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProdutoMapper {
    public Produto produtoDtoSaveToEntity(ProdutoDtoSaveRequest dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setQuantidade(dto.getQuantidade());
        produto.setPreco(dto.getPreco());
        produto.setDataCadastro(LocalDateTime.now());
        return produto;
    }

    public ProdutoDtoResponse toDtoUpdateReponse(Produto produto) {
        ProdutoDtoResponse dtoResponse = new ProdutoDtoResponse();
        dtoResponse.setId(produto.getId());
        dtoResponse.setNome(produto.getNome());
        dtoResponse.setQuantidade(produto.getQuantidade());
        dtoResponse.setPreco(produto.getPreco());
        dtoResponse.setDataAtualizacao(produto.getDataAtualizacao());
        dtoResponse.setDataCadastro(produto.getDataCadastro());
        return dtoResponse;
    }
}
