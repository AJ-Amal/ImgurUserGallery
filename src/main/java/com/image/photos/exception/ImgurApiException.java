package com.image.photos.exception;

public class ImgurApiException extends RuntimeException{

    public ImgurApiException(String message) {
        super(message);
    }

    public ImgurApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
