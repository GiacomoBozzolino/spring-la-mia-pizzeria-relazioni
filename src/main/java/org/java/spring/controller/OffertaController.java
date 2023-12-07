package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OffertaSpecialeService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class OffertaController {
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
    private OffertaSpecialeService offertaSpecialeService;
	
	
	
	@GetMapping("/pizzas/{id}/offerte")
    public String createOffertaSpeciale(Model model, @PathVariable int id) {
    	
        Pizza pizza = pizzaService.findById(id);
        
        OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
        model.addAttribute("offertaSpeciale", offertaSpeciale);
        model.addAttribute("pizza", pizza);
        
        return "offerta-form";
    }

	
	  @PostMapping("/pizzas/{id}/offerte")
	    public String storeOffertaSpeciale(@ModelAttribute DTO dto, @PathVariable int id) {
	        Pizza pizza = pizzaService.findById(id);
	        OffertaSpeciale offertaSpeciale = new OffertaSpeciale(dto.getTitolo(), dto.getDataDiInizio(), dto.getDataDiFine(), pizza);
	        
	        offertaSpecialeService.save(offertaSpeciale);
	        return "redirect:/pizzas/{id}";
	    }

    
   
 @GetMapping("/pizzas/{pizza_id}/offerte/edit/{id}")
  public String editOffertaSpeciale(Model model, @PathVariable int id, @PathVariable int pizza_id) {
      
       OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(id);
       model.addAttribute("offertaSpeciale", offertaSpeciale);
       return "offerta-form";
   }
	
	
   @PostMapping("/pizzas/{pizza_id}/offerte/edit/{id}")
   public String updateOffertaSpeciale(Model model, @ModelAttribute DTO dto, @PathVariable int pizza_id, @PathVariable int id)
            {
	   Pizza pizza = pizzaService.findById(pizza_id);
	   OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(id);
	   
	   offertaSpeciale.setTitolo(dto.getTitolo());
	   offertaSpeciale.setDataDiInizio(dto.getDataDiInizio());
	   offertaSpeciale.setDataDiFine(dto.getDataDiFine());
	   
	   offertaSpecialeService.save(offertaSpeciale);
	  
	   return "redirect:/pizzas/{pizza_id}";
       
   }
	
	
}

