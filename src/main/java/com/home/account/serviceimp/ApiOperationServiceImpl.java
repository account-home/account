package  com.home.account.serviceimp;

import com.home.account.dao.ApiOperationDao;
import com.home.account.entity.ApiOperationLog;
import com.home.account.service.ApiOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class ApiOperationServiceImpl  implements ApiOperationService {

    @Autowired
    private ApiOperationDao apiOperationDao;

    private  Logger  logging = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addApiOperation(ApiOperationLog apiOperationLog) {
        try {
            apiOperationDao.addApiOperation(apiOperationLog);
        }catch (Exception e){
            logging.error(String.valueOf(e));
            logging.error("日志记载失败！");
        }
    }
}
