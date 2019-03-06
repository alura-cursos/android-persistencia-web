package br.com.alura.estoque.ui.dialog;

import android.content.Context;

public class SalvaProdutoDialog extends FormularioProdutoDialog {

    private static final String TITULO = "Salvando produto";
    private static final String TITULO_BOTAO_POSITIVO = "Salvar";

    public SalvaProdutoDialog(Context context,
                              ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener);
    }

}
