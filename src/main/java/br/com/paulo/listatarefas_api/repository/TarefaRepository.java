package br.com.paulo.listatarefas_api.repository;

import br.com.paulo.listatarefas_api.model.Tarefa;
import br.com.paulo.listatarefas_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Você "estende" JpaRepository, dizendo:
// "Quero gerenciar a entidade 'Tarefa', e o ID dela é do tipo 'Long'"
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Pronto. Métodos como save(), findAll(), findById(), deleteById()
    // já existem e funcionam!
    // O Spring cria o SQL automático para isso:
    List<Tarefa> findAllByUsuario(Usuario usuario);
}
