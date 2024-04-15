package ch.m295.todorganizer.project;

import ch.m295.todorganizer.base.MessageResponse;
import ch.m295.todorganizer.project.Project;
import ch.m295.todorganizer.project.ProjectService;
import ch.m295.todorganizer.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("api/project")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Project>> all() {
        List<Project> result = projectService.getProjects();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("api/project/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Project> one(@PathVariable Long id) {
        Project project = projectService.getProject(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("api/project")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Project> newMember(@Valid @RequestBody Project project) {
        Project savedProject = projectService.insertProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @PutMapping("api/project/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project, @PathVariable Long id) {
        Project savedProject = projectService.updateProject(project, id);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @DeleteMapping("api/project/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.deleteProject(id));
        } catch (Throwable t) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
