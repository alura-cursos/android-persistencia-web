package br.com.alura.estoque.ui.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;

import androidx.appcompat.app.AlertDialog;
import br.com.alura.estoque.R;
import br.com.alura.estoque.model.Produto;

abstract public class FormularioProdutoDialog {

    private final String titulo;
    private final String tituloBotaoPositivo;
    private final ConfirmacaoListener listener;
    private final Context context;
    private static final String TITULO_BOTAO_NEGATIVO = "Cancelar";
    private Produto produto;

    FormularioProdutoDialog(Context context,
                            String titulo,
                            String tituloBotaoPositivo,
                            ConfirmacaoListener listener) {
        this.titulo = titulo;
        this.tituloBotaoPositivo = tituloBotaoPositivo;
        this.listener = listener;
        this.context = context;
    }

    FormularioProdutoDialog(Context context,
                            String titulo,
                            String tituloBotaoPositivo,
                            ConfirmacaoListener listener,
                            Produto produto) {
        this(context, titulo, tituloBotaoPositivo, listener);
        this.produto = produto;
    }

    public void mostra() {
        @SuppressLint("InflateParams") View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.formulario_produto, null);
        tentaPreencherFormulario(viewCriada);
        new AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewCriada)
                .setPositiveButton(tituloBotaoPositivo, (dialog, which) -> {
                    EditText campoNome = getEditText(viewCriada, R.id.formulario_produto_nome);
                    EditText campoPreco = getEditText(viewCriada, R.id.formulario_produto_preco);
                    EditText campoQuantidade = getEditText(viewCriada, R.id.formulario_produto_quantidade);
                    criaProduto(campoNome, campoPreco, campoQuantidade);
                })
                .setNegativeButton(TITULO_BOTAO_NEGATIVO, null)
                .show();
    }

    @SuppressLint("SetTextI18n")
    private void tentaPreencherFormulario(View viewCriada) {
        if (produto != null) {
            TextView campoId = viewCriada.findViewById(R.id.formulario_produto_id);
            campoId.setText(String.valueOf(produto.getId()));
            campoId.setVisibility(View.VISIBLE);
            EditText campoNome = getEditText(viewCriada, R.id.formulario_produto_nome);
            campoNome.setText(produto.getNome());
            EditText campoPreco = getEditText(viewCriada, R.id.formulario_produto_preco);
            campoPreco.setText(produto.getPreco().toString());
            EditText campoQuantidade = getEditText(viewCriada, R.id.formulario_produto_quantidade);
            campoQuantidade.setText(String.valueOf(produto.getQuantidade()));
        }
    }

    private void criaProduto(EditText campoNome, EditText campoPreco, EditText campoQuantidade) {
        String nome = campoNome.getText().toString();
        BigDecimal preco = tentaConverterPreco(campoPreco);
        int quantidade = tentaConverterQuantidade(campoQuantidade);
        long id = preencheId();
        Produto produto = new Produto(id, nome, preco, quantidade);
        listener.quandoConfirmado(produto);
    }

    private long preencheId() {
        if (produto != null) {
            return produto.getId();
        }
        return 0;
    }

    private BigDecimal tentaConverterPreco(EditText campoPreco) {
        try {
            return new BigDecimal(campoPreco.getText().toString());
        } catch (NumberFormatException ignored) {
            return BigDecimal.ZERO;
        }
    }

    private int tentaConverterQuantidade(EditText campoQuantidade) {
        try {
            return Integer.valueOf(
                    campoQuantidade.getText().toString());
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private EditText getEditText(View viewCriada, int idTextInputLayout) {
        TextInputLayout textInputLayout = viewCriada.findViewById(idTextInputLayout);
        return textInputLayout.getEditText();
    }

    public interface ConfirmacaoListener {
        void quandoConfirmado(Produto produto);
    }


}
