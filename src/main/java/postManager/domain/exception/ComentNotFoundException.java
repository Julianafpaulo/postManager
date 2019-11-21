package postManager.domain.exception;

public class ComentNotFoundException extends RuntimeException {


    private static final long serialVersionUID = 1869300553614629710L;

    public ComentNotFoundException() {
        super();
    }
    public ComentNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ComentNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
