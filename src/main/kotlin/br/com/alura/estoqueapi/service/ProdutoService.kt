package br.com.alura.estoqueapi.service

import br.com.alura.estoqueapi.model.Produto
import br.com.alura.estoqueapi.repository.ProdutoRepository
import org.springframework.stereotype.Service

@Service
class ProdutoService(
        val produtoRepository: ProdutoRepository) {

    fun buscaTodos(): List<Produto> {
        return produtoRepository.findAll().toList()
    }

    fun salva(id: Long = 0, produto: Produto): Produto {
        val produtoComIdAtualizado = produto.copy(id = id)
        return produtoRepository.save(produtoComIdAtualizado)
    }

    fun temProduto(id: Long): Boolean {
        return produtoRepository.existsById(id)
    }

    fun remove(id: Long) {
        produtoRepository.deleteById(id)
    }

}