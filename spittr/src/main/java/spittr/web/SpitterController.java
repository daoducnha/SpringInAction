package spittr.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	private final static Logger log = LoggerFactory.getLogger(SpitterController.class);
	private SpitterRepository spitterRepository;

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	public SpitterController() {

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("spitter", new Spitter());
		return "registerForm";
	}

	@PostMapping(value = "/register")
	public String processRegistration(@ModelAttribute Spitter spitter, Errors errors, Model model) {
		
		log.info("===================" + spitter.toString());
		if (errors.hasErrors()) {
			return "registerForm";
		}
		spitter = new Spitter(1l, "aaaa", "password", "fullName", "email", true);
		spitterRepository.save(spitter);
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable(value = "username") String username, Model model) {

		if (!model.containsAttribute("spitter")) {
			model.addAttribute(spitterRepository.findByUsername(username));
		}

		return "profile";
	}
}
