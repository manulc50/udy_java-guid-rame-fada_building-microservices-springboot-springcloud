package net.mlorenzo.springbootrestfulws.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class ErrorDetails {

    private final LocalDateTime timestamp;
    private final String message;
    private final String path;
    private final String errorCode;
}
