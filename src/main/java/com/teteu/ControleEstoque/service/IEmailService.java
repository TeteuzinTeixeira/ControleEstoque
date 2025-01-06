package com.teteu.ControleEstoque.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface IEmailService {

    void enviarEmail(String para, String assunto, String conteudo);
}
