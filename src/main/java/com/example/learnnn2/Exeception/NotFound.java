package com.example.learnnn2.Exeception;

public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
