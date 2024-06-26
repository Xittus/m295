package ch.m295.todorganizer.task;

import ch.m295.todorganizer.category.Category;
import ch.m295.todorganizer.member.Member;
import ch.m295.todorganizer.project.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "memberid")
    private Member member;

    @ManyToOne(optional = true)
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToOne(optional = true)
    @JoinColumn(name = "projectid")
    private Project project;

    public Task(String name,Category category, Member member, Project project) {
        this.name = name;
        this.category = category;
        this.member = member;
        this.project= project;
    }

    public Task() {
    }
}
