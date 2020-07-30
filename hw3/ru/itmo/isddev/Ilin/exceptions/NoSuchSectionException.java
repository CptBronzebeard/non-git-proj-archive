package ru.itmo.isddev.Ilin.exceptions;
public class NoSuchSectionException extends RuntimeException { 
    public NoSuchSectionException(String fileName, String secName) {
        super("Section " + secName + " in file " + fileName +" doesn't exist");
    }
}