package excursiones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import excursiones.modelo.dao.ExcursionDao;

@Controller
@RequestMapping("/productos")
public class ExcursionController {
	@Autowired
	private ExcursionDao edao;
	
	@GetMapping("/detalle/{id}")
	public String verDetalle(Model model, @PathVariable long id) {
		return "/forward";
	}
}
