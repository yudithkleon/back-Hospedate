package com.example.hospedate;

import com.example.hospedate.service.UsuarioServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        String method = request.getMethod();

        return path.equals("/auth/login")
                || path.equals("auth/register")
                || (path.equals("/habitaciones") && method.equals("GET"));
    }


    @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
           throws ServletException, IOException {

       final String authorizationHeader = request.getHeader("Authorization");

       String correo = null;
       String jwt = null;

       if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           jwt = authorizationHeader.substring(7);
           System.out.println("TOKEN RECIBIDO: " + jwt);
           correo = jwtUtil.extractUsername(jwt);
       }

       if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

           UserDetails userDetails = userService.loadUserByUsername(correo);

           List<SimpleGrantedAuthority> authorities =
                   jwtUtil.extractRoles(jwt)
                           .stream()
                           .map(SimpleGrantedAuthority::new)
                           .toList();

           UsernamePasswordAuthenticationToken authToken =
                   new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

           SecurityContextHolder.getContext().setAuthentication(authToken);
       }

       chain.doFilter(request, response);
   }

}
