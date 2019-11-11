package com.chocorean.morecommands.exception;


public class HomeNotFoundException extends MoreCommandsException {
    public HomeNotFoundException(Object... objects) {
        super("commands.morecommands.home.error", objects);
    }
}
