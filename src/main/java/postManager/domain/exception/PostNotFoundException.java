package postManager.domain.exception;

public class PostNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public PostNotFoundException() {
        super();
    }
    public PostNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PostNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

