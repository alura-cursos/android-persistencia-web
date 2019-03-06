package br.com.alura.estoque.database.converter;

import java.math.BigDecimal;

import androidx.room.TypeConverter;

public class BigDecimalConverter {

    @TypeConverter
    public Double paraDouble(BigDecimal valor) {
        return valor.doubleValue();
    }

    @TypeConverter
    public BigDecimal paraBigDecimal(Double valor) {
        if (valor != null) {
            return new BigDecimal(valor);
        }
        return BigDecimal.ZERO;
    }

}
