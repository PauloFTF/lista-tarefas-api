package br.com.paulo.listatarefas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import br.com.paulo.listatarefas_api.model.Tarefa;
import br.com.paulo.listatarefas_api.repository.TarefaRepository;

@RestController // Diz ao Spring que esta classe define "endpoints" de API REST
@RequestMapping("/api/tarefas") // Todas as rotas aqui dentro começam com /api/tarefas
@CrossOrigin(origins = "http://localhost:4200") // IMPORTANTE! Permite que o Angular (da porta 4200) chame esta API.
public class TarefaController {

    @Autowired // "Injeção de Dependência": O Spring vai te dar (injetar)
    // uma instância pronta do TarefaRepository.
    private TarefaRepository repository;

    // Rota 1: LISTAR TODAS (GET /api/tarefas)
    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    // Rota 2: CRIAR NOVA (POST /api/tarefas)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        // @RequestBody: Pega o JSON enviado pelo Angular e transforma em objeto Tarefa
        return repository.save(tarefa);
    }

    // Rota 3: DELETAR (DELETE /api/tarefas/5)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        // @PathVariable: Pega o "id" que veio na URL
        repository.deleteById(id);
    }

    // Rota 4: ATUALIZAR (Marcar como concluída) (PUT /api/tarefas/5)
    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        // Busca a tarefa existente
        return repository.findById(id)
                .map(tarefa -> { // Se encontrou...
                    tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    tarefa.setConcluida(tarefaAtualizada.isConcluida());
                    return repository.save(tarefa); // Salva as alterações
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada!")); // Se não encontrou
    }
}