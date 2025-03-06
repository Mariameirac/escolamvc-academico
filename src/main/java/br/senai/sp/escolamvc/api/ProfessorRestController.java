package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.model.Professor;
import br.senai.sp.escolamvc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorRestController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/listar")
    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    @PostMapping("/inserir")
    public Professor inserir(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/alterar")
    public Professor alterar(@RequestBody Professor professor) {
       return  professorRepository.save(professor);
    }

    @DeleteMapping("/deletar")
    public void  deletar(@PathVariable Long Id) {
        professorRepository.deleteById(Id);
    }

    // inserir vários
    @PostMapping("/inserirvarios")
    public void InserirVarios(@RequestBody List<Professor> professores) {
        professorRepository.saveAll(professores);
    }

    //Buscar por Id
    @GetMapping("/buscar/{id}")
    public Professor buscarPorId(@PathVariable Long id) {
        return professorRepository.findById(id).get();
    }

    //Buscar por Nome
    @GetMapping("/buscar-por-nome/{nome}")
    public List<Professor> buscarPorNome(@PathVariable String nome) {
        return professorRepository.findProfessorsByNomeContaining(nome);
    }

    //Buscar pelo CPF
    @GetMapping("/buscar-por-cpf/{cpf}")
    public Professor buscarPorCpf(@PathVariable String cpf) {
        return professorRepository.findProfessorByCpf(cpf);
    }
}
