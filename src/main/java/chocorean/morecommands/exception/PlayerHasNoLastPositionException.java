package chocorean.morecommands.exception;

public class PlayerHasNoLastPositionException extends MoreCommandsException {
    public PlayerHasNoLastPositionException(Object... objects) {
        super("You have no previous location. This is a bug.", objects);
    }
}
