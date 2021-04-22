package eduspring.eduspring.repository;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{


    private final EntityManager em;


    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
         em.persist(member);
         return member;
    }

//    @Override
//    public People save(People people) {
//        em.persist(people);
//        return people;
//    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

//    @Override
//    public Optional<People> findById(String id) {
//        People people = em.find(People.class, id);
//        return Optional.ofNullable(people);
//    }
//
//    @Override
//    public Optional<People> findByPs(String ps) {
//        List<People> result = em.createQuery("select m from People m where m.ps=:ps", People.class)
//                .setParameter("ps",ps)
//                .getResultList();
//        return result.stream().findAny();
//    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void deleteById(Long id) {
        em.detach(id);
    }

    @Override
    public List<Member> findAll(Sort id) {
        return em.createNamedQuery("select m from Member as m", Member.class)
                .getResultList();
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return null;
    }


}
