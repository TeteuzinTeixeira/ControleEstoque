package com.teteu.ControleEstoque.service.seviceImp;

import com.teteu.ControleEstoque.dto.IdDto;
import com.teteu.ControleEstoque.dto.ProdutoDtoResponse;
import com.teteu.ControleEstoque.dto.ProdutoDtoSaveRequest;
import com.teteu.ControleEstoque.dto.ProdutoDtoUpdateRequest;
import com.teteu.ControleEstoque.entity.Produto;
import com.teteu.ControleEstoque.mapper.ProdutoMapper;
import com.teteu.ControleEstoque.repository.ProdutoRepository;
import com.teteu.ControleEstoque.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoServiceImp implements IProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper mapper;

    @Override
    public Produto salvarProduto(ProdutoDtoSaveRequest dto) {
        try {
            if (!validarProduto(dto))
                throw new RuntimeException("Preencha todos os campos");
            Produto produto = mapper.produtoDtoSaveToEntity(dto);
            Produto produtoValidado = validaProdutoIgual(dto);
            if (produtoValidado == null)
                return produtoRepository.save(produto);
            produtoValidado.setQuantidade(produtoValidado.getQuantidade() + dto.getQuantidade());
            produtoValidado.setDataAtualizacao(LocalDateTime.now());
            return produtoRepository.save(produtoValidado);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarProduto(String id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Produto> buscarProdutoPorNome(String nome) {
        try {
            return produtoRepository.findByNomeContainingIgnoreCase(nome);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ProdutoDtoResponse atualizarProduto(ProdutoDtoUpdateRequest produtoAtualizado) {
        try {
            if (!validarProdutoUpdate(produtoAtualizado))
                throw new RuntimeException("Preencha todos os campos");
            Optional<Produto> optionalProduto = produtoRepository.findById(produtoAtualizado.getId());
            if (optionalProduto.isPresent()) {
                Produto produtoDb = optionalProduto.get();
                produtoDb = atualizarProdutoMapper(produtoAtualizado, produtoDb);
                produtoRepository.save(produtoDb);
                return mapper.toDtoUpdateReponse(produtoDb);
            } else {
                throw new RuntimeException("Produto não encontrado");
            }
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deletarProduto(IdDto id) {
        Optional<Produto> produto = produtoRepository.findById(id.getId());
        if (produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id.getId());
    }

    @Override
    public Boolean validarProduto(ProdutoDtoSaveRequest produto) {
        if (produto.getNome() == null || produto.getNome().isBlank() || produto.getNome().isEmpty())
            return false;
        if (produto.getQuantidade() < 0)
            return false;
        if (produto.getPreco() < 0)
            return false;
        return true;
    }

    @Override
    public Boolean validarProdutoUpdate(ProdutoDtoUpdateRequest produto) {
        if (produto.getNome() == null || produto.getNome().isBlank() || produto.getNome().isEmpty())
            return false;
        if (produto.getQuantidade() < 0)
            return false;
        if (produto.getPreco() < 0)
            return false;
        return true;
    }

    private Produto atualizarProdutoMapper(ProdutoDtoUpdateRequest produtoAtualizado, Produto produtoDb) {
        produtoDb.setQuantidade(produtoAtualizado.getQuantidade());
        produtoDb.setNome(produtoAtualizado.getNome());
        produtoDb.setPreco(produtoAtualizado.getPreco());
        produtoDb.setDataAtualizacao(LocalDateTime.now());
        return produtoDb;
    }

    private Produto validaProdutoIgual(ProdutoDtoSaveRequest produto) {
        List<Produto> listProdutos = produtoRepository.findByNomeIgnoreCase(produto.getNome());
        for (Produto produtoUnico:listProdutos) {
            if (produtoUnico.getPreco() == produto.getPreco())
                return produtoUnico;
        }
        return null;
    }
}
