package chocorean.morecommands.exception;

public class NoHomeException extends MoreCommandsException{
    public NoHomeException(Object... objects) {
        super("command.morecommands.home.error", objects);
    }
}
