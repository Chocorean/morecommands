package chocorean.morecommands.exception;


import chocorean.morecommands.MoreCommands;

public class HomeNotFoundException extends MoreCommandsException {
    public HomeNotFoundException(Object... objects) {
        super(MoreCommands.localization.get("command.morecommands.home.error"), objects);
    }
}
