package  com.home.account.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter
public class LoginFilter implements Filter {
    static ArrayList<String> arrayList = new ArrayList<String>();
    static{
        //需要排除的login.html
        arrayList.add("/research/views/login.html");
        arrayList.add("/research/views/error.html");
        arrayList.add("/research/loginCheck");
        arrayList.add("/research/getCode");
        arrayList.add("/research/login.html");
        arrayList.add("/research/file/getFile");
        arrayList.add(" /research/img/login.jpg");

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
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       /* servletResponse.setCharacterEncoding("utf-8");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session  = request.getSession();
        String path = request.getRequestURI();
        boolean allowedPath = arrayList.contains(path);
        if (allowedPath) {
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }
        Object obj = session.getAttribute("user");
        if(obj !=null ){
            User user = (User) obj;
            logger.info(user.getUserId()+"通过过滤器");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //没有session，就直接跳转到/views/error.html

       // logger.info("未通过过滤器，即将跳转到登录界面！");
       // request.getRequestDispatcher("/views/error.html").forward(servletRequest,  servletResponse);
        return ;
*/
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
