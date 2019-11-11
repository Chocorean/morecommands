package chocorean.morecommands.exception;


public class HomeNotFoundException extends MoreCommandsException {
    public HomeNotFoundException(Object... objects) {
        super("command.morecommands.home.error", objects);
    }
}
