package excursiones.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import excursiones.modelo.dao.ExcursionDao;
import excursiones.modelo.entities.Excursion;

@Controller
@RequestMapping("/excursiones")
public class ExcursionController {
	@Autowired
	private ExcursionDao edao;
	
	@PostMapping("/editar/{idExcursion}")
	public String procFormEditar(Excursion excursion, @PathVariable long idExcursion, RedirectAttributes ratt) {
		excursion.setIdExcursion(idExcursion);
		if(edao.updateOne(excursion)==1)
			ratt.addFlashAttribute("mensaje", "Excursion editada");
		else
			ratt.addFlashAttribute("mensaje", "excursion no editado");
		return "redirect:/";
	}
	
	
	
	@GetMapping("/editar/{idExcursion}")
	public String mostrarFormEditar(Model model,  @PathVariable long idExcursion) {
		Excursion excursion = edao.findById(idExcursion);
		if(excursion == null) {
			model.addAttribute("mensaje", "Excursion no existe");
			return "forward:/";
		}
		model.addAttribute("excursion", excursion);
		
		return "formEditarExcursion";
	}
	
	@GetMapping("/destacados")
	public String destacados(Model model) {
		List<Excursion> lista = edao.findByDestacados();
		model.addAttribute("mensaje", "Destacados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	@GetMapping("/creados")
	public String creados(Model model) {
		List<Excursion> lista = edao.findByCreados();
		model.addAttribute("mensaje", "Creados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	@GetMapping("/terminados")
	public String terminados(Model model) {
		List<Excursion> lista = edao.findByTerminado();
		model.addAttribute("mensaje", "Terminados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	@GetMapping("/cancelados")
	public String cancelados (Model model) {
		List<Excursion> lista = edao.findByCancelados();
		model.addAttribute("mensaje", "Cancelados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	
	@PostMapping("/porOrigen")
	public String origen(Model model, @RequestParam String origen) {
	    List<Excursion> lista = edao.origenContiene(origen);
	    model.addAttribute("mensaje", "Filtrar por " + origen);
	    model.addAttribute("excursion", lista);
	    return "home"; // Redirect or forward to the appropriate view
	}
	
	@GetMapping("/alta")
	public String alta() {
		return "formAltaExcursion";
	}
	
	
	@PostMapping("/alta")
	public String procAlta(Excursion excursion, RedirectAttributes ratt) {
		if (edao.insertOne(excursion)==1) {
			ratt.addFlashAttribute("mensaje", "Excursion insertada correctamente");
		}else {
			ratt.addFlashAttribute("mensaje","Excursion no insertada");
		}
		
		
		return "redirect:/";
	}
	
	
	@GetMapping("/cancelar/{idExcursion}")
	public String eliminar(@PathVariable long idExcursion, Model model) {
		Excursion excursion = edao.findById(idExcursion);
		if(excursion.getEstado() == "cancelado") {
			model.addAttribute("mensaje", "Esta excursion ya esta cancelada");
			return "forward:/";
		}
		else {
		excursion.setEstado("cancelado");
		edao.updateOne(excursion);
		}
		
		
		model.addAttribute("excursion", excursion);
	
		return "forward:/";
	}
		
		/*if(edao.(idExcursion)==1) {
			model.addAttribute("mensaje", "Producto eliminado");
		}else {
			model.addAttribute("mensaje", "Producto no eliminado");
		}
		*/
		

	
	@GetMapping("/detalle/{idExcursion}")
	public String verDetalle(@PathVariable long idExcursion, Model model) {
		Excursion excursion = edao.findById(idExcursion);
		if(excursion != null) {
			model.addAttribute("excursion", excursion);
		}else {
			model.addAttribute("mensaje", "No existe excursion");
			return "forward:/";
		}
		return "VerDetalle";
	}
}
	
	
	/*
	@PostMapping("/alta/")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return entity;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
*/