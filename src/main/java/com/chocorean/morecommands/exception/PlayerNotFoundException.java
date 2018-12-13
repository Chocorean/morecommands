package com.chocorean.morecommands.exception;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format("Player %s has not been found.", username), objects);
    }
}
