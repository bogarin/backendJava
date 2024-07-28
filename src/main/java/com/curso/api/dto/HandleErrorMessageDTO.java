package com.curso.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class HandleErrorMessageDTO implements Serializable{
    private String  backendMessage;
    private String url;
    private String method;
    private String message;
    private LocalDateTime timestamp;
}
