package br.com.alura.estoque.asynctask;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecutaListener<T> executaListener;
    private final FinalizadaListener<T> finalizadaListener;

    public BaseAsyncTask(ExecutaListener<T> executaListener,
                         FinalizadaListener<T> finalizadaListener) {
        this.executaListener = executaListener;
        this.finalizadaListener = finalizadaListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return executaListener.quandoExecuta();
    }

    @Override
    protected void onPostExecute(T resultado) {
        super.onPostExecute(resultado);
        finalizadaListener.quandoFinalizada(resultado);
    }

    public interface ExecutaListener<T> {
        T quandoExecuta();
    }

    public interface FinalizadaListener<T> {
        void quandoFinalizada(T resultado);
    }

}
