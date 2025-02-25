package com.eliabdiel.controller;

import com.eliabdiel.model.dto.TaskDto;
import com.eliabdiel.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTask(@PathVariable Long userId, @RequestBody TaskDto taskDto) {
        return taskService.addTask(userId, taskDto);
    }
}
