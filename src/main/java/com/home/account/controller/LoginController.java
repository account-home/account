package  com.home.account.controller;

import com.home.account.entity.User;
import com.home.account.serviceimp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 */
@RestController
@RequestMapping("/login")
public class LoginController {

     @Autowired
     private UserServiceImpl userService;

    @RequestMapping("/loginCheck")
    public String LoginCheck(String code, String userId, String userPassword,   HttpServletRequest requests) {

        //1，用户名和密码错误 2，验证码错误 3，验证成功
        String sessionCode = ""+requests.getSession().getAttribute("code");
        if(code != null && code != ""&&code.equals(sessionCode)){
            User user = new User();
            user.setUser_id(userId);
            user.setUser_password(userPassword);
            if(userService.validate(user)){
                return "succeed";
            }
            return "userName or password error";
        }else {
            return "code error";
        }

    }

}
