package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.MoreCommands;

public class HomeWrongDimensionException extends MoreCommandsException {
    public HomeWrongDimensionException(Object... objects) {
        super("commands.morecommands.home.dimension", objects);
    }
}
