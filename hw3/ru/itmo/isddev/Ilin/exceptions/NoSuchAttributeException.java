package ru.itmo.isddev.Ilin.exceptions;
public class NoSuchAttributeException extends RuntimeException { 
    public NoSuchAttributeException(String secName, String attrName) {
        super("Attribute " + attrName + " in section " + secName +" doesn't exist");
    }
}