package chocorean.morecommands.exception;

public class HomeWrongDimensionException extends MoreCommandsException {
    public HomeWrongDimensionException(Object... objects) {
        super("command.morecommands.home.dimension", objects);
    }
}
