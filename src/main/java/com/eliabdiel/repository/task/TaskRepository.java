package com.eliabdiel.repository.task;

import com.eliabdiel.model.task.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findTaskBy(Pageable pageable);

    //boolean existsByUserId(Long userId);
}
