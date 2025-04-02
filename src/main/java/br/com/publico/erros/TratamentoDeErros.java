package br.com.publico.erros;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.publico.dto.MensagemDeErro;
import br.com.publico.dto.ValidandoCampos;

@ControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MensagemDeErro>cepIncorreto(){
		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST, "CEP não existe !");
		return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro>idNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST, "ID não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
		
	}
	
	public ResponseEntity<?>validarCampos(MethodArgumentNotValidException ex){
		var validar = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(validar.stream().map(ValidandoCampos::new).toList());
	}
	
}
