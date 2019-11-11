package chocorean.morecommands.exception;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format("command.morecommands.invsee.error", username), objects);
    }
}
