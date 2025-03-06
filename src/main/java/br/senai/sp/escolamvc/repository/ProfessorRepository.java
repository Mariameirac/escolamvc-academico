package br.senai.sp.escolamvc.repository;
import br.senai.sp.escolamvc.model.Professor;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findProfessorsByNomeContaining(@NotEmpty(message = "O campo nome deve ser preenchido") String nome);

    Professor findProfessorByCpf(String cpf);

    List<Professor> findProfessorsByNomeContainingOrCpfContaining(String nome, String cpf);
}
