# 实现自定义日志功能
## 1.注释类

- 创建注释类，添加该注释的方法，表明在执行后需要打印执行日志
~~~java
@Retention(RetentionPolicy.RUNTIME)//表明运行时生效
@Target(ElementType.METHOD) //表明在方法上生效
public @interface SystemLog {
    String businessName(); //添加注释时可添加内容表明方法的作用
}
~~~
## 2.切面类

- 使用aop，反射等方法实现日志功能
~~~java
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.wz.blogcommon.annotation.SystemLog)")//表明所有添加该注释的方法都是切点
    public void pt() { // 切点
        
    }

    /**
     * 
     * @param joinPoint 是切点所在方法的封装
     * @return
     * @throws Throwable
     */
    @Around("pt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed(); // 执行切点方法
            handleAfter(ret);
        } finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleAfter(Object proceed) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(proceed));
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        //通过ThreadLocal获取当前线程正在处理的请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        SystemLog systemLog = getSystemLog(joinPoint);

        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURI());
        // 打印描述信息
        log.info("BusinessName   : {}", systemLog.businessName());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getRequestURI());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        // TODO 参数为MultipartFile时无法打印，抛出异常
        log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));
    }

    private SystemLog getSystemLog(ProceedingJoinPoint joinPoint) {
        //获取切点方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(SystemLog.class);
    }
}
~~~