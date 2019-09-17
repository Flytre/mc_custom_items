package com.flytre;

public class InvalidItemException extends Exception {
    public InvalidItemException(String param, String val) {
        super("Invalid " + param + ": " + val);
    }

}
