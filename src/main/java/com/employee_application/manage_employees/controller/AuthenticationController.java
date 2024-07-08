//package com.employee_application.manage_employees.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.employee_application.manage_employees.model.AuthenticationRequest;
//import com.employee_application.manage_employees.security.util.JwtUtil;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@Controller
//public class AuthenticationController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired 
//    private UserDetailsService userDetailsService;
//
//    @Autowired 
//    private JwtUtil jwtTokenUtil;
//
//    @PostMapping("/authenticate")
//    public String createAuthenticationToken(
//    			HttpSession session,
//            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
//
//        System.out.println("Authentication attempt for user: " + authenticationRequest.getUsername());
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//        } catch (Exception ex) {
//            session.setAttribute("msg", "Bad credentials");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // Set 401 Unauthorized status
//            return "redirect:/login";
//        }
//
//        try {
//            final UserDetails userDetails = userDetailsService
//                    .loadUserByUsername(authenticationRequest.getUsername());
//
//            final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//            Cookie cookie = new Cookie("jwt", jwt);
//            cookie.setMaxAge(Integer.MAX_VALUE);
//            response.addCookie(cookie);
//
//            response.setStatus(HttpServletResponse.SC_OK);  // Set 200 OK status
//            return jwt;
//        } catch (Exception e) {
//            session.setAttribute("msg", "Credentials were right but something went wrong!!");
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);  // Set 500 Internal Server Error status
//            return "redirect:/login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse res, Model m, HttpSession session) {
//        String msg = null;
//
//        Cookie[] cookies2 = request.getCookies();
//        for (int i = 0; i < cookies2.length; i++) {
//            if (cookies2[i].getName().equals("jwt")) {
//                cookies2[i].setMaxAge(0);
//                res.addCookie(cookies2[i]);
//                msg = "Logout Successful";
//            }
//        }
//        session.setAttribute("msg", msg);
//        return "redirect:/login";
//    }
//}
