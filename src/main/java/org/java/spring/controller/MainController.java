package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getPizza(Model model,
			@RequestParam(required = false) String search) {
		List<Pizza> pizzas = search == null 
				? pizzaService.findAll()
				: pizzaService.findByNome(search);
		
		model.addAttribute("pizzas",pizzas);
		model.addAttribute("search", search == null ? "" : search);
		
		return "pizzas";
		
	}
	
	
		@GetMapping("/pizzas/{id}")
		public String getPizza(Model model, @PathVariable int id) {
			
			Pizza pizza = pizzaService.findById(id);
			
			model.addAttribute("pizza", pizza);
			
			return "pizza";
		}
		
		@GetMapping("/pizzas/create")
		public String createBook(Model model) {
			
			Pizza pizza = new Pizza();
			
			model.addAttribute("pizza", pizza);
			
			return "pizza-form";
		}
		
		
		
		
		@PostMapping("/pizzas/create")
	    public String storePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {

	            System.out.println(bindingResult);

	            model.addAttribute("pizza", pizza);
	            return "pizza-form";
	        }

	        pizzaService.save(pizza);

	        return "redirect:/";
	    }
		
		
		@GetMapping("/pizzas/edit/{id}")
	    public String editPizza (Model model,
	                @PathVariable int id) {
	        Pizza pizza = pizzaService.findById(id);
	        model.addAttribute("pizza", pizza);

	        return "pizza-form";
	    }

	    @PostMapping("/pizzas/edit/{id}")
	    public String updatePizza(Model model,
	                @Valid @ModelAttribute Pizza pizza,
	                BindingResult bindingResult) {
	        return storePizza(model, pizza, bindingResult);
	    }
			
	    @PostMapping("/pizzas/delete/{id}")
		public String deletePizza(@PathVariable int id) {
			
			Pizza pizza = pizzaService.findById(id);
			pizzaService.delete(pizza);
			
			System.out.println(pizza);
			
			return "redirect:/";
		}
		
}
