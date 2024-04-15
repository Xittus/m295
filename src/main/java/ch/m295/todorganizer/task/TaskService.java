package ch.m295.todorganizer.task;

import ch.m295.todorganizer.base.MessageResponse;
import ch.m295.todorganizer.project.Project;
import ch.m295.todorganizer.project.ProjectRepository;
import ch.m295.todorganizer.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getTasks() {
        return repository.findByOrderByNameAsc();
    }

    public Task getTask(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Task.class));
    }

    public Task insertTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task, Long id) {
        return repository.findById(id)
                .map(taskOrig -> {
                    taskOrig.setName(task.getName());
                    return repository.save(taskOrig);
                })
                .orElseGet(() -> repository.save(task));
    }

    public MessageResponse deleteTask(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Task " + id + " deleted");
    }
}
