package com.todoLb.security;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.todoLb.Document.User;
import com.todoLb.Services.UserService;
import com.todoLb.jwt.jwtHelper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AccessTokenFilter extends OncePerRequestFilter {
    @Autowired
    private jwtHelper jwtHelper;
    @Autowired
    private UserService userservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            Optional<String> accessToken = parseAccessToken(request);
            if (accessToken.isPresent() && jwtHelper.validateAccessToken(accessToken.get())) {
                String userId = jwtHelper.getUserIdFromAccessToken(accessToken.get());
                User user = userservice.findById(userId);
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        } catch (Exception e) {
            log.error("cannot set authentication");
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> parseAccessToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer")) {
            return Optional.of(authHeader.replace("Bearer", ""));
        }
        return Optional.empty();
    }

}
