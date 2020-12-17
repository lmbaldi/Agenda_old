package com.example.agenda.asynctask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.modelo.Aluno;
import com.example.agenda.modelo.Telefone;
import com.example.agenda.modelo.TipoTelefone;

import java.util.List;

public class EditarAlunoTask extends BaseAlunoComTelefoneTask {

    private  final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;

    public EditarAlunoTask(
            AlunoDAO alunoDAO,
            Aluno aluno,
            Telefone telefoneFixo,
            Telefone telefoneCelular,
            TelefoneDAO telefoneDAO,
            List<Telefone> telefonesDoAluno,
            FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.editar(aluno);
        vincularAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualizar(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
