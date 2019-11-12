package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class InvalidArgumentException extends MoreCommandsException {
    public InvalidArgumentException(String distance, Object... objects) {
        super(String.format(MoreCommands.localization.get("command.morecommands.killall.error"), distance), objects);
    }
}
