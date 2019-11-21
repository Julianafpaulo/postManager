package postManager.domain.exception;

public class UserNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }

    public UserNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
