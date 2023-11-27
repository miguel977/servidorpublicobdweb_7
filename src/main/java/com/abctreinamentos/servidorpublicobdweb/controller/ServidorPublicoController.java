package com.abctreinamentos.servidorpublicobdweb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.abctreinamentos.servidorpublicobdweb.entity.ServidorPublico;
import com.abctreinamentos.servidorpublicobdweb.service.ServidorPublicoService;

@Controller
public class ServidorPublicoController {
	
	private ServidorPublicoService servidorService;
	
	@Autowired
	public void setServidorPublicoService(ServidorPublicoService servidorService)
	{
		this.servidorService = servidorService;
	}
	
	@GetMapping("/listarServidores")
	public String listarServidores(Model model)
	{
		model.addAttribute("servidorespublicos",servidorService.listAll());
		return "servidorespublicos";
	}

	@GetMapping("/listarServidor/{matricula}")
	public String listarServidor(@PathVariable long matricula, Model model)
	{
		model.addAttribute("servidorpublico",servidorService.listByMatricula(matricula).get());
		return "servidorpublico";
	}
	
	@GetMapping("/excluirServidor/{matricula}")
	public String excluirServidor(@PathVariable long matricula)
	{
		servidorService.delete(matricula);
		return "redirect:/listarServidores";
	}
	
	@GetMapping("/formularioEditarServidor/{matricula}")
	public String formEditarServidor(@PathVariable long matricula, Model model)
	{
		Optional<ServidorPublico> servidorEncontrado = servidorService.listByMatricula(matricula);
		model.addAttribute("servidorPublico",servidorEncontrado);
		return "editarservidorpublico";
	}
	
	@PostMapping("/editarServidor/{matricula}")
	public String editarServidor(@ModelAttribute ServidorPublico servidor)
	{
		servidorService.update(servidor);
		return "redirect:/listarServidores";
	}
	
	@GetMapping("/formularioNovoServidor")
	public String formNovoServidor(Model model)
	{
		model.addAttribute("servidorPublico",new ServidorPublico());
		return "novoservidorpublico";
	}
	
	@PostMapping("/cadastrarServidor")
	public String cadastrarServidor(@ModelAttribute ServidorPublico novoservidor)
	{
		Optional<ServidorPublico> servidorEncontrado = 
				servidorService.listByMatricula(novoservidor.getMatricula());
		
		if (!servidorEncontrado.isPresent())
			servidorService.save(novoservidor);
		else
			return "redirect:/mensagem";
		return "redirect:/listarServidores";
	}
	
	@GetMapping("/mensagem")
	public String mensagem(Model model)
	{
		model.addAttribute("erroMatriculaExistente",true);
		return "erro/mensagem";
	}
}
