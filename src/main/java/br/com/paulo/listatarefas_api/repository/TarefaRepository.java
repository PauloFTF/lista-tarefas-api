package br.com.paulo.listatarefas_api.repository;

import br.com.paulo.listatarefas_api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

// Você "estende" JpaRepository, dizendo:
// "Quero gerenciar a entidade 'Tarefa', e o ID dela é do tipo 'Long'"
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Pronto. Métodos como save(), findAll(), findById(), deleteById()
    // já existem e funcionam!
}
