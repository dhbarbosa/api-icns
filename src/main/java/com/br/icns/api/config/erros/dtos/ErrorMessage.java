package com.br.icns.api.config.erros.dtos;

import java.util.Date;

public record ErrorMessage(
        Date timestamp,
        int status,
        String Conflict
) {

}
