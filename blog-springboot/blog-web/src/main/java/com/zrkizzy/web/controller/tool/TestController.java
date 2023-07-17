package com.zrkizzy.web.controller.tool;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.zrkizzy.common.annotation.ParamMean;
import com.zrkizzy.common.base.response.Result;
import com.zrkizzy.common.config.properties.OssProperties;
import com.zrkizzy.common.enums.file.FIleTypeEnum;
import com.zrkizzy.common.utils.ServletUtil;
import com.zrkizzy.common.utils.file.FileExportUtil;
import com.zrkizzy.data.vo.export.ApiScanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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

    @ApiOperation("扫描所有接口并导出为Excel")
    @ParamMean(in = "测试String参数, 测试type参数", out = "返回结果对象")
    @GetMapping("/apiScan")
    public void apiScan(String str, Integer inter) throws UnsupportedEncodingException {
        try {
            List<ApiScanVO> list = setApiScanInfo();
            HttpServletResponse response = ServletUtil.getResponse();
            FileExportUtil.setResponseProp(response, "test", FIleTypeEnum.XLSX);
            EasyExcel.write(response.getOutputStream())
                    .head(ApiScanVO.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("测试第一页")
                    .doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ApiScanVO> setApiScanInfo() {
        List<ApiScanVO> list = new ArrayList<>();
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
            // 请求方法
            String methodName = method.getName();
            // 请求类名
            String className = bean.getName();

            // 反射注解
            Api api = bean.getAnnotation(Api.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            ParamMean paramMean = method.getAnnotation(ParamMean.class);
            // 获取当前参数类型
            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
            Parameter[] parameters = method.getParameters();
            ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

            // 请求类描述
            String classDesc = null;
            // 请求方法描述
            String methodDesc = null;
            // 请求方法类型
            String methodType = null;
            // 请求URL
            String url = null;
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            methodType = methodsCondition.getMethods().toString();
            Set<String> patternValues = requestMappingInfo.getPatternValues();
            for (String patternValue : patternValues) {
                url = patternValue;
            }
            if (null != api) {
                classDesc = Arrays.toString(api.tags());
                if (null != apiOperation) {
                    methodDesc = apiOperation.value();
                }
                // 入参描述
                String inDesc = null;
                // 返回参数描述
                String outDesc = null;
                if (null != paramMean) {
                    inDesc = "[" + paramMean.in() + "]";
                    outDesc = "[" + paramMean.out() + "]";
                }
                // 返回参数类型
                String outType = null;
                MethodParameter returnType = handlerMethod.getReturnType();
                Type genericType = returnType.getGenericParameterType();
                if (genericType instanceof ParameterizedType parameterizedType) {
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    if (typeArguments.length > 0) {
                        Type typeArgument = typeArguments[0];
                        if (typeArgument instanceof ParameterizedType nestedParameterizedType) {
                            Type[] nestedTypeArguments = nestedParameterizedType.getActualTypeArguments();
                            if (nestedTypeArguments.length > 0) {
                                Type nestedTypeArgument = nestedTypeArguments[0];
                                if (nestedTypeArgument instanceof Class<?> genericClass) {
                                    // 返回参数类型
                                    outType = genericClass.getName();
                                }
                            }

                        } else if (typeArgument instanceof Class<?> genericClass) {
                            // 返回参数类型
                            outType = genericClass.getName();
                        }
                    }
                }
                if (!StringUtils.hasLength(outType)) {
                    outType = "void";
                }
                // 入参名称数组
                String inParam = "[]";
                // 参数类型
                StringBuilder inParamType = new StringBuilder();
                if (methodParameters.length > 0) {
                    String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
                    inParam = Arrays.toString(parameterNames);
                    inParamType.append("[");
                    for (Parameter parameter : parameters) {
                        Class<?> type = parameter.getType();
                        inParamType.append(type.getName()).append(", ");
                    }
                    // 清除最后多余的","
                    inParamType.delete(inParamType.lastIndexOf(","), inParamType.length()).append("]");
                }
                ApiScanVO apiScanVO = ApiScanVO.builder()
                        .className(className)
                        .classDesc(classDesc)
                        .url(url)
                        .method(methodName)
                        .methodDesc(methodDesc)
                        .methodType(methodType)
                        .inParam(inParam)
                        .inParamType(inParamType.toString())
                        .inDesc(inDesc)
                        .outType(outType)
                        .outDesc(outDesc)
                        .build();
                list.add(apiScanVO);
            }
        }
        return list;
    }
}
