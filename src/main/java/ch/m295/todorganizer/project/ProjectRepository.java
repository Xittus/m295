package ch.m295.todorganizer.project;

import ch.m295.todorganizer.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOrderByNameAsc();

}