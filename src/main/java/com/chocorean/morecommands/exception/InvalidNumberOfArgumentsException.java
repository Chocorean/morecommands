package com.chocorean.morecommands.exception;

public class InvalidNumberOfArgumentsException extends MoreCommandsException{
    public InvalidNumberOfArgumentsException(Object... objects) {
        super("Invalid number of arguments.", objects);
    }
}
