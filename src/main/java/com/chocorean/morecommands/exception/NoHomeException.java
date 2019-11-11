package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class NoHomeException extends MoreCommandsException{
    public NoHomeException(Object... objects) {
        super("commands.morecommands.home.error", objects);
    }
}
