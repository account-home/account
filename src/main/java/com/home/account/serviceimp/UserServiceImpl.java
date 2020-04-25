package  com.home.account.serviceimp;

import com.home.account.dao.UserDao;
import com.home.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Component
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    /**
     * 验证用户密码是否正确
     * @param user user
     * @return boolean
     */
    public   boolean validate(User user){

     User user2 = userDao.findById(user.getUser_id());
     //还有一种情况就是以手机号登陆的。
     if (user2!=null){
         //使用username进行验证
         if (user.getUser_id()!=null){
             //这有可能是手机号
             if ((user.getUser_id().equals(user2.getUser_id())||user.getUser_iphone().equals(user2.getUser_iphone()))&&user2.getUser_password().equals(user.getUser_password())){
                 return true;}
              }
           }
     return false;
    }
}
