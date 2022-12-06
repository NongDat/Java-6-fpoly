//package com.example.j6demo7_csdl_security.service;
//
//import com.example.j6demo7_csdl_security.dao.AccountDAO;
//import com.example.j6demo7_csdl_security.entity.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService implements UserDetailsService {
//    @Autowired
//    AccountDAO accountDAO;
//    @Autowired
//     BCryptPasswordEncoder pe;
//    @Autowired
//    HttpServletRequest req;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            Account account = accountDAO.findById(username).get();
//            // Tao userDetails từ Account
//            System.out.println(account);
//            System.out.println(req.getParameter("username") + " " + req.getParameter("password"));
//            String password = pe.encode(account.getPassword());
//            String[] roles = account.getAuthorities().stream()
//                    .map(au -> au.getRole().getId())
//                    .collect(Collectors.toList()).toArray(new String[0]);
//            System.out.println(account.getPassword()  + " : " +password );
//            return User.withUsername(username)
//                    .password(password)
//                    .roles(roles)
//                    .build();
//        } catch (Exception e) {
//            throw new UsernameNotFoundException(username + "not found!");
//        }
//    }
//}
