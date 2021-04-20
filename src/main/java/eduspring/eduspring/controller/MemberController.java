package eduspring.eduspring.controller;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.dto.MemberDto;
import eduspring.eduspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        member.setContent(form.getContent());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    @GetMapping("/members/content/{id}")
    public String view(Model model, @PathVariable("id")Long id){
        Optional<Member> result = memberService.findOne(id);
        Member member = new Member();
        member.setId(result.get().id);
        member.setName(result.get().name);
        member.setContent(result.get().content);

        model.addAttribute("member", member);
        return "members/boardContent";
    }

  /*  @PutMapping("/members/content/{id}")
    public String modify(MemberForm form, Member member){
        member.setName(form.getName());
        member.setContent((form.getContent()));
        System.out.println("test11");
//        memberService.modify(member);
        return "members/memberList";
    }*/

}
