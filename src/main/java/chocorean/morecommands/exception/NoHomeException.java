package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class NoHomeException extends MoreCommandsException{
    public NoHomeException(Object... objects) {
        super(MoreCommands.localization.get("command.morecommands.home.error"), objects);
    }
}
