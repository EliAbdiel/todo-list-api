package com.eliabdiel.service;

import com.eliabdiel.infra.exception.ApiException;
import com.eliabdiel.model.dto.TaskDetails;
import com.eliabdiel.model.dto.TaskDto;
import com.eliabdiel.model.task.Task;
import com.eliabdiel.repository.TaskRepository;
import com.eliabdiel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> addTask(Long userId, TaskDto taskDto) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "User ID not found")
            );
            var newTask = new Task(taskDto.title(), taskDto.description(), user);
            var saved = taskRepository.save(newTask);
            return ResponseEntity.ok(new TaskDetails(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
}
