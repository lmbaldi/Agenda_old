package com.example.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.modelo.Telefone;

public class BuscaPrimeiroTelefoneDoAluno extends AsyncTask<Void, Void, Telefone> {

    private final TelefoneDAO dao;
    private final TextView campoTelefone;
    private final int alunoId;

    public BuscaPrimeiroTelefoneDoAluno(TelefoneDAO dao, TextView campoTelefone, int alunoId) {
        this.dao = dao;
        this.campoTelefone = campoTelefone;
        this.alunoId = alunoId;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        campoTelefone.setText(primeiroTelefone.getNumero());
    }
}
