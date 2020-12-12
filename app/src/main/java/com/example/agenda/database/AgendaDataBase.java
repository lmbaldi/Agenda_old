package com.example.agenda.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.database.dao.RoomAlunoDao;
import com.example.agenda.modelo.Aluno;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class AgendaDataBase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "agenda.db";

    //devolve uma instancia do RoomAlunoDao
    public abstract RoomAlunoDao getRoomAlunoDao();

    public static AgendaDataBase getInstance(Context context) {
        return Room
                .databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                })
                .build();
    }

}
