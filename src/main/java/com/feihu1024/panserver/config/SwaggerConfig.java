package com.feihu1024.panserver.config;


import com.feihu1024.panserver.common.SwaggerProperties;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger配置
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerProperties.getEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())) // 为当前包路径
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())   // 页面标题
                .version(swaggerProperties.getApplicationVersion())   // 版本号
                .description(swaggerProperties.getApplicationDescription())
                .contact(new Contact("feihu1024", null, null))    // 描述
                .build();
    }
}

@Component
class OwnSwaggerAddtion implements ApiListingScannerPlugin {
    /**
     * 实现此方法可手动添加ApiDescriptions
     */
    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return Arrays.asList(createLoginApi());
    }

    /**
     * 是否使用此插件
     *
     * @param documentationType swagger文档类型
     * @return true 启用
     */
    @Override
    public boolean supports(DocumentationType documentationType) {
        return DocumentationType.OAS_30.equals(documentationType);
    }

    private ApiDescription createLoginApi() {
        // 参数列表
        List<RequestParameter> requestParameters=new ArrayList<>();
        requestParameters.add(new RequestParameterBuilder().name("client_id").description("客户端id").in(ParameterType.QUERY).parameterIndex(0).required(true).build());
        requestParameters.add(new RequestParameterBuilder().name("client_secret").description("客户端密码").in(ParameterType.QUERY).parameterIndex(1).required(true).build());
        requestParameters.add(new RequestParameterBuilder().name("grant_type").description("登录类型").in(ParameterType.QUERY).parameterIndex(2).required(true).build());
        requestParameters.add(new RequestParameterBuilder().name("username").description("用户名").in(ParameterType.QUERY).parameterIndex(3).required(true).build());
        requestParameters.add(new RequestParameterBuilder().name("password").description("密码").in(ParameterType.QUERY).parameterIndex(4).required(true).build());

        Operation loginOperation = new OperationBuilder(new CachingOperationNameGenerator()).method(HttpMethod.POST).summary("登录").notes("用户登陆获取token").tags(Sets.newHashSet("权限管理服务")).requestParameters(requestParameters).build();
        ApiDescription loginApiDescription = new ApiDescription(null, "/oauth/token", null, null, Arrays.asList(loginOperation), false);

        return loginApiDescription;
    }
}