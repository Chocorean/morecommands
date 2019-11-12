package chocorean.morecommands.exception;

import chocorean.morecommands.MoreCommands;

public class InvalidNumberOfArgumentsException extends MoreCommandsException{
    public InvalidNumberOfArgumentsException(Object... objects) {
        super(MoreCommands.localization.get("morecommandsmisc.invalid_number_arguments"), objects);
    }
}
