package  com.home.account.entity;


import com.home.account.util.DateUtil;
import com.home.account.util.StringUtil;

import java.util.Date;

public class ApiOperationLog {
    private String operationId = StringUtil.getUUID();
    private String requestUrl;//请求地址
    private String requestMethod;//请求方式
    private String requestFunction;//请求方法名
    private String requestDate ;//请求时间
    private String requestParam;//请求参数
    private Long responseTime;//响应时长
    private String responseValue;//返回值
    private String clientIp;//请求方IP


    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestFunction() {
        return requestFunction;
    }

    public void setRequestFunction(String requestFunction) {
        this.requestFunction = requestFunction;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public ApiOperationLog() {
        this.requestDate = DateUtil.dateToString(new Date());
    }
}
