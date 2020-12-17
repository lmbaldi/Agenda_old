package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class RemoverAlunoTask  extends AsyncTask<Void, Void, Void> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoverAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remover(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remover(aluno);
    }
}
