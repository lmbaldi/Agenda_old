package com.example.agenda.DAO;

import androidx.annotation.Nullable;

import com.example.agenda.modelo.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorId);
        alunos.add(aluno);
        atualizarId();
    }

    private void atualizarId() {
        contadorId++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = buscarAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscarAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoParaRemover = buscarAlunoPeloId(aluno);
        if(alunoParaRemover != null){
            alunos.remove(alunoParaRemover);
        }
    }
}
