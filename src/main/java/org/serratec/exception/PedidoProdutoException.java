package org.serratec.exception;

public class PedidoProdutoException extends RuntimeException{

    public PedidoProdutoException(String message) {
        super(message);
    }

    public PedidoProdutoException(String message, Throwable cause) {
        super(message, cause);
    }

}
