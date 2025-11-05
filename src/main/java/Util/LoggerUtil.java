package Util;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class LoggerUtil {
    private static final Logger log = Logger.getLogger(LoggerUtil.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("app.log", true);
            fh.setFormatter(new SimpleFormatter());
            fh.setLevel(Level.ALL);
            fh.setEncoding("UTF-8");
            ConsoleHandler ch = new ConsoleHandler() {
                protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
                    super.setOutputStream(System.out);
                }
            };
            ch.setFormatter(new SimpleFormatter());
            ch.setLevel(Level.ALL);
            log.addHandler(fh);
            log.addHandler(ch);
            log.setUseParentHandlers(false);
            log.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Logger setup failed: " + e.getMessage());
        }

    }

    public static Logger getLogger() {
        return log;
    }
}
