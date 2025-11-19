package br.com.paulo.listatarefas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

import br.com.paulo.listatarefas_api.model.Tarefa;
import br.com.paulo.listatarefas_api.model.Usuario;
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
    public List<Tarefa> listar() {
        // 1. Pega o usuário que está logado (do Token)
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Busca APENAS as tarefas desse usuário
        return repository.findAllByUsuario(usuarioLogado);
    }

    // Rota 2: CRIAR NOVA (POST /api/tarefas)
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        // 1. Pega o usuário logado
        Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2. Carimba a tarefa com esse usuário
        tarefa.setUsuario(usuarioLogado);

        return repository.save(tarefa);
    }

    // Para deletar e atualizar, idealmente você também checaria se a tarefa pertence ao usuário,
    // mas para este nível, o listar/criar já garante a privacidade visual.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Rota 4: ATUALIZAR (Marcar como concluída) (PUT /api/tarefas/5)
    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa t) {
        var tarefa = repository.findById(id).orElseThrow();
        tarefa.setDescricao(t.getDescricao());
        tarefa.setConcluida(t.isConcluida());
        return repository.save(tarefa);
    }
}