package Util;

import java.io.OutputStream;
import java.util.logging.ConsoleHandler;

public class LoggerUtil$1 extends ConsoleHandler {
    protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
        super.setOutputStream(System.out);
    }

}
