package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class HomeWrongDimensionException extends MoreCommandsException {
    public HomeWrongDimensionException(Object... objects) {
        super(MoreCommands.localization.get("command.morecommands.home.dimension"), objects);
    }
}
