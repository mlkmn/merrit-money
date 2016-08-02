package pl.mlkmn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.mlkmn.enums.Page;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/login", "/error").permitAll()
            .antMatchers("/" + Page.ADMIN_PANEL).access("hasRole('admin')")
            .antMatchers("/" + Page.DASHBOARD).access("hasRole('admin') or hasRole('mod') or hasRole('user')")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/" + Page.LOGIN.getName())
            .usernameParameter("login")
            .passwordParameter("password")
            .successForwardUrl("/" + Page.DASHBOARD)
            .permitAll()
            .and()
        .csrf()
            .and()
        .logout()
            .permitAll()
            .and()
        .exceptionHandling()
            .accessDeniedPage("/" + Page.ACCESS_DENIED);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
    
}
