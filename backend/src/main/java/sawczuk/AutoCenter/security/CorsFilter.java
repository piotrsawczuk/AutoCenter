package sawczuk.AutoCenter.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
    private static final int ACCESS_CONTROL_MAX_AGE_IN_SECONDS = 3600;

    @Value("${security.cors.client-app-url}")
    private String clientAppUrl;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, clientAppUrl);
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.setHeader(
                HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                String.join(", ", Arrays.asList(
                        HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE)
                ));
        response.addHeader(
                HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS,
                String.join(", ", Arrays.asList(
                        HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(), HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name())
                ));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, String.valueOf(ACCESS_CONTROL_MAX_AGE_IN_SECONDS));

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

}
