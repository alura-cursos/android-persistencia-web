package br.com.alura.estoqueapi.controller

import br.com.alura.estoqueapi.model.Produto
import br.com.alura.estoqueapi.service.ProdutoService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/produto")
class ProdutoController(val produtoService: ProdutoService) {

    @GetMapping
    fun buscaTodos(): ResponseEntity<List<Produto>> {
        return ResponseEntity.ok(produtoService.buscaTodos())
    }

    @PostMapping
    fun salva(@RequestBody produto: Produto): ResponseEntity<Produto> {
        return ResponseEntity.ok(produtoService.salva(produto = produto))
    }

    @PutMapping("/{id}")
    fun atualiza(@PathVariable id: Long, @RequestBody produto: Produto): ResponseEntity<Produto> {
        if (produtoService.temProduto(id)) {
            return ResponseEntity.ok(produtoService.salva(id = id, produto = produto))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun remove(@PathVariable id: Long): ResponseEntity<Any> {
        if (produtoService.temProduto(id)) {
            produtoService.remove(id)
        }
        return ResponseEntity.ok().build()
    }

}