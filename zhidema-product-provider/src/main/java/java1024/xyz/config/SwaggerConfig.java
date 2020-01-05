package java1024.xyz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author xivinChen
 * @Email 1250402127@qq.com
 * @Versio V1.0
 **/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    final List<ResponseMessage> globalResponses = Arrays.asList(
            new ResponseMessageBuilder()
                    .code(200)
                    .message("成功")
                    .responseModel(new ModelRef("Success"))
                    .build(),
            new ResponseMessageBuilder()
                    .code(201)
                    .message("添加或者修改成功")
                    .build(),
            new ResponseMessageBuilder()
                    .code(400)
                    .message("错误请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(401)
                    .message("未授权")
                    .build(),
            new ResponseMessageBuilder()
                    .code(403)
                    .message("拒绝请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(404)
                    .message("未找到相关的请求")
                    .build(),
            new ResponseMessageBuilder()
                    .code(500)
                    .message("服务器内部错误")
                    .build());
    @Bean
    public Docket createRestApi() {


        List<Parameter> addParameters = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2).extensions(getExtension())
                .apiInfo(getApiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("java1024.xyz.controller"))
                    .paths(PathSelectors.any())
                    .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,globalResponses)
                .globalResponseMessage(RequestMethod.POST,globalResponses)
                .globalResponseMessage(RequestMethod.PUT,globalResponses)
                .globalResponseMessage(RequestMethod.DELETE,globalResponses)
                .globalOperationParameters(addParameters)
                ;
    }


    /**
     * @Description 增加顶级扩展，ListVendorExtension表示以列表形式
     * @Author Yu XueWen
     * @return List<VendorExtension>
     */
    private List<VendorExtension> getExtension(){

        List<VendorExtension> vendorExtensionsList = new ArrayList<>();

        List<String> stringList = new ArrayList<>();
        stringList.add("http");

        VendorExtension listVendorExtension = new ListVendorExtension<String>("schemes", stringList);

        vendorExtensionsList.add(listVendorExtension);

        return vendorExtensionsList;
    }

    private ApiInfo getApiInfo() {
        // 定义联系人信息
        Contact contact = new Contact("xivin","https://www.java1024.com/", "12504020127@qq.com");
        return new ApiInfoBuilder()
                .title("值得吗 价格记录账本") // 标题
                .description("值得吗价格记录账本 API信息") // 描述信息
                .version("1.0.0") // //版本
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }

    @Autowired
    private CachingOperationNameGenerator cachingOperationNameGenerator;


    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder().validatorUrl("").build();
    }
}
