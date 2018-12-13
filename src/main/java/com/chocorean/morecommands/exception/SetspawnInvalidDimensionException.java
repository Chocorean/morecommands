package com.chocorean.morecommands.exception;

import com.chocorean.morecommands.exception.MoreCommandsException;

public class SetspawnInvalidDimensionException extends MoreCommandsException {
    public SetspawnInvalidDimensionException(Object... objects) {
        super("You must be in the overworld to set the spawn point.", objects);
    }
}
