package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class InvalidGamemodeException extends MoreCommandsException {
    public InvalidGamemodeException(String gamemode, Object... objects) {
        super(String.format(MoreCommands.getConfig().getMessageConfig().getInvalidGamemodeMessage(), gamemode), objects);
    }
}
