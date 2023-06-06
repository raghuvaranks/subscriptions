package com.fs.subscriptions.subscriptions.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private String fieldName;
    private LocalDateTime timestamp;
}