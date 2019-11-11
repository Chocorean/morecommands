package chocorean.morecommands.exception;

public class NoHomeException extends MoreCommandsException{
    public NoHomeException(Object... objects) {
        super("commands.morecommands.home.error", objects);
    }
}
