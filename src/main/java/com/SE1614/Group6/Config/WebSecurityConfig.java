package com.SE1614.Group6.Config;

import com.SE1614.Group6.Model.Role;
import com.SE1614.Group6.Service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailService userDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**").permitAll()
                .antMatchers("/sign-up/**").permitAll()
                .antMatchers("/forgot_pass/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/", "/css/**", "/js/**", "/img/**", "/fonts/**", "/sass/**", "/Source/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/reset_password/**").permitAll()
                .antMatchers("/pages-profile/**").permitAll()
                .antMatchers("/blogs/**","/shop/**","/contact/**","/about/**","/product/**").permitAll()
                .antMatchers("/message","/user_avatar/**","/blog_image/**","/product_image/**","/blog-details/**","/blog/**").permitAll()

                .anyRequest()
                .authenticated().and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/dologin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/default")
                .failureUrl("/403")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
}