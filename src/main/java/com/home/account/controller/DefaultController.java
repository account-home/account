package  com.home.account.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@Controller
public class DefaultController {
    @RequestMapping("/")
    public String  defaultIndex(){
        return "/login.html";
    }
}
