package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaTodosTelefonesDoAlunoTask;
import com.example.agenda.asynctask.EditarAlunoTask;
import com.example.agenda.asynctask.SalvarAlunoTask;
import com.example.agenda.database.AgendaDataBase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.modelo.Telefone;
import com.example.agenda.modelo.TipoTelefone;

import java.util.List;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITAR_ALUNO = "Editar Aluno";
    private EditText campoNome;
    //    private EditText campoSobreNome;
    private EditText campoTelefoneFixo;
    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private Aluno aluno;
    private AlunoDAO alunoDAO;
    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        AgendaDataBase dataBase = AgendaDataBase.getInstance(this);
        alunoDAO = dataBase.getAlunoDao();
        telefoneDAO = dataBase.getTelefoneDAO();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITAR_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencherCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencherCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        preencherCamposDeTelefone();
    }

    private void preencherCamposDeTelefone() {
        new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, aluno, telefones -> {
            this.telefonesDoAluno = telefones;
            for (Telefone telefone : telefonesDoAluno) {
                if (telefone.getTipo() == TipoTelefone.FIXO) {
                    campoTelefoneFixo.setText(telefone.getNumero());
                } else {
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();
    }

    private void finalizarFormulario() {
        preencherAluno();
        Telefone telefoneFixo = criaTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criaTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);
        if (aluno.temIdValido()) {
            editarAluno(telefoneFixo, telefoneCelular);
        } else {
            salvarAluno(telefoneFixo, telefoneCelular);
        }
    }

    private Telefone criaTelefone(EditText campoTelefoneFixo, TipoTelefone fixo) {
        String numeroFixo = campoTelefoneFixo.getText().toString();
        return new Telefone(numeroFixo, fixo);
    }

    private void salvarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvarAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, this::finish).execute();

    }

    private void editarAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new EditarAlunoTask(alunoDAO, aluno, telefoneFixo, telefoneCelular, telefoneDAO, telefonesDoAluno, this::finish).execute();

    }

    private void preencherAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setEmail(email);
    }
}