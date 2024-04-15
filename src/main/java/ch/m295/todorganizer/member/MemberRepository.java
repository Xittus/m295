package ch.m295.todorganizer.member;

import ch.m295.todorganizer.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    List<Member> findByOrderByNameAsc();

}
