package it.epicode.energy.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException (String message) {
        super(message);
    }
}
