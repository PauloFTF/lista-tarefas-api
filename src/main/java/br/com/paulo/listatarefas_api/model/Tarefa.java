package br.com.paulo.listatarefas_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data; // Se você usou o Lombok

@Data // (Lombok) Cria getters, setters, etc. automaticamente
@Entity // Marca esta classe como uma "tabela" para o JPA
public class Tarefa {

    @Id // Marca como Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para auto-incrementar o ID
    private Long id;

    private String descricao;
    private boolean concluida;

    // NOVO: Relacionamento com o Usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore // Importante: Não envia os dados do usuário de volta no JSON da tarefa
    private Usuario usuario;
}
