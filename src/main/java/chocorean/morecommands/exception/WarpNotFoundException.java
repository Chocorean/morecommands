package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class WarpNotFoundException extends MoreCommandsException {
    public WarpNotFoundException(String message, Object... objects) {
        super(MoreCommands.localization.get("command.morecommands.warp.error"), objects);
    }
}
