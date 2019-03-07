package br.com.alura.estoque.retrofit;

import retrofit2.Retrofit;

public class EstoqueRetrofit {

    public EstoqueRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.20.249:8080/")
                .build();
    }
}
