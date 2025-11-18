package br.com.paulo.listatarefas_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Se você usou o Lombok

@Data // (Lombok) Cria getters, setters, etc. automaticamente
@Entity // Marca esta classe como uma "tabela" para o JPA
public class Tarefa {

    @Id // Marca como Chave Primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para auto-incrementar o ID
    private Long id;

    private String descricao;
    private boolean concluida;

    // Se não usar Lombok, crie os getters e setters manualmente
}
