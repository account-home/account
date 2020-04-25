package  com.home.account.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
@WebFilter
@Component
public class LoginFilter implements Filter {
  /*  static ArrayList<String> javaList = new ArrayList<String>();
    static ArrayList<String> webList = new ArrayList<String>();
    static{
        //需要排除的login.html
        javaList.add("/login/loginCheck");
        javaList.add("/code/getCode");
        javaList.add("/api/printmanycatalog");


    }

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    /**
     * 登陆请求拦截，session里面不存在user的，将不许通过
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException
     * @throws ServletException
     */
   /* @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session  = request.getSession();
        String path = request.getServletPath();
        boolean allowedPath = javaList.contains(path);
        boolean webPath = webList.contains(path);

        if (allowedPath || webPath) {
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }
        Object obj = session.getAttribute("token");
        if(obj !=null && obj.equals("token")){
            logger.info("通过过滤器");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //没有session，就直接跳转到/login.html
        logger.info("未通过过滤器，即将跳转到登录界面！");
        request.getRequestDispatcher("/index.html").forward(servletRequest,  servletResponse);
        return ;

    }


}*/
