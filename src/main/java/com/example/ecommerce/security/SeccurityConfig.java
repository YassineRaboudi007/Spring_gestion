package com.example.ecommerce.security;

import com.example.ecommerce.auth.AuthFilter;
import com.example.ecommerce.auth.AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SeccurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthFilter filter = new AuthFilter(authenticationManagerBean());
        filter.setFilterProcessesUrl("/api/login");
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/api/login/**").permitAll().and()
                //.authorizeRequests().antMatchers("/api/admin/**").hasAnyAuthority("admin").and()
                //.authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority("user").and()
                .authorizeRequests().antMatchers("/api/**").permitAll().and()
                .authorizeRequests().anyRequest().authenticated()
                .and().formLogin();

        http.addFilter(filter);
        http.addFilterBefore(new AuthorizationFilter(),AuthFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
