package eduspring.eduspring.repository;

import eduspring.eduspring.domain.People;

import java.util.*;

public class MemoryPeopleRepository implements PeopleRepository {
    private static Map<String, People> peopleStore = new HashMap<>();
    private static long sequence =0L;

    @Override
    public People save(People people) {
        peopleStore.put(people.getId(), people );
        return people;
    }

    @Override
    public Optional<People> findById(String id) {
        return Optional.ofNullable(peopleStore.get(id));
//        return peopleStore.values().stream()
//                .filter(people -> people.getId().equals(id))
//                .findAny();
    }

    @Override
    public Optional<People> findByPs(String ps) {
        return peopleStore.values().stream()
                .filter(people -> people.getPs().equals(ps))
                .findAny();
    }
}
