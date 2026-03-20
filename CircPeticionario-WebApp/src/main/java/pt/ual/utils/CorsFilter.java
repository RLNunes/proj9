 package pt.ual.utils;

 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.annotation.WebFilter;
 import javax.servlet.http.HttpServletResponse;

 @WebFilter({"/*"})
 public class CorsFilter
   implements Filter {
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
     HttpServletResponse httpResponse = (HttpServletResponse)response;

     httpResponse.setHeader("Access-Control-Allow-Origin", "*");
     httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
     httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");

     chain.doFilter(request, response);
   }

   public void init(FilterConfig filterConfig) throws ServletException {}

   public void destroy() {}
 }