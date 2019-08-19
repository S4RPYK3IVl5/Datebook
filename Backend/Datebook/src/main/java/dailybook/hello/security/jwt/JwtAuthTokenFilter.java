package dailybook.hello.security.jwt;

import dailybook.hello.service.UserDetailServerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailServerImpl userDetailServer;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwt = gwtJwt(httpServletRequest);
            if (jwt != null && jwtProvider.validateJwtToken(jwt)){

                String username = jwtProvider.getUsernameFromJwtToken(jwt);

                UserDetails userDetails = userDetailServer.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }catch (Exception ex){
            logger.error("Can Not set user authentication -> ", ex);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String gwtJwt(HttpServletRequest httpServletRequest){

        String authHandler = httpServletRequest.getHeader("Authorization");

        if (authHandler != null && authHandler.startsWith("Bearer ")){
            return authHandler.replace("Bearer ", "");
        }

        return null;
    }

}
