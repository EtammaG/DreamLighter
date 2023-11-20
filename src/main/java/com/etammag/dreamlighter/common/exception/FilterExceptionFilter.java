package com.etammag.dreamlighter.common.exception;

import com.alibaba.fastjson.JSON;
import com.etammag.dreamlighter.common.entity.Result;
import com.etammag.dreamlighter.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class FilterExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            log.warn(e.getMessage());
            WebUtils.renderString(response, JSON.toJSONString(Result.error(e.getMessage())));
        }
    }
}
