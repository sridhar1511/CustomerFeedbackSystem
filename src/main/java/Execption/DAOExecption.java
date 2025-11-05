package Execption;

public class DAOExecption extends RuntimeException{
    public DAOExecption() {
    }

    public DAOExecption(String message, Throwable cause) {
        super(message, cause);
    }
}

