package ch.m295.todorganizer.task;

import ch.m295.todorganizer.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOrderByNameAsc();

}