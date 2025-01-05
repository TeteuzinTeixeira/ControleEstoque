package com.teteu.ControleEstoque.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoDtoResponse {
    private String id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}
