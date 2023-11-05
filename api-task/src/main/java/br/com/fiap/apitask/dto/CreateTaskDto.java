package br.com.fiap.apitask.dto;

import java.util.Date;

public record CreateTaskDto(
        String title,
        String description,
        Date dueDate
) {
}
