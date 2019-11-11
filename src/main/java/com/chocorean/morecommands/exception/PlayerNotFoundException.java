package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format("commands.morecommands.invsee.error", username), objects);
    }
}
