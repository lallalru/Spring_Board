package eduspring.eduspring.repository;

import eduspring.eduspring.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    void deleteById(Long id);
    List<Member> findAll(Sort id);
    Page<Member> findAll(Pageable pageable);

}
