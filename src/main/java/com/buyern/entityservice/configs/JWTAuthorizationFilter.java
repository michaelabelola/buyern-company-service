package com.buyern.entityservice.configs;

import com.buyern.entityservice.authorization.CustomAuthority;
import com.buyern.entityservice.authorization.Permission;
import com.buyern.entityservice.authorization.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";
    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader(HEADER_STRING);
//        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    // Reads the JWT from the Authorization header, and then uses JWT to validate the token
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
//        String token = request.getHeader(HEADER_STRING);
//        if (token != null) {
//            String userId = null;
//            try {
//                userId = JWT.require(Algorithm.HMAC512(secret.getBytes()))
//                        .build()
//                        .verify(token.replace(TOKEN_PREFIX, ""))
//                        .getSubject();
        //TODO: check if token type in token header is an entity token
        UserSession userSession = new UserSession();
        userSession.setId(1);
        userSession.setUid("userUid");
        userSession.setLastName("Abel");
        userSession.setFirstName("Michael");
        //TODO: get authorities from jwt
        List<CustomAuthority> authorities = List.of(
                new CustomAuthority(23L, 1L, Permission.CREATE),
                new CustomAuthority(23L, 2L, Permission.ACCOUNT_PREFERENCES),
                new CustomAuthority(23L, 3L, Permission.ADD),
                new CustomAuthority(23L, 4L, Permission.DELETE),
                new CustomAuthority(23L, 4L, Permission.ASSIGN)
        );
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userSession, null, authorities);
        authToken.setDetails(userSession);
        return authToken;
////                return new UsernamePasswordAuthenticationToken(userId, null, userSession.getAuthorities());
//                return new UsernamePasswordAuthenticationToken(userId, null, null);
//            } catch (Exception ex) {
//                return null;
//            }
//        } else
//            try {
//                response.sendError(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, "Invalid Token");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        setResponse(response, "Error 2");
//        return null;
    }

//    public void setResponse(HttpServletResponse response, String message) throws IOException {
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(bos);
//        oos.writeObject(message);
//        oos.flush();
//        response.getOutputStream().write(bos.toByteArray());
//    }

}
