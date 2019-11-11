package chocorean.morecommands.exception;

public class InvalidArgumentException extends MoreCommandsException {
    public InvalidArgumentException(String distance, Object... objects) {
        super(String.format("command.morecommands.killall.error", distance), objects);
    }
}
