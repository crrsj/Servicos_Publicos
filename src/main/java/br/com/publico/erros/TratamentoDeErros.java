package br.com.publico.erros;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.publico.dto.MensagemDeErro;

@ControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MensagemDeErro>cepIncorreto(){
		var erros = new MensagemDeErro(HttpStatus.BAD_REQUEST, "CEP não existe !");
		return new ResponseEntity<>(erros,HttpStatus.BAD_REQUEST);
		
	}

	
}
