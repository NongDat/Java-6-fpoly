//package com.example.j6demo6_1;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//// cho sử dụng anotation trong controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class AuthConfig_Anotation extends WebSecurityConfigurerAdapter {
//
//    //    Mã Hóa mật khẩu
//    @Bean
//    public BCryptPasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     *  QUản Lý người dữ liệu ngươi sử dụng
//     *  tìm cách xây dựng ra nguồn dữ liệu để bỏ vào trong  "auth"
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(pe.encode("123")).roles("GUEST")
//                .and()
//                .withUser("user2").password(pe.encode("123")).roles("USER","GUEST")
//                .and()
//                .withUser("user3").password(pe.encode("123")).roles("ADMIN","USER","GUEST");
//    }
//
//    /**
//     *                   Phân quyền sử dụng và hình thức đăng nhập
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
////      CSRF,: chia sẻ tài nguyên từ bên ngoài
////      CORS : truy cập từ bên ngoài, domain => vô hiệu hóa
//        http.csrf().disable().cors().disable();
//
//        // Phân quyền sử dụng
//        http.authorizeRequests()
//                .anyRequest().permitAll();// all trang còn lại cho phép truy cập
//
////        Khi đăng nhập rồi mà truy cập tới đường link không được phép thì sao>?
////         cần có xử lí điều khiển
////        Điều Khiển Lỗi Truy Cập Không Đúng Vai Trò
//        http.exceptionHandling()
//                .accessDeniedPage("/auth/access/denied"); // [/error]
//
////         Giao diện đăng nhập
////        http.httpBasic(); // giao diện mặc định
//
////        Giao diện của ta
//        http.formLogin()
//                .loginPage("/auth/login/form") // địa chỉ của form
//                .loginProcessingUrl("/auth/login") // submit tới chỗ nào? mặc định là [login]
//                .defaultSuccessUrl("/auth/login/success", false)// sau khi đăng nhập thành công thì tới đâu?
////               False : nghĩa là k nhất thiết sau khi success phải về trang "/auth/login/success" .
////                true: mặc định sau khi success thì tới trang "/auth/login/success" .
////                Giả sử : user muốn vào trang about thì sau khi success xong thì nó trở ra trang about
//                .failureUrl("/auth/login/error")// nếu fail login thì quay về trang này
////                trong html có username & password chúng ta bỏ 2 dòng dưới cũng dc
//                .usernameParameter("username")
//                .passwordParameter("password");
//
//        http.rememberMe()
//                .rememberMeParameter("remember"); // tên mặc định là remember-me
////         đăng xuất
//        http.logout()
//                .logoutUrl("/auth/logoff")// mặc định
//                .logoutSuccessUrl("/auth/logoff/success"); // sau khi logout thành công sẽ chuyển tới địa chỉ này
//    }
//}
