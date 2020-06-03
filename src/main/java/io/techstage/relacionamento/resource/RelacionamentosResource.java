package io.techstage.relacionamento.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.techstage.relacionamento.model.Relacionamento;
import io.techstage.relacionamento.repository.Relacionamentos;

@CrossOrigin
@RestController	
@RequestMapping("/relacionamento")
public class RelacionamentosResource {
  
	@Autowired
	private Relacionamentos relacionamentos;
	
	@CrossOrigin
	@PostMapping
	public Relacionamento adicionar(@Valid @RequestBody Relacionamento relacionamento) {
		return relacionamentos.save(relacionamento);
	}
	
	@CrossOrigin
	@GetMapping
	public List<Relacionamento> listar() {
		return relacionamentos.findAll();
	}
  
	@CrossOrigin
	@GetMapping("/{id}")
	public ResponseEntity<Relacionamento> buscar(@PathVariable Long id) {
    Relacionamento relacionamento = relacionamentos.findById(id).get();
    
		if (relacionamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(relacionamento);
	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<Relacionamento> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Relacionamento relacionamento) {
				Relacionamento existente = relacionamentos.findById(id).get();
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(relacionamento, existente, "id");
		
		existente = relacionamentos.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@CrossOrigin
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Relacionamento relacionamento = relacionamentos.findById(id).get();
		
		if (relacionamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		relacionamentos.delete(relacionamento);
		
		return ResponseEntity.noContent().build();
	}
}