package com.eliabdiel.model.dto;

import com.eliabdiel.model.task.Task;

public record TaskDetails(Long id, String title, String description) {

    public TaskDetails(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );
    }
}
