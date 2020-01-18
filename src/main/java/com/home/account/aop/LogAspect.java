package  com.home.account.aop;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.home.account.entity.ApiOperationLog;
import com.home.account.serviceimp.ApiOperationServiceImpl;
import com.home.account.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * web请求日志切面类---专门针对控制层，如谁被请求了，花了多少时间，请求发送的参数，返回得值等
 * @author 吴应平
 */
@Aspect
@Component
@Order(3)
public class LogAspect {

    //定义日志记录器--获取sl4j包下提供的logger
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required=true)
    private ApiOperationServiceImpl apiOperationServiceImpl;

    //定义一个对象
    ThreadLocal<Long> startTime = new ThreadLocal<>();  //线程副本类去记录各个线程的开始时间


    //自定义切入点
    @Pointcut("@annotation(com.home.account.aop.WebLog)")
    public void webLog() {  }
    //自定义切入点
    @Pointcut("@annotation(com.home.account.aop.OperLog)")
    public void apiLog() {  }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {        //方法里面注入连接点
        logger.info("=====================前置通知=========================");           //info ,debug ,warn ,erro四种级别，这里我们注入info级别
        startTime.set(System.currentTimeMillis());
        //获取servlet请求对象---因为这不是控制器，这里不能注入HttpServletRequest，但springMVC本身提供ServletRequestAttributes可以拿到
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("请求地址URL:" + request.getRequestURL().toString());         // 想那个url发的请求
        logger.info("请求方式METHOD:" + request.getMethod());
        logger.info("请求的类方法CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());                     // 请求的是哪个类，哪种方法
        logger.info("请求的参数ARGS:" + Arrays.toString(joinPoint.getArgs()));     // 方法本传了哪些参数
        logger.info("=====================前置通知结束=========================");
    }


    //方法的返回值注入给ret
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void after(Object ret) {
        logger.info("=====================后置通知=========================");
        logger.info("返回对象RESPONSE:" + ret);       // 响应的内容---方法的返回值responseEntity
        logger.info("响应时间SPEND:" + (System.currentTimeMillis() - startTime.get()));
        logger.info("=====================后置通知结束=========================");
    }

    /**
     * 获取切面注解的描述
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(WebLog.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

    /**
     * 环绕设置，前后都需要，记载请求和返回值
     *
     * @param joinPoint joinPoint
     * @return 返回目标方法的返回值
     */
    @Around("apiLog() && @annotation(operLog)")
    public Object around(ProceedingJoinPoint joinPoint,OperLog operLog) {
        long startTime = System.currentTimeMillis();
        logger.info("=====================环绕日志记载========================");
        ApiOperationLog operation = new ApiOperationLog();
        Object result = null;
        try {
            //获取切点 方法的返回值
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //获取servlet请求对象---因为这不是控制器，这里不能注入HttpServletRequest，但springMVC本身提供ServletRequestAttributes可以拿到
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        operation.setRequestUrl(request.getRequestURL().toString()); //请求地址
        operation.setRequestMethod(request.getMethod());//请求方法
        logger.info(operLog.operType());
        //post和get获取参数方式不一样
        operation.setRequestParam(StringUtil.objectToString(joinPoint.getArgs()));//请求参数
        operation.setRequestFunction(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());//请求函数名
        operation.setResponseValue(new Gson().toJson(result));//响应返回值
        operation.setResponseTime(System.currentTimeMillis() - startTime);//响应时间
        operation.setClientIp(getRemoteHostIP(request.getRemoteHost())+":"+request.getRemotePort());
        apiOperationServiceImpl.addApiOperation(operation);//保存日志
        logger.info("=====================环绕日志记载结束========================");
        //返回目标方法的返回值
        return result;
    }

    /**
     * 本地IPV6转V4的方法
     * @param ip
     * @return
     */
    public static String getRemoteHostIP(String ip) {
        if(ip.equals("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        return ip;
    }

}