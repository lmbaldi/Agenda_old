package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.modelo.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criarAlunosDeTeste();
    }

    private void criarAlunosDeTeste() {
        //setado para teste
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Bianca", "7777777", "bianca@bianca.com"));
        dao.salva(new Aluno("Caio", "25252525", "caio@caio.com"));
    }
}
