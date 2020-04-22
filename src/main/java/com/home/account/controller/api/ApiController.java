package com.home.account.controller.api;

import com.home.account.config.BaseConfig;
import com.home.account.config.BookConfig;
import com.home.account.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 所有api接口都写在这里
 * 多余操作方法写在api.ServiceUtils中
 */
@RequestMapping("api")
@RestController
public class ApiController {
    @Autowired
    private BookConfig bookConfig;
    private Result result = new Result();

    @RequestMapping("book")
    public Result getBookInfo(){
        bookConfig.setIp(BaseConfig.get("ftp.url"));
        result.setData(bookConfig);
        result.setSuccess(true);
        return result;
    }
}
