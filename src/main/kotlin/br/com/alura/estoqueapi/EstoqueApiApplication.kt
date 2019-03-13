package br.com.alura.estoqueapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EstoqueApiApplication

fun main(args: Array<String>) {
	runApplication<EstoqueApiApplication>(*args)
}
