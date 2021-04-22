package eduspring.eduspring.repository;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,
        MemberRepository {

    @Override
    Optional<Member> findByName(String name);

    @Override
    void deleteById(Long id);

//    @Override
//    Optional<People> findById(String id);
//
//    @Override
//    Optional<People> findByPs(String ps);

}
