package org.yascode.securingweb.exception;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

}
