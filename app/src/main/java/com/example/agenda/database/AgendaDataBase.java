package com.example.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.modelo.Aluno;

import static com.example.agenda.database.AgendaMigrations.ALL_MIGRATIONS;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    //metodo que devolve uma instancia do RoomAlunoDao
    public abstract RoomAlunoDao getRoomAlunoDao();

    public static AgendaDataBase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(ALL_MIGRATIONS
                ).build();
    }

}

















