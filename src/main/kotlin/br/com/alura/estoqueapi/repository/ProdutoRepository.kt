package br.com.alura.estoqueapi.repository

import br.com.alura.estoqueapi.model.Produto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository : CrudRepository<Produto, Long>