package eduspring.eduspring.service;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.dto.MemberDto;
import eduspring.eduspring.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) { //회원가입
        //같은 이름의 중복 회원 가입 불가

            //validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원 이름입니다.");
            });
    }

    public List<Member> findMembers(){  //전체회원조회
        List<Member> memberEntity = memberRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<MemberDto> memberDto = new ArrayList<>();

        for(Member member : memberEntity){
            MemberDto dto = MemberDto.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .content(member.getContent())
                    .build();
            memberDto.add(dto);
        }
        return memberRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);

    }

    public void delete(Long id){
        memberRepository.deleteById(id);

    }
}
