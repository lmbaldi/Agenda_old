package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.modelo.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    public BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    protected void vincularAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }

}
