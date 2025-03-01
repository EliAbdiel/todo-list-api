package com.eliabdiel.model.task;

import com.eliabdiel.model.dto.TaskDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "user_id")
    private Long userId;

    public Task(TaskDto taskDto, Long userId) {
        this.title = taskDto.title();
        this.description = taskDto.description();
        this.userId = userId;
    }
}
