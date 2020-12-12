package com.example.agenda.ui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.agenda.database.AgendaDataBase;
import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final RoomAlunoDao dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao =  AgendaDataBase.getInstance(context).getRoomAlunoDao();
    }

    public void atualizarAlunos() {
        adapter.atualizar(dao.listar());
    }

    public void confirmarRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeze que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                    remover(alunoEscolhido);
                })
                .setNegativeButton("Nao", null)
                .show();
    }

    private void remover(Aluno aluno) {
        dao.remover(aluno);
        adapter.remover(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

}
