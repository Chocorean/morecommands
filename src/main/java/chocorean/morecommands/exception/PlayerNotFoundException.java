package chocorean.morecommands.exception;

public class PlayerNotFoundException extends MoreCommandsException {
    public PlayerNotFoundException(String username, Object... objects) {
        super(String.format("commands.morecommands.invsee.error", username), objects);
    }
}
