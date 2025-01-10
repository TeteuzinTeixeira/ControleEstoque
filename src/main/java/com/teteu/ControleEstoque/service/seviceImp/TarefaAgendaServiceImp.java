package com.teteu.ControleEstoque.service.seviceImp;

import com.teteu.ControleEstoque.entity.Produto;
import com.teteu.ControleEstoque.service.ITarefaAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaAgendaServiceImp implements ITarefaAgendaService {

    @Autowired
    private final EmailServiceImp emailService;

    @Autowired
    private final ProdutoServiceImp produtoServiceImp;

    public TarefaAgendaServiceImp(EmailServiceImp emailService, ProdutoServiceImp produtoServiceImp) {
        this.emailService = emailService;
        this.produtoServiceImp = produtoServiceImp;
    }
    @Scheduled(cron = "0 59 23 * * *")
    public void executarTarefa() {
        List<Produto> produtos = produtoServiceImp.listarProdutos();
        List<String> produtosComBaixaQuantidade = new ArrayList<>();
        for (Produto produto:produtos) {
            if (produto.getQuantidade() <= 50) {
                produtosComBaixaQuantidade.add(produto.getNome());
            }
        }
        if (!produtosComBaixaQuantidade.isEmpty())
            emailService.enviarEmail("mateus.teixeira.gomes29@gmail.com", "Validação de estoque", "<h1>Produtos com 50 ou menos unidades em estoque:</h1><br/><p> " + produtosComBaixaQuantidade + " <p/>");
    }
}
