package excursiones.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
			ratt.addFlashAttribute("mensaje", "excursion editada");
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
		model.addAttribute("Excursion", excursion);
		
		return "FormEditarExcursion";
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
		
		
		model.addAttribute("Excursion", excursion);
	
		return "forward:/";
		
		/*if(edao.(idExcursion)==1) {
			model.addAttribute("mensaje", "Producto eliminado");
		}else {
			model.addAttribute("mensaje", "Producto no eliminado");
		}
		*/
		
	}
}
	
	/*@GetMapping("/detalle/{idProducto}")
	public String verDetalle(@PathVariable long idProducto, Model model) {
		Producto producto = pdao.findById(idProducto);
		if(producto != null) {
			model.addAttribute("producto", producto);
		}else {
			model.addAttribute("mensaje", "No existe producto");
			return "forward:/";
		}
		return "verDetalle";
	}
	
	
	
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