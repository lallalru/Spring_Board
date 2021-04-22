package eduspring.eduspring.repository;

import eduspring.eduspring.domain.People;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaPeopleRepository implements PeopleRepository{


    private final EntityManager em;


    public JpaPeopleRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public People save(People people) {
        em.persist(people);
        return people;
    }

    @Override
    public Optional<People> findById(String id) {
        People people = em.find(People.class, id);
        return Optional.ofNullable(people);
    }

    @Override
    public Optional<People> findByPs(String ps) {
        List<People> result = em.createQuery("select m from People m where m.ps=:ps", People.class)
                .setParameter("ps",ps)
                .getResultList();
        return result.stream().findAny();
    }


}
