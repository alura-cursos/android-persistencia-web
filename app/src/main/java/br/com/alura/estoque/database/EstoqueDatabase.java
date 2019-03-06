package br.com.alura.estoque.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import br.com.alura.estoque.database.converter.BigDecimalConverter;
import br.com.alura.estoque.database.dao.ProdutoDAO;
import br.com.alura.estoque.model.Produto;

@Database(entities = {Produto.class}, version = 1, exportSchema = false)
@TypeConverters(value = {BigDecimalConverter.class})
public abstract class EstoqueDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "estoque.db";

    public abstract ProdutoDAO getProdutoDAO();

    public static EstoqueDatabase getInstance(Context context) {
        return Room.databaseBuilder(
                context,
                EstoqueDatabase.class,
                NOME_BANCO_DE_DADOS)
                .build();
    }
}
