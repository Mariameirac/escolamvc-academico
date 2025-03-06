package br.senai.sp.escolamvc.controller;

import br.senai.sp.escolamvc.model.Professor;
import br.senai.sp.escolamvc.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepository;

    @PostMapping("/buscar")
    public String buscarProfessor(@Param("nome") String nome, Model model) {
        if (nome == null) {
            return "professor/listagem";
        }

        List<Professor> ProfessoresRecuperadosNoBanco = professorRepository.findProfessorsByNomeContaining(nome);
        model.addAttribute("professores", ProfessoresRecuperadosNoBanco);
        return "professor/listagem";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());

        return "professor/inserir";
    }

    @PostMapping("/salvar")
    public String salvarProfessor(@Valid Professor professor, BindingResult result,
                              RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template professor/inserir.html
        if (result.hasErrors()) {
            return "professor/inserir";
        }

        // Salva o professor no banco de dados
        professorRepository.save(professor);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem", "Professor salvo com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/professor/novo";
    }

    /*
     * Método que direciona para templates/professores/listagem.html
     */
    @GetMapping
    public String listagem(Model model) {

        // Busca a lista de professores no banco de dados
        List<Professor> listaProfessor = professorRepository.findAll();

        // Adiciona a lista de professores no objeto model para ser carregado no template
        model.addAttribute("professores", listaProfessor);

        // Retorna o template professor/listagem.html
        return "professor/listagem";
    }

    /*
     * Método para excluir um aluno
     */
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id,
                          RedirectAttributes attributes) {

        // Busca o professor no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o professor do banco de dados
        professorRepository.delete(professor);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Professor excluído com sucesso!");

        // Redireciona para a página de listagem de professore
        return "redirect:/professor";
    }

    /*
     * Método que direciona para templates/professor/alterar.html
     */
    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Professor professor = professorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Adiciona o professor no objeto model para ser carregado no formulário
        model.addAttribute("professor", professor);

        // Retorna o template professor/alterar.html
        return "professor/alterar";
    }

    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable("id") Long id, @Valid Professor professor,
                          BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template professor/alterar.html
        if (result.hasErrors()) {
            return "professor/alterar";
        }

        // Busca o professor no banco de dados
        Professor professorAtualizado = professorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));


        // Seta os dados do professor
        professorAtualizado.setNome(professor.getNome());
        professorAtualizado.setEmail(professor.getEmail());
        professorAtualizado.setCpf(professor.getCpf());
        professorAtualizado.setDataNascimento(professor.getDataNascimento());

        // Salva o professor no banco de dados
        professorRepository.save(professorAtualizado);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Professor atualizado com sucesso!");

        // Redireciona para a página de listagem de professores
        return "redirect:/professor";


    }

}