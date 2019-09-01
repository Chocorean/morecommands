package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class InvalidArgumentException extends MoreCommandsException {
    public InvalidArgumentException(String distance, Object... objects) {
        super(String.format(MoreCommands.getConfig().getMessageConfig().getInvalidDistanceMessage(), distance), objects);
    }
}
