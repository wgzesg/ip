package exceptions;

import commands.Command;

public class NullArgumentException extends RuntimeException {

    public NullArgumentException(String msg) {
        super(msg);
    }
}
