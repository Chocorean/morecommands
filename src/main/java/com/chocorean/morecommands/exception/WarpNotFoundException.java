package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class WarpNotFoundException extends MoreCommandsException {
    public WarpNotFoundException(String message, Object... objects) {
        super(MoreCommands.getConfig().getMessageConfig().getWarpNotFoundMessage(), objects);
    }
}
