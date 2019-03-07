package br.com.alura.estoque.retrofit;

import br.com.alura.estoque.model.Produto;
import br.com.alura.estoque.retrofit.service.ProdutoService;
import retrofit2.Retrofit;

public class EstoqueRetrofit {

    private final ProdutoService produtoService;

    public EstoqueRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.249:8080/")
                .build();
        produtoService = retrofit.create(ProdutoService.class);
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }
}
