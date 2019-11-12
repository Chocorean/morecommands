package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format(MoreCommands.localization.get("command.morecommands.invsee.error"), username), objects);
    }
}
