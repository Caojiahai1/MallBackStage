package com.mailbackstage.config;

import com.mailbackstage.common.exceptionhander.RestAuthenticationEntryPoint;
import com.mailbackstage.common.exceptionhander.RestfulAccessDeniedHandler;
import com.mailbackstage.common.mysecurity.AdminUserDetails;
import com.mailbackstage.common.mysecurity.JwtAuthenticationTokenFilter;
import com.mailbackstage.model.UmsAdmin;
import com.mailbackstage.model.UmsPermission;
import com.mailbackstage.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author Yan liang
 * @create 2019/6/8
 * @since 1.0.0
 * EnableGlobalMethodSecurity(prePostEnabled = true) 注解开启接口访问权限配置，不开启默认用户可以访问所有接口
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UmsAdminService umsAdminService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf() //由于使用了jwt，不需要csrf（跨域保护）
                .disable()
                .sessionManagement() //基于token，不需要session
                // Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                            "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css",
                            "/**/*.js", "/swagger-resources/**", "/v2/api-docs/**")
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                //测试放开
//                .antMatchers("/**")
//                .permitAll()
                //除上面外的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated();
        //禁用缓存
        httpSecurity.headers().cacheControl();
        //添加jwt Filter
        httpSecurity.addFilterBefore(getJwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsService())
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public JwtAuthenticationTokenFilter getJwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(username);
            if (umsAdmin != null) {
                List<UmsPermission> permissions = umsAdminService.getPermissionList(umsAdmin.getId());
                return new AdminUserDetails(umsAdmin, permissions);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}