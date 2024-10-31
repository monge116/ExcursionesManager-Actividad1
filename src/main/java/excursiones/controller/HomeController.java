package excursiones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import excursiones.modelo.dao.ExcursionDao;
import excursiones.modelo.entities.Excursion;

@Controller
public class HomeController {
	@Autowired
	private ExcursionDao edao;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Excursion> lista = edao.findAll();
		
		model.addAttribute("excursion", lista);
		
		return "home";
	}
}
