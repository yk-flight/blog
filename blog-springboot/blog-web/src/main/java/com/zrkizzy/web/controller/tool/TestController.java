package com.zrkizzy.web.controller.tool;

import com.zrkizzy.common.annotation.ParamMean;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.config.properties.OssProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.mail.MessagingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 系统创建测试类
 *
 * @author zhangrongkang
 * @since 2023/3/6
 */
@Api(tags = "系统创建测试类")
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 邮件发送人
     */
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @ApiOperation("获取邮件发送人")
    @GetMapping("/getEmailFrom")
    public String getEmailFrom() {
        return sender;
    }

    @ApiOperation("测试发送邮件")
    @GetMapping("/email")
    public void sendEmail() {
        try {
            // true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            // 邮件发信人
            mimeMessageHelper.setFrom("1072876976@qq.com");
            // 邮件收信人
            mimeMessageHelper.setTo("1072876976@qq.com");
            // 邮件主题
            mimeMessageHelper.setSubject("测试邮件发送功能");
            // 邮件内容
            mimeMessageHelper.setText("当你看到我的时候就已经成功了");
            // 邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }
    }

    @GetMapping("/testOssProperties")
    public Result<?> testOssProperties() {
        String builder = ossProperties.getDomain() + "\\n" +
                ossProperties.getEndpoint() + "\\n" +
                ossProperties.getAccessKeyId() + "\\n" +
                ossProperties.getAccessKeySecret() + "\\n" +
                ossProperties.getBucketName() + "\\n";
        return Result.success(builder);
    }

    @ApiOperation("扫描所有接口")
    @ParamMean(in = "测试String参数, 测试type参数", out = "返回结果对象")
    @GetMapping("/apiScan")
    public Result<?> apiScan(String str, Integer inter) {
        RequestMappingHandlerMapping mapping = webApplicationContext.getBean("requestMappingHandlerMapping",RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的所有方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> methodEntry : methodMap.entrySet()) {
            // 请求映射信息
            RequestMappingInfo requestMappingInfo = methodEntry.getKey();
            // 处理器方法
            HandlerMethod handlerMethod = methodEntry.getValue();

            // 获取当前所在方法名
            Method method = handlerMethod.getMethod();
            // 获取当前类名
            Class<?> bean = handlerMethod.getBeanType();
            System.out.println("当前处理的方法： " + method.getName() + "  ------------------------------------------");

            // 反射注解
            Api api = bean.getAnnotation(Api.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            ParamMean paramMean = method.getAnnotation(ParamMean.class);
            // 获取当前参数类型
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            Parameter[] parameters = method.getParameters();
            ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

            if (null != api) {
                System.out.println("当前类注解：" + Arrays.toString(api.tags()));
                if (null != apiOperation) {
                    System.out.println("当前方法注解：" + apiOperation.value());
                }
                if (null != paramMean) {
                    System.out.println("入参注解：" + paramMean.in());
                    System.out.println("出参注解：" + paramMean.out());
                }
                MethodParameter returnType = handlerMethod.getReturnType();
                Type genericType = returnType.getGenericParameterType();
                if (genericType instanceof ParameterizedType parameterizedType) {
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    if (typeArguments.length > 0) {
                        Type typeArgument = typeArguments[0];
                        if (typeArgument instanceof Class<?> genericClass) {
                            System.out.println("出参类型：" + genericClass.getName());
                        }
                    }
                }
                RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
                System.out.println("方法类型：" + methodsCondition.getMethods());
                Set<String> patternValues = requestMappingInfo.getPatternValues();
                for (String patternValue : patternValues) {
                    System.out.println("请求URL：" + patternValue);
                }
                if (methodParameters.length == 0) {
                    System.out.println("参数类型：[]");
                } else {
                    String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                    System.out.println("参数名称：" + Arrays.toString(parameterNames));
                    StringBuilder builder = new StringBuilder();
                    builder.append("[");
                    for (Parameter parameter : parameters) {
                        Class<?> type = parameter.getType();
                        builder.append(type.getName()).append(", ");
                    }
                    // 清除最后多余的","
                    builder.delete(builder.lastIndexOf(","), builder.length()).append("]");
                    System.out.println("参数类型：" + builder.toString());
                }
            }
        }

        return Result.success();
    }
}
