package br.com.fiap.apitask.model;

import br.com.fiap.apitask.dto.CreateTaskDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Boolean status;
    private Date dueDate;
    private Boolean isActive;

    public Task(CreateTaskDto data) {
        this.title = data.title();
        this.description = data.description();
        this.dueDate = data.dueDate();
        this.status = false;
        this.isActive = true;
    }

    public Task() {}
}
