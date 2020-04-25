package  com.home.account.controller;

import com.home.account.entity.Result;
import com.home.account.entity.User;
import com.home.account.serviceimp.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 登录控制器
 */
@RestController
@RequestMapping("/login")
public class LoginController {

     @Autowired
     private UserServiceImpl userService;
     private Result result = new Result();

            /**
             *  验证代码逻辑
             *  验证通过生成token，将token存到redis中，token存入规则为（用户名，token）
             * @param code 验证码
             * @param userId 用户ID
             * @param userPassword 登录密码
             * @param requests 请求体
             * @return 返回token或者错误信息
             */
    @RequestMapping("/loginCheck")
    public Result LoginCheck(String code, String userId, String userPassword, HttpServletRequest requests) {


        //1，用户名和密码错误 2，验证码错误 3，验证成功
        String sessionCode = ""+requests.getSession().getAttribute("code");
        if(code != null && code != "" && code.equalsIgnoreCase(sessionCode)){
            User user = new User();
            user.setUser_id(userId);
            user.setUser_password(userPassword);
            if(userService.validate(user)){
                HttpSession session = requests.getSession();
                String token = "token";
                //分别存在session和redis里面
                session.setAttribute("token",token);
                result.setSuccess(true);
                result.setMessage("success");
                return result;
            }
            result.setSuccess(false);
            result.setMessage("userName or password error");
            return result;
        }else {
            result.setSuccess(false);
            result.setMessage("code error");
            return result;
        }

    }



}
