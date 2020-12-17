package com.example.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.converter.ConversorTipoTelefone;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.modelo.Telefone;

import static com.example.agenda.database.AgendaMigrations.ALL_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";
    //metodo que devolve uma instancia do RoomAlunoDao
    public abstract AlunoDAO getAlunoDao();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDataBase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)
                .addMigrations(ALL_MIGRATIONS).build();
    }
}

















