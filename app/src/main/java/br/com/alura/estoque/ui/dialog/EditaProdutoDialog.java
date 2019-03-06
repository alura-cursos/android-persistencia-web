package br.com.alura.estoque.ui.dialog;

import android.content.Context;

import br.com.alura.estoque.model.Produto;

public class EditaProdutoDialog extends FormularioProdutoDialog {

    private static final String TITULO = "Editando produto";
    private static final String TITULO_BOTAO_POSITIVO = "Editar";

    public EditaProdutoDialog(Context context,
                              Produto produto,
                              ConfirmacaoListener listener) {
        super(context, TITULO, TITULO_BOTAO_POSITIVO, listener, produto);
    }
}
