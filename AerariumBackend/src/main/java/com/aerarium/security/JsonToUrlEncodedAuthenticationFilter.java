package com.aerarium.security;

import com.aerarium.setting.CustomRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * The following class intend to apply a filter at authentication
 * routes to allow the Spring OAuth2 framework to handle a json
 * body at token requests. The same is required as the Nebular
 * toolkit used for Angular applications creates only application/json
 * content for refresh tokens requisitions.
 *
 * @author  Nicholas Checan
 * @version 1.0
 * @since   2019-11-13
 */

@Component
@Order(value = Integer.MIN_VALUE)
public class JsonToUrlEncodedAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        if (Objects.equals(request.getContentType(), "application/json")
                && Objects.equals(((RequestFacade) request).getServletPath(), "/oauth/token")) {

            InputStream binaryBody = request.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[16384];
            int nRead;

            while ((nRead = binaryBody.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();
            byte[] jsonType = buffer.toByteArray();

            @SuppressWarnings("unchecked")
            HashMap<String, String> jsonValues = new ObjectMapper().readValue(jsonType, HashMap.class);
            HashMap<String, String[]> requestParams = new HashMap<>();

            for (String key : jsonValues.keySet()) {
                String[] val = new String[1];
                val[0] = jsonValues.get(key);
                requestParams.put(key, val);
            }

            String[] val = new String[1];
            val[0] = ((RequestFacade) request).getMethod();
            requestParams.put("_method", val);
            HttpServletRequest newRequest =
                    new CustomRequestWrapper(((HttpServletRequest) request), requestParams);
            chain.doFilter(newRequest, response);

        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
    }
}
