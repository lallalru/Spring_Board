package eduspring.eduspring.repository;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository  {

    People save(People people);
    Optional<People> findById(String id);
    Optional<People> findByPs(String id);
}
