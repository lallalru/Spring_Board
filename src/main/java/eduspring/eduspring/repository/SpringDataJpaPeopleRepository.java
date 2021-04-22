package eduspring.eduspring.repository;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaPeopleRepository extends JpaRepository<People,String>,
        PeopleRepository {


    @Override
    Optional<People> findById(String id);

    @Override
    Optional<People> findByPs(String id);

}
