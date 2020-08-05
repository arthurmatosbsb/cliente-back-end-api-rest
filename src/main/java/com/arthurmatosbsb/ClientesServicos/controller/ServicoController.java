package com.arthurmatosbsb.ClientesServicos.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.arthurmatosbsb.ClientesServicos.dto.ServicoDTO;
import com.arthurmatosbsb.ClientesServicos.models.Cliente;
import com.arthurmatosbsb.ClientesServicos.models.Servico;
import com.arthurmatosbsb.ClientesServicos.repository.ClienteRepository;
import com.arthurmatosbsb.ClientesServicos.repository.ServicoRepository;
import com.arthurmatosbsb.ClientesServicos.util.BigDecimalConverter;

@RestController
@RequestMapping("/servicos-prestados")
public class ServicoController {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private BigDecimalConverter bigDecimalConverter;

	  @PostMapping
	  @ResponseStatus(HttpStatus.CREATED)
	    public Servico salvar( @RequestBody @Valid ServicoDTO dto ){
	        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	        Integer idCliente = dto.getIdCliente();

	        Cliente cliente =
	                clienteRepository
	                        .findById(idCliente)
	                        .orElseThrow(() ->
	                                new ResponseStatusException(
	                                        HttpStatus.BAD_REQUEST, "Cliente inexistente."));


	        Servico servicoPrestado = new Servico();
	        servicoPrestado.setDescricao(dto.getDescricao());
	        servicoPrestado.setData( data );
	        servicoPrestado.setCliente(cliente);
	        servicoPrestado.setValor( bigDecimalConverter.converter(dto.getPreco())  );

	        return servicoRepository.save(servicoPrestado);
	    }
	  	@GetMapping
	    public List<Servico> pesquisar(
	            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
	            @RequestParam(value = "mes", required = false, defaultValue = "") Integer mes
	    ) {
	        return servicoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
	    }
}
