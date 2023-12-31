package com.rekindle.book.store.domain.application;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

//TODO dirty workaround until more robust jwt verification is implemented on service-side
public class AuthorizationPreFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (!isPathAllowed(httpRequest.getServletPath())) {
      List<String> list = Collections.list(httpRequest.getHeaderNames());
      if (!list.contains("authorization")) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
            "Unable to authenticate Domain User with provided credentials");
      } else {
        chain.doFilter(request, response);
      }
    } else {
      chain.doFilter(request, response);
    }
  }

  private boolean isPathAllowed(String path) {
    return path.startsWith("/actuator") || path.startsWith("/v3/api-docs") || path.startsWith(
        "/v2/api-docs") || path.startsWith("/swagger-ui") || path.startsWith("/swagger-resources");
  }

  @Override
  public void destroy() {
  }

}
