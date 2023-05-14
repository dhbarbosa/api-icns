package com.br.icns.api.config.erros.dtos;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;

public record ErrorNotSuported(
        String message,
        String detail,
        int value,
        String string,
        String contextPath
) {

}
