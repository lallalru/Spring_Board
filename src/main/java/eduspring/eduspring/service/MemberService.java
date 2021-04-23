package eduspring.eduspring.service;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.dto.MemberDto;
import eduspring.eduspring.repository.MemberRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
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

    public Long join(Member member) { //게시판 등록
            //validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) { //중복체크용 함수
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 이름입니다.");
            });
    }

    public List<Member> findMembers(){  //전체게시판조회
        List<Member> memberEntity = memberRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<MemberDto> memberDto = new ArrayList<>();
        Page<Member> page = new PageImpl<Member>(memberEntity);

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

    public List<Member> findAll(PageRequest pageRequest){
        Page<Member> recordsPage = memberRepository.findAll(pageRequest);
        return recordsPage.getContent();
    }

}
