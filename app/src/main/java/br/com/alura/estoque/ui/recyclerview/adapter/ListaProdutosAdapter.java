package br.com.alura.estoque.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.alura.estoque.R;
import br.com.alura.estoque.model.Produto;

public class ListaProdutosAdapter extends
        RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder> {

    private final OnItemClickListener onItemClickListener;
    private OnItemClickRemoveContextMenuListener
            onItemClickRemoveContextMenuListener = (posicao, produtoRemovido) -> {
    };
    private final Context context;
    private final List<Produto> produtos = new ArrayList<>();

    public ListaProdutosAdapter(Context context,
                                OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    public void setOnItemClickRemoveContextMenuListener(OnItemClickRemoveContextMenuListener onItemClickRemoveContextMenuListener) {
        this.onItemClickRemoveContextMenuListener = onItemClickRemoveContextMenuListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false);
        return new ViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.vincula(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void atualiza(List<Produto> produtos) {
        this.produtos.clear();
        this.produtos.addAll(produtos);
        this.notifyItemRangeInserted(0, this.produtos.size());
    }

    public void adiciona(Produto... produtos) {
        int tamanhoAtual = this.produtos.size();
        Collections.addAll(this.produtos, produtos);
        int tamanhoNovo = this.produtos.size();
        notifyItemRangeInserted(tamanhoAtual, tamanhoNovo);
    }

    public void edita(int posicao, Produto produto) {
        produtos.set(posicao, produto);
        notifyItemChanged(posicao);
    }

    public void remove(int posicao) {
        produtos.remove(posicao);
        notifyItemRemoved(posicao);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView campoId;
        private final TextView campoNome;
        private final TextView campoPreco;
        private final TextView campoQuantidade;
        private Produto produto;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            campoId = itemView.findViewById(R.id.produto_item_id);
            campoNome = itemView.findViewById(R.id.produto_item_nome);
            campoPreco = itemView.findViewById(R.id.produto_item_preco);
            campoQuantidade = itemView.findViewById(R.id.produto_item_quantidade);
            configuraItemClique(itemView);
            configuraMenuDeContexto(itemView);
        }

        private void configuraMenuDeContexto(@NonNull View itemView) {
            itemView.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
                new MenuInflater(context).inflate(R.menu.lista_produtos_menu, menu);
                menu.findItem(R.id.menu_lista_produtos_remove)
                        .setOnMenuItemClickListener(
                                item -> {
                                    int posicaoProduto = getAdapterPosition();
                                    onItemClickRemoveContextMenuListener
                                            .onItemClick(posicaoProduto, produto);
                                    return true;
                                });
            });
        }

        private void configuraItemClique(@NonNull View itemView) {
            itemView.setOnClickListener(v -> onItemClickListener
                    .onItemClick(getAdapterPosition(), produto));
        }

        void vincula(Produto produto) {
            this.produto = produto;
            campoId.setText(String.valueOf(produto.getId()));
            campoNome.setText(produto.getNome());
            campoPreco.setText(formataParaMoeda(produto.getPreco()));
            campoQuantidade.setText(String.valueOf(produto.getQuantidade()));
        }

        private String formataParaMoeda(BigDecimal valor) {
            NumberFormat formatador = NumberFormat.getCurrencyInstance();
            return formatador.format(valor);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int posicao, Produto produto);
    }

    public interface OnItemClickRemoveContextMenuListener {
        void onItemClick(int posicao, Produto produtoRemovido);
    }

}
