package br.senai.sp.escolamvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo",
        discriminatorType = DiscriminatorType.STRING
)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome deve ser preenchido")
    private String nome;

    @CPF(message = "CPF inválido")
    @NotEmpty(message = "O campo CPF deve ser preenchido")
    private String cpf;

    @NotEmpty(message = "O campo Email deve ser preenchido")
    @Email(message = "Email inválido")
    private String email;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

}
