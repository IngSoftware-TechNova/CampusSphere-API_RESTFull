package com.technova.campussphereapi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technova.campussphereapi.exception.CustomErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Este metodo se ejecuta cuando un usuario no autenticado intenta acceder a un recurso que requiere autenticacion
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Todo: Obtener el mensaje de error si es que existe, de lo contrario establecer un mensaje por defecto
        String exeptionMsg = (String) request.getAttribute("exception");

        if (exeptionMsg != null) {
            exeptionMsg = "Token not found or invalid"; //Mensaje de error por defecto
        }

        // Todo: Crear un objeto de error personalizado con la fecha, mensaje de error y la URL de la solicitud
        CustomErrorResponse errorResponse = new CustomErrorResponse(LocalDateTime.now(), exeptionMsg, request.getRequestURI());

        // Todo: Establecer el estado HTTP de respuesta como "401 Unauthorized"
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Todo: Escribir la respuesta de error en formato JSON en el cuerpo de la respuesta
        response.getWriter().write(convertObjectToJson(errorResponse));

        // Todo: Establecer el tipo de contenido de la respuesta como JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        //Todo: Convertir el objeto en una cadena de JSON utilizando ObjectMapper de Jackson
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules(); // Esto registra modulos adicionales si es necesario (por ejemplo soporte de fechas)
        return mapper.writeValueAsString(object); // Devolver el objeto convertido a JSON
    }
}
