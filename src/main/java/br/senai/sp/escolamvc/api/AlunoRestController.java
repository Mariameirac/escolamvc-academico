package br.senai.sp.escolamvc.api;

import br.senai.sp.escolamvc.model.Aluno;
import br.senai.sp.escolamvc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoRestController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/listar")
    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @PostMapping("/inserir")
    public Aluno inserir(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/alterar")
    public Aluno alterar(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/deletar")
    public void deletar(@PathVariable Long Id) {
        alunoRepository.deleteById(Id);
    }

// inserir vários
    @PostMapping("/inserirvarios")
    public void InserirVarios(@RequestBody List<Aluno> alunos) {
        alunoRepository.saveAll(alunos);
    }

//Buscar por Id
    @GetMapping("/buscar/{id}")
    public Aluno buscarPorId(@PathVariable Long id) {
        return alunoRepository.findById(id).get();
    }

//Buscar por Nome
    @GetMapping("/buscar-por-nome/{nome}")
    public List<Aluno> buscarPorNome(@PathVariable String nome) {
        return alunoRepository.findAlunosByNomeContaining(nome);
    }

//Buscar pelo CPF
    @GetMapping("/buscar-por-cpf/{cpf}")
    public Aluno buscarPorCpf(@PathVariable String cpf) {
        return alunoRepository.findAlunoByCpf(cpf);
    }//{
       // return alunoRepository.findAlunosByNomeContainingOrCpfContaining(nome, cpf);
    //  }
}
