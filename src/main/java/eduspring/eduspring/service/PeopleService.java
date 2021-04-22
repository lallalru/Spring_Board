package eduspring.eduspring.service;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import eduspring.eduspring.repository.MemberRepository;
import eduspring.eduspring.repository.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class PeopleService {

    private PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public String joinPeople(People people) { //회원가입
        //같은 이름의 중복 회원 가입 불가

        Boolean test = findOne(people).isPresent();
        if(test){
            return "";
        }else {
            peopleRepository.save(people);
            return people.getId();
        }
    }

    public Optional<People> findOne(People people) {
        System.out.println("hi hi hi");
        return (peopleRepository.findById(people.getId()));

    }

    public Optional<People> findPs(People people) {
        return peopleRepository.findByPs(people.getId());
    }
}
