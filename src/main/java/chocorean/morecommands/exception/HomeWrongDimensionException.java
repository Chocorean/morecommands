package chocorean.morecommands.exception;

public class HomeWrongDimensionException extends MoreCommandsException {
    public HomeWrongDimensionException(Object... objects) {
        super("commands.morecommands.home.dimension", objects);
    }
}
