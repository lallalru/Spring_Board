package eduspring.eduspring.controller;

import eduspring.eduspring.domain.Member;
import eduspring.eduspring.domain.People;
import eduspring.eduspring.service.MemberService;
import eduspring.eduspring.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

@Controller
public class MemberController {

    private MemberService memberService;
    private PeopleService peopleService;

    @Autowired
    public MemberController(MemberService memberService, PeopleService peopleService){
        this.memberService = memberService;
        this.peopleService = peopleService;
    }

    @PostMapping("/people/signIn")
    public String signin(PeopleForm form, Model model , RedirectAttributes rttr){
        People people = new People();
        people.setId(form.getId());
        people.setPs(form.getPs());
        people.setBookmark(NULL);

        String result = peopleService.joinPeople(people);
        if(result.equals("")){ //중복 아이디
            rttr.addFlashAttribute("message",
                    "가입실패. "+form.getId()+"는 이미존재하는 아이디 입니다.");
        }else{
            rttr.addFlashAttribute("message","가입성공");
            rttr.addFlashAttribute("user", people);
        }

        return "redirect:/";
    }

    @PostMapping("/people/logIn")
    public String login(PeopleForm form, Model model , RedirectAttributes rttr){
        People people = new People();
        people.setId(form.getId());
        people.setPs(form.getPs());
        Optional<People> profile =peopleService.findOne(people);
        if( people.getPs().equals(profile.get().getPs()) ){//비밀번호 일치 체크
            rttr.addFlashAttribute("message","로그인 성공");
            rttr.addFlashAttribute("user", people);
        }else{
            rttr.addFlashAttribute("message","가입 정보가 일치하지 않습니다.");
        }



        return "redirect:/";
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

    @PostMapping("/members/content/{id}")
    public String update(MemberForm form, Member member){
        member.setName(form.getName());
        member.setContent((form.getContent()));
        memberService.join(member);
        return "redirect:/members";
    }

    @DeleteMapping("/members/content/{id}")
    public String delete(@PathVariable("id") Long id){
        memberService.delete(id);
        return "redirect:/members";
    }

}
