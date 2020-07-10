//package cm.webmvc.loadbalance.test;
//
//import feign.Target;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.DefaultParameterNameDiscoverer;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.expression.EvaluationContext;
//import org.springframework.expression.Expression;
//import org.springframework.expression.spel.standard.SpelExpressionParser;
//import org.springframework.expression.spel.support.StandardEvaluationContext;
//import org.springframework.stereotype.Component;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//@Slf4j
//@Aspect
//@Component
//public class TestAdvice {
//
//    @Autowired
//    TestService testService;
//
//    /**
//     * 用于SpEL表达式解析.
//     */
//    private SpelExpressionParser parser = new SpelExpressionParser();
//    /**
//     * 用于获取方法参数定义名字.
//     */
//    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
//
////    @Around("execution(* cm.webmvc.loadbalance.FeignClientInterface+.*(..))")
////    public Object test(ProceedingJoinPoint joinPoint) {
////        Object result = null;
////        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
////        Method method = methodSignature.getMethod();
////        log.info(method.getName());
////        try {
////            result = joinPoint.proceed();
////        } catch (Throwable e) {
////
////        }
////        return result;
////    }
//
//    /**
//     * 返回通知
//     */
////    @Around("@within(org.springframework.cloud.openfeign.FeignClient) && @annotation(feignClient)")
//
//
//
//
//    @Around("execution(* cm.webmvc.loadbalance.FeignClientInterface+.*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) throws Exception {
//        log.info("进入AOP");
//        Object proxy = joinPoint.getTarget();
//        Target.HardCodedTarget
//        Class clazz = proxy.getClass();
//        Field url = clazz.getDeclaredField("url");
//        url.setAccessible(true);
//        url.set(proxy, testService.get());
//        Object result = null;
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            log.error(e.getMessage());
//        } finally {
//            return result;
//        }
//    }
//
//    public String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
//        Expression expression = parser.parseExpression(spELString);
//        EvaluationContext context = new StandardEvaluationContext();
//        Object[] args = joinPoint.getArgs();
//        for(int i = 0 ; i < args.length ; i++) {
//            context.setVariable(paramNames[i], args[i]);
//        }
//        return expression.getValue(context).toString();
//    }
//
//    private <T extends Annotation> T getAnnotation(Method method, Class<T> cls) {
//        T annotation = AnnotationUtils.findAnnotation(method, cls);
//        if (annotation == null) {
//            annotation = AnnotationUtils.findAnnotation(method.getDeclaringClass(), cls);
//        }
//        return annotation;
//    }
//}
