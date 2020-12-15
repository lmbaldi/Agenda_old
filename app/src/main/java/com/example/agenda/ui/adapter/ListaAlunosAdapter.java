package com.example.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.database.AgendaDataBase;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.modelo.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return alunos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criarView(viewGroup);
        Aluno alunoDevolvido = alunos.get(posicao);
        vincularInformacoes(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincularInformacoes(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
//        nome.setText(aluno.getNome());
        nome.setText(aluno.getNome() + " " + aluno.dataFormatada());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        TelefoneDAO dao = AgendaDataBase.getInstance(context).getTelefoneDAO();
        Telefone primeiroTelefone = dao.buscaPrimeiroTelefoneDoAluno();
        telefone.setText(primeiroTelefone.getNumero());
    }

    private View criarView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualizar(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remover(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
