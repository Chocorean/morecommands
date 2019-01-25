package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format(MoreCommands.getConfig().getMessageConfig().getPlayerNotFoundMessage(), username), objects);
    }
}
