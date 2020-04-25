package  com.home.account.dao;


import com.home.account.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {

    //根据ID查询User
    User findById(String  id);

    User findByUser(User user);

    User findByIphone(String iphone);

}
