//package com.charisplace.luxsmartbuy.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public Docket productApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.charisplace.luxsmartbuy"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo getApiInfo(){
//        Contact contact = new Contact("luxsmartbuy", "http://luxsmartbuy.com", "contact.luxsmartbuy@gmail.com");
//        return new ApiInfoBuilder()
//                .title("LuxSmartBuy API")
//                .description("Documentation LuxSmartBuy API")
//                .version("1.0.0")
//                .license("http://www.apache.org/licences/LICENSE-2.0")
//                .contact(contact)
//                .build();
//    }
//}
