package com.eliabdiel.controller;

import com.eliabdiel.model.dto.TaskDto;
import com.eliabdiel.model.dto.UpdateTask;
import com.eliabdiel.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable Long userId, @PageableDefault(size = 5) Pageable pageable) {
        return taskService.getAllTasks(userId, pageable);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTask(@PathVariable Long userId, @RequestBody TaskDto taskDto) {
        return taskService.addTask(userId, taskDto);
    }

    @PutMapping("/{taskId}/users/{userId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @PathVariable Long userId, @RequestBody UpdateTask updateTask) {
        return taskService.updateTask(userId, taskId, updateTask);
    }
}
