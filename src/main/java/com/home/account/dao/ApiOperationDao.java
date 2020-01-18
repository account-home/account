package  com.home.account.dao;

import com.home.account.entity.ApiOperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ApiOperationDao {
  void addApiOperation(ApiOperationLog apiOperationLog);
}
