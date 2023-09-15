package com.feihu1024.panserver.config;


import com.feihu1024.panserver.oauth2.CustomAuthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Resource服务核心配置
 */

@Configuration
@EnableResourceServer

public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "pan-server";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private CustomAuthEntryPoint customAuthEntryPoint;

//    @Bean
//    public ResourceServerTokenServices resourceServerTokenServices(){
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:3000/oauth/check_token");
//        remoteTokenServices.setClientId("pan-server-web");
//        remoteTokenServices.setClientSecret(new CustomMD5PasswordEncoder().encode("secret"));
//        return remoteTokenServices;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                .stateless(true)
                .authenticationEntryPoint(customAuthEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/pan-server/**").access("#oauth2.hasScope('all')")
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }
}
