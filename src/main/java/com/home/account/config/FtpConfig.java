package  com.home.account.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/ftpConfig.properties")
@ConfigurationProperties(prefix="ftp.host")
public class FtpConfig {
    private   String  host;// FTP服务器hostname
    private   int     port;// FTP服务器端口
    private   String  username;// FTP登录账号
    private   String  password;// FTP登录密码
    private   String  basePath;// FTP服务器基础目录

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getBasePath() {
        return basePath;
    }

    public  void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
