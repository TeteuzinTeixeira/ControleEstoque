package com.teteu.ControleEstoque.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoDtoUpdateRequest {
    private String Id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDateTime dataAtualizacao;
}
