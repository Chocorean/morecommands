package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class HomeNotFoundException extends MoreCommandsException {
    public HomeNotFoundException(Object... objects) {
        super(MoreCommands.getConfig().getMessageConfig().getHomeNotFoundMessage(), objects);
    }
}
