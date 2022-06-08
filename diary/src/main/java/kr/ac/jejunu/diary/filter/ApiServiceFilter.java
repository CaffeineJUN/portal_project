package kr.ac.jejunu.diary.filter;

import kr.ac.jejunu.diary.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// api 접근 필터링 기존 api 와는 다른 경로로 접근시 403에러
@Slf4j
public class ApiServiceFilter extends OncePerRequestFilter {
    private final String[] whitelist={"/","/api/login","/api/logout","/api/register"};
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if(isWhiteList(requestURI)){
            HttpSession session = request.getSession(false);
            User user = (User)session.getAttribute("user");
            if(user==null){
                response.sendError(403,"접근권한이 없습니다.");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private boolean isWhiteList(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}