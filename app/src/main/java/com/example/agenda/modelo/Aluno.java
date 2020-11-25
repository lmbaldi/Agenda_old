package com.example.agenda.modelo;

import androidx.annotation.NonNull;

public class Aluno {

    private final String nome;
    private final String telefone;
    private final String email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    @NonNull
    @Override
    public String toString() {
        return nome;
//        return "Aluno{" +
//                "name='" + nome + '\'' +
//                ", telefone='" + telefone + '\'' +
//                ", email='" + email + '\'' +
//                '}';
    }
}
