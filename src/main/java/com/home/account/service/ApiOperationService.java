package  com.home.account.service;

import com.home.account.entity.ApiOperationLog;

public interface ApiOperationService {
    void addApiOperation(ApiOperationLog apiOperationLog);
}
