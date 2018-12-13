package com.chocorean.morecommands.exception;

public class NoHomeException extends MoreCommandsException{
    public NoHomeException(Object... objects) {
        super("You must be in the overworld to set your home.", objects);
    }
}
