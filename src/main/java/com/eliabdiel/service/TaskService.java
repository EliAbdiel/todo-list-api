package com.eliabdiel.service;

import com.eliabdiel.infra.exception.ApiException;
import com.eliabdiel.model.dto.TaskDetails;
import com.eliabdiel.model.dto.TaskDto;
import com.eliabdiel.model.dto.UpdateTask;
import com.eliabdiel.model.task.Task;
import com.eliabdiel.repository.task.TaskRepository;
import com.eliabdiel.repository.user_role.UserRepository;
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
            var newTask = new Task(taskDto.title(), taskDto.description(), user.getId());
            var saved = taskRepository.save(newTask);
            return ResponseEntity.ok(new TaskDetails(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateTask(Long userId, Long taskId, UpdateTask updateTask) {
        try {
            var task = taskRepository.findById(taskId).orElseThrow(
                    () -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Task ID not exists.")
            );
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "User ID not exists")
            );
            if (updateTask.title() != null){
                task.setTitle(updateTask.title());
            }
            if (updateTask.description() != null) {
                task.setDescription(updateTask.description());
            }

            var saved = taskRepository.save(task);

            return ResponseEntity.ok(new TaskDetails(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error: " + e.getMessage());
        }
    }
}
