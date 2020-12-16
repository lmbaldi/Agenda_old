package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.modelo.Telefone;

import java.util.List;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId LIMIT 1" )
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salvar(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId" )
    List<Telefone> buscarTodosTelefonesDoAluno(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualizar(Telefone... telefones);
}
