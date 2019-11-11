package chocorean.morecommands.exception;

public class SetspawnInvalidDimensionException extends MoreCommandsException {
    public SetspawnInvalidDimensionException(Object... objects) {
        super("commands.morecommands.setspawn.dimension", objects);
    }
}
