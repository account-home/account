package  com.home.account.dao;


import com.home.account.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    //根据ID查询User
    User findById(User user);

}
