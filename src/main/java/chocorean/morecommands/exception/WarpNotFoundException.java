package chocorean.morecommands.exception;

public class WarpNotFoundException extends MoreCommandsException {
    public WarpNotFoundException(String message, Object... objects) {
        super("command.morecommands.warp.error", objects);
    }
}
