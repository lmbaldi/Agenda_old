package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

import java.util.List;

public class BuscarAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscarAlunosTask(AlunoDAO dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.listar();
    }

    @Override
    protected void onPostExecute(List<Aluno> listaDeAlunos) {
        super.onPostExecute(listaDeAlunos);
        adapter.atualizar(listaDeAlunos);
    }
}
