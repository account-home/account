package  com.home.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 测试时用的一个实体类
 * 用于测试是否读取到properties文件的信息
 */
@Component
@PropertySource("classpath:config/bookConfig.properties")
@ConfigurationProperties(prefix="book.host")
public class BookConfig {
    // @Configuration不需要，否则无法转为json

    private String name;

    private String ip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
