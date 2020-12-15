package com.example.agenda.database.dao;

import androidx.room.Dao;

import com.example.agenda.modelo.Telefone;

@Dao
public interface TelefoneDAO {
    Telefone buscaPrimeiroTelefoneDoAluno();
}
