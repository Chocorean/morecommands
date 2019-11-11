package chocorean.morecommands.exception;

import net.minecraft.command.CommandException;

public class MoreCommandsException extends CommandException {
    public MoreCommandsException(String message, Object... objects) {
        super(message, objects);
    }
}
