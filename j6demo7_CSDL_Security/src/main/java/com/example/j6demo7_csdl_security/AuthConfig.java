package com.example.j6demo7_csdl_security;

import com.example.j6demo7_csdl_security.dao.AccountDAO;
import com.example.j6demo7_csdl_security.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

//    Mã Hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     *  QUản Lý người dữ liệu ngươi sử dụng
     *  tìm cách xây dựng ra nguồn dữ liệu để bỏ vào trong  "auth"
     */
//    @Autowired
//    UserService userService;
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    BCryptPasswordEncoder pe;
    @Autowired
    HttpServletRequest req;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account account = accountDAO.findById(username).get();
                // Tao userDetails từ Account
                String password = pe.encode(account.getPassword());
                String[] roles = account.getAuthorities().stream()
                        .map(au -> au.getRole().getId())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username)
                        .password(password)
                        .roles(roles)
                        .build();
            } catch (Exception e) {
                throw new UsernameNotFoundException(username + "not found!");
            }
        });
    }

    /**
     *  Phân quyền sử dụng và hình thức đăng nhập
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
//      CSRF,: chia sẻ tài nguyên từ bên ngoài
//      CORS : truy cập từ bên ngoài, domain => vô hiệu hóa
        http.csrf().disable().cors().disable();

        // Phân quyền sử dụng
        http.authorizeRequests()
                .antMatchers("/home/admins").hasRole("DIRE") // trang cho phép Admin truy cập
                .antMatchers("/home/users").hasAnyRole("DIRE", "STAF")
                .antMatchers("/home/authenticated").authenticated() // bắt buộc phải đăng nhập k cần biết vai trò gì
                .anyRequest().permitAll();// all trang còn lại cho phép truy cập

//        Khi đăng nhập rồi mà truy cập tới đường link không được phép thì sao>?
//         cần có xử lí điều khiển
//        Điều Khiển Lỗi Truy Cập Không Đúng Vai Trò
        http.exceptionHandling()
                        .accessDeniedPage("/auth/access/denied"); // [/error]

//         Giao diện đăng nhập
//        http.httpBasic(); // giao diện mặc định

//        Giao diện của ta
        http.formLogin()
                .loginPage("/auth/login/form") // địa chỉ của form
                .loginProcessingUrl("/auth/login") // submit tới chỗ nào? mặc định là [login]
                .defaultSuccessUrl("/auth/login/success", false)// sau khi đăng nhập thành công thì tới đâu?
//               False : nghĩa là k nhất thiết sau khi success phải về trang "/auth/login/success" .
//                true: mặc định sau khi success thì tới trang "/auth/login/success" .
//                Giả sử : user muốn vào trang about thì sau khi success xong thì nó trở ra trang about
                .failureUrl("/auth/login/error")// nếu fail login thì quay về trang này
//                trong html có username & password chúng ta bỏ 2 dòng dưới cũng dc
                .usernameParameter("username")
                .passwordParameter("password");

        http.rememberMe()
                .rememberMeParameter("remember"); // tên mặc định là remember-me
//         đăng xuất
        http.logout()
                .logoutUrl("/auth/logoff")// mặc định
                .logoutSuccessUrl("/auth/logoff/success"); // sau khi logout thành công sẽ chuyển tới địa chỉ này

        //OAuth2 - Dang nhap mang xa hoi
        http.oauth2Login().loginPage("/auth/login/form")
                .defaultSuccessUrl("/oauth2/login/success", true)
                .failureUrl("/auth/login/error")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
    }
}
