package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.modelo.Aluno;

import java.util.List;

@Dao
public interface RoomAlunoDao {

    @Insert
    void salvar(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> listar();

    @Delete
    void remover(Aluno aluno);

    @Update
    void editar(Aluno aluno);
}
