package eduspring.eduspring.dto;

import eduspring.eduspring.domain.Member;
import lombok.Builder;

public class MemberDto {
    private Long id;
    private String name;
    private String content;

    public Member toMember(){
        Member member = new Member();
        member.setName(name);
        member.setId(id);
        member.setContent(content);
        return member;
    }

    @Builder
    public MemberDto(long id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
