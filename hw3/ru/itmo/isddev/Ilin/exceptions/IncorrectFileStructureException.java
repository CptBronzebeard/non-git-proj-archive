package ru.itmo.isddev.Ilin.exceptions;
public class IncorrectFileStructureException extends RuntimeException { 
    public IncorrectFileStructureException(String message) {
        super(message);
    }
}