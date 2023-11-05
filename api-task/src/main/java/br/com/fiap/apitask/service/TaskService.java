package br.com.fiap.apitask.service;

import br.com.fiap.apitask.model.Task;
import br.com.fiap.apitask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public void registration(Task task, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
        }
        repository.save(task);
    }
}
