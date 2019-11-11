package chocorean.morecommands.exception;

public class SetspawnInvalidDimensionException extends MoreCommandsException {
    public SetspawnInvalidDimensionException(Object... objects) {
        super("command.morecommands.setspawn.dimension", objects);
    }
}
