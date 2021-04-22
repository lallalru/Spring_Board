package eduspring.eduspring.controller;

import eduspring.eduspring.domain.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.sql.Types.NULL;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){

        if(model.containsAttribute("user")){ //리다이렉트 받음

        }else { //리다이렉트 못받음
            People people = new People();
            people.setId("비회원");
            model.addAttribute("user", people);
        }


        return "home";
    }
}
