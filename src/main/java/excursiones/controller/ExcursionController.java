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
	
	
	/**
     * Actualiza una excursión existente con los detalles proporcionados.
     * @param excursion los detalles de la excursión a actualizar
     * @param idExcursion el ID de la excursión a editar
     * @param ratt RedirectAttributes para agregar mensajes al modelo
     * @return redirige a la página de inicio
     */
	
	@PostMapping("/editar/{idExcursion}")
	public String procFormEditar(Excursion excursion, @PathVariable long idExcursion, RedirectAttributes ratt) {
		excursion.setIdExcursion(idExcursion);
		if(edao.updateOne(excursion)==1)
			ratt.addFlashAttribute("mensaje", "Excursion editada");
		else
			ratt.addFlashAttribute("mensaje", "excursion no editado");
		return "redirect:/";
	}
	
	
	/**
     * Muestra el formulario de edición para una excursión.
     * @param model Objeto Model para agregar atributos
     * @param idExcursion el ID de la excursión a recuperar y mostrar
     * @return el nombre de la vista para editar excursiones
     */
	
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
	
	/**
     * Muestra una lista de excursiones destacadas.
     * @param model Objeto Model para agregar atributos
     * @return la vista de la página de inicio con una lista de excursiones destacadas
     */
	
	@GetMapping("/destacados")
	public String destacados(Model model) {
		List<Excursion> lista = edao.findByDestacados();
		model.addAttribute("mensaje", "Destacados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	
	/**
     * Muestra una lista de excursiones creadas.
     * @param model Objeto Model para agregar atributos
     * @return la vista de la página de inicio con una lista de excursiones creadas
     */
	
	@GetMapping("/creados")
	public String creados(Model model) {
		List<Excursion> lista = edao.findByCreados();
		model.addAttribute("mensaje", "Creados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	
	/**
     * Muestra una lista de excursiones terminadas.
     * @param model Objeto Model para agregar atributos
     * @return la vista de la página de inicio con una lista de excursiones terminadas
     */
	
	@GetMapping("/terminados")
	public String terminados(Model model) {
		List<Excursion> lista = edao.findByTerminado();
		model.addAttribute("mensaje", "Terminados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	
	/**
     * Muestra una lista de excursiones canceladas.
     * @param model Objeto Model para agregar atributos
     * @return la vista de la página de inicio con una lista de excursiones canceladas
     */
	
	@GetMapping("/cancelados")
	public String cancelados (Model model) {
		List<Excursion> lista = edao.findByCancelados();
		model.addAttribute("mensaje", "Cancelados");
		model.addAttribute("excursion", lista);
		
		return "home";
	}
	
	
	/**
     * Filtra excursiones por el lugar de origen.
     * @param model Objeto Model para agregar atributos
     * @param origen el origen por el cual se filtran las excursiones
     * @return la vista de la página de inicio con los resultados filtrados
     */
	
	@PostMapping("/porOrigen")
	public String origen(Model model, @RequestParam String origen) {
	    List<Excursion> lista = edao.origenContiene(origen);
	    model.addAttribute("mensaje", "Filtrar por " + origen);
	    model.addAttribute("excursion", lista);
	    return "home"; // Redirect or forward to the appropriate view
	}
	
	/**
     * Filtra excursiones por un rango de precio.
     * @param model Objeto Model para agregar atributos
     * @param precioMinimo el precio mínimo del rango de filtro
     * @param precioMaximo el precio máximo del rango de filtro
     * @return la vista de la página de inicio con los resultados filtrados por precio
     */
	
	@PostMapping("/porPrecio")
	public String porPrecio(Model model, @RequestParam(required = false) Double precioMinimo, 
            @RequestParam(required = false) Double precioMaximo) {
	    
	    
	 // Si no se proporcionan valores, se asignan valores predeterminados
	    if (precioMinimo == null) {
	        precioMinimo = 0.0; // Si no se proporciona, por defecto 0
	    }
	    if (precioMaximo == null) {
	        precioMaximo = 9999999.0; // Si no se proporciona, por defecto un valor muy alto (infinito)
	    }
	    List<Excursion> lista = edao.findByPrecioBetween(precioMinimo, precioMaximo);
	    model.addAttribute("mensaje", "Filtrar por precio entre " + precioMinimo + "€ y " + precioMaximo + "€");
	    model.addAttribute("excursion", lista);
	    return "home"; // Devuelve la vista con los resultados filtrados
	}
	
	/**
     * Muestra el formulario de alta de una nueva excursión.
     * @return el nombre de la vista para dar de alta una excursión
     */
	
	@GetMapping("/alta")
	public String alta() {
		return "formAltaExcursion";
	}
	
	/**
     * Procesa el formulario de alta de una nueva excursión.
     * @param excursion los detalles de la nueva excursión
     * @param ratt RedirectAttributes para agregar mensajes al modelo
     * @return redirige a la página de inicio
     */
	
	@PostMapping("/alta")
	public String procAlta(Excursion excursion, RedirectAttributes ratt) {
		if (edao.insertOne(excursion)==1) {
			ratt.addFlashAttribute("mensaje", "Excursion insertada correctamente");
		}else {
			ratt.addFlashAttribute("mensaje","Excursion no insertada");
		}
		
		
		return "redirect:/";
	}
	
	/**
     * Cambia el estado de una excursión a "cancelado".
     * @param idExcursion el ID de la excursión a cancelar
     * @param model Objeto Model para agregar atributos
     * @return redirige a la página de inicio
     */
	
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
	
	/**
	 * Cambia el estado de destacado de una excursión.
	 * 
	 * @param idExcursion El identificador de la excursión.
	 * @param model El modelo para pasar atributos a la vista.
	 * @return Redirige a la página de inicio.
	 */
	@GetMapping("/destacar/{idExcursion}")
	public String destacar(@PathVariable long idExcursion, Model model) {
	    Excursion excursion = edao.findById(idExcursion);
	    
	    if (excursion != null) {
	       
	        if ("S".equals(excursion.getDestacado())) {
	            excursion.setDestacado("N"); 
	        } else {
	            excursion.setDestacado("S");
	        }
	
	        edao.updateOne(excursion);
	        model.addAttribute("excursion", excursion);
	    } else {
	        model.addAttribute("mensaje", "Excursión no encontrada");
	    }

	    return "forward:/";
	}
		

	/**
     * Muestra los detalles de una excursión específica.
     * @param idExcursion el ID de la excursión a mostrar
     * @param model Objeto Model para agregar atributos
     * @return la vista de detalle de la excursión
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