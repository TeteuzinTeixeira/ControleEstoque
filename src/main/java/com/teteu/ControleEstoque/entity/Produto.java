package com.teteu.ControleEstoque.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "produtos")
public class Produto {
    @Id
    private String id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
