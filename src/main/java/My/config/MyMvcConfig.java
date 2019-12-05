package My.config;

import My.component.LoginHandlerInterceptor;
import My.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    //视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/index").setViewName("index");
//        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main").setViewName("dashboard");
        registry.addViewController("/main.html").setViewName("dashboard");

//        下面的这些是我暂时调试不同main页面的时候用的 到时候有了登陆自动识别跳转之后可以注释掉 因为留着或许不太好 有串台风险 包括上面的main也一样
        registry.addViewController("/mainStu.html").setViewName("mainStu");
        registry.addViewController("/mainStu").setViewName("mainStu");
        registry.addViewController("/mainManager.html").setViewName("mainManager");
        registry.addViewController("/mainManager").setViewName("mainManager");
        registry.addViewController("/mainMonitor.html").setViewName("mainMonitor");
        registry.addViewController("/mainMonitor").setViewName("mainMonitor");
        registry.addViewController("/sendPage1.html").setViewName("mainBigBoss");
        registry.addViewController("/mainBigBoss").setViewName("mainBigBoss");
//        registry.addViewController("/checkManager.html").setViewName("check");
//        registry.addViewController("/check").setViewName("check");
    }

    //注册自定义国际化功能组件LocaleResolver
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    //注册拦截器
    //springboot已经自动把静态资源映射排除在外了 所以不用另外处理
    //我去你妈的不用另外处理 版本不同 系统默认确实设置了静态资源 但是addPathPatterns("/**").我第一条就给它否了
    //系统默认访问到 classpath:/META-INF/resources/”, “classpath:/resources/”, “classpath:/static/”, “classpath:/public/”
    //所以把 "/asserts/**","/webjars/**"给我老老实实加上去
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/","/index","/index.html","/user/login","/asserts/**","/error/**","/webjars/**","/templates/error/**");

    }
}
