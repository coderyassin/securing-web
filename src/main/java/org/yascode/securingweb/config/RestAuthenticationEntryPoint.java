package org.yascode.securingweb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.yascode.securingweb.exception.ErrorMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ErrorMessage errorMessage = ErrorMessage.builder()
                .statusCode(HttpServletResponse.SC_FORBIDDEN)
                .error("Unauthorized")
                .message(authException.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), bodyFromErrorMessage(errorMessage));
    }
    private Map<String, Object> bodyFromErrorMessage(ErrorMessage errorMessage) {
        Map<String, Object> body = new HashMap<>();
        body.put("StatusCode", errorMessage.getStatusCode());
        body.put("Error", errorMessage.getError());
        body.put("Message", errorMessage.getMessage());
        body.put("Path", errorMessage.getPath());
        body.put("Timestamp", errorMessage.getTimestamp().toString());
        return body;
    }
}
