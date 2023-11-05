package br.com.fiap.apitask.controller;

import br.com.fiap.apitask.dto.CreateTaskDto;
import br.com.fiap.apitask.model.Task;
import br.com.fiap.apitask.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("task")
public class TaskController {

    private TaskService taskService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity create (@RequestBody CreateTaskDto data, UriComponentsBuilder builder) {
        System.out.println("DENTRO DO CREATE TASK");
        Task task = new Task(data);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        taskService.registration(task, authentication);
        System.out.println(authentication);
        var uri = builder.path("/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(task);
    }
}
