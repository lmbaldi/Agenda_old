package com.example.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.modelo.TipoTelefone;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //criar uma nova tabela com as informacoes desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`telefone` TEXT, " +
                    "`email` TEXT)");

            //copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno");

            //remover tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT, " +
                    "`telefoneFixo` TEXT, " +
                    "`telefoneCelular` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");

            //copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoDeCadastro)" +
                    " SELECT id, nome, telefone, email, momentoDeCadastro FROM Aluno");

            //remover tabela antiga
            database.execSQL(" DROP TABLE Aluno");

            //renomear a tabela nova com o nome da tabela antiga
            database.execSQL(" ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    public static final Migration MIGRATION_5_6 = new Migration(5,6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)");

            //copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome,  email, momentoDeCadastro)" +
                    " SELECT id, nome, email, momentoDeCadastro FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`alunoId` INTEGER NOT NULL, " +
                    "`numero` TEXT, " +
                    "`tipo` TEXT)");

            //copiar dados(nr de telefone que jah existem) da tabela antiga para a nova
            database.execSQL("INSERT INTO Telefone (numero, alunoId)" +
                    " SELECT telelfoneFixo, id FROM Aluno");

            database.execSQL("UPDATE Telefone SET tipo= ?", new TipoTelefone[] {TipoTelefone.FIXO});

            //remover tabela antiga
            database.execSQL(" DROP TABLE Aluno");

            //renomear a tabela nova com o nome da tabela antiga
            database.execSQL(" ALTER TABLE Aluno_novo RENAME TO Aluno");

        }
    };

    static final Migration[] ALL_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}

