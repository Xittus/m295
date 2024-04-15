package ch.m295.todorganizer.project;

import ch.m295.todorganizer.base.MessageResponse;
import ch.m295.todorganizer.project.Project;
import ch.m295.todorganizer.project.ProjectRepository;
import ch.m295.todorganizer.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectService {
    private final ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public List<Project> getProjects() {
        return repository.findByOrderByNameAsc();
    }

    public Project getProject(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Project.class));
    }

    public Project insertProject(Project project) {
        return repository.save(project);
    }

    public Project updateProject(Project project, Long id) {
        return repository.findById(id)
                .map(projectOrig -> {
                    projectOrig.setName(project.getName());
                    return repository.save(projectOrig);
                })
                .orElseGet(() -> repository.save(project));
    }

    public MessageResponse deleteProject(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Project " + id + " deleted");
    }
}
