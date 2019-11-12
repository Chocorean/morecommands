package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class SetspawnInvalidDimensionException extends MoreCommandsException {
    public SetspawnInvalidDimensionException(Object... objects) {
        super(MoreCommands.localization.get("command.morecommands.setspawn.dimension"), objects);
    }
}
