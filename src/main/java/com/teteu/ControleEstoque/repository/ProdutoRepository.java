package com.teteu.ControleEstoque.repository;

import com.teteu.ControleEstoque.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByNomeIgnoreCase(String nome);
}
