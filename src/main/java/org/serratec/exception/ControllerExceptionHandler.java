package org.serratec.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
  
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request){       
        LocalDateTime dataHora = LocalDateTime.now();

        List<String> errors = new ArrayList<String>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ErroResposta erroResposta = new ErroResposta(status.value(),
        "Existem campos inválidos", 
        dataHora,errors);
        
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, 
    HttpHeaders headers, HttpStatus status, WebRequest request) {
        LocalDateTime dataHora = LocalDateTime.now();
    
        List<String> errors = new ArrayList<>();

        Throwable mostSpecificCause = ex.getMostSpecificCause();
        ErroResposta erroResposta;

        if (mostSpecificCause != null) {
            String exceptionName = mostSpecificCause.getClass().getName();
            String message = mostSpecificCause.getMessage();
            errors.add(message);
            erroResposta = new ErroResposta(status.value(), exceptionName, dataHora, errors);
        } else {
            erroResposta = new ErroResposta(status.value(), ex.getMessage(), dataHora);
        }
        return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
    }
    
    //CUSTOM NOT FOUND EXCEPTION
    @ExceptionHandler(CustomNotFoundException.class)
    protected ResponseEntity<Object> handleCustomNotFoundException(CustomNotFoundException ex){   
        CustomNotFoundException customNotFoundException= new CustomNotFoundException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customNotFoundException.getLocalizedMessage());
    }

    //CUSTOM NO CONTENT EXCEPTION
    @ExceptionHandler(CustomNoContentException.class)
    protected ResponseEntity<Object> handleCustomNoContentException(CustomNoContentException ex){   
        CustomNoContentException customNoContentException = new CustomNoContentException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(customNoContentException.getLocalizedMessage());
    }

    //EMAIL EXCEPTION
    @ExceptionHandler(EmailException.class)
    protected ResponseEntity<Object> handleEmailException(EmailException ex){
        EmailException emailException = new EmailException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emailException.getLocalizedMessage());
    }

    //CPF EXCEPTION
    @ExceptionHandler(CpfException.class)
    protected ResponseEntity<Object> handleCpfException(CpfException ex){
        CpfException cpfException = new CpfException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cpfException.getLocalizedMessage());
    }

    //CATEGORIA EXCEPTION
    @ExceptionHandler(CategoriaException.class)
    protected ResponseEntity<Object> handleCategoriaException(CategoriaException ex){
        CategoriaException categoriaException = new CategoriaException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categoriaException.getLocalizedMessage());
    }

    //CLIENTE EXCEPTION
    @ExceptionHandler(ClienteException.class)
    protected ResponseEntity<Object> handleClienteException(ClienteException ex){
        ClienteException clienteException = new ClienteException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(clienteException.getLocalizedMessage());
    }

    //PRODUTO EXCEPTION
    @ExceptionHandler(ProdutoException.class)
    protected ResponseEntity<Object> handleProdutoException(ProdutoException ex){
        ProdutoException produtoException = new ProdutoException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(produtoException.getLocalizedMessage());
    }

    //PEDIDO EXCEPTION
    @ExceptionHandler(PedidoException.class)
    protected ResponseEntity<Object> handleProdutoException(PedidoException ex){
        PedidoException pedidoException = new PedidoException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pedidoException.getLocalizedMessage());
    }

    //PEDIDO PRODUTO EXCEPTION
    @ExceptionHandler(PedidoProdutoException.class)
    protected ResponseEntity<Object> handleProdutoException(PedidoProdutoException ex){
        PedidoProdutoException pedidoProdutoException = new PedidoProdutoException(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pedidoProdutoException.getLocalizedMessage());
    }


}
    
