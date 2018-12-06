package spittr.web;

import java.io.File;

import java.io.IOException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
  final static Logger log = LoggerFactory.getLogger(SpitterController.class);
  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }

  public SpitterController() {

  }

//  @RequestMapping(value = "/register", method = RequestMethod.GET)
//  public String showRegistrationForm() {
//    return "registerForm";
//  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String processRegistration(@Valid Spitter spitter,
       Errors error, RedirectAttributes model) throws IOException {
//    @RequestPart("profilePicture") MultipartFile profilePicture,
    if (error.hasErrors()) {
      return "registerForm";
    }
    log.info("==============================Test================================");
//    profilePicture.transferTo(
//        new File("/home/duc-nha/GitHub/SpringInAction" + profilePicture.getOriginalFilename()));
    model.addAttribute("username", spitter.getUsername());
    model.addFlashAttribute("spitter",spitter);
    spitterRepository.save(spitter);
    return "redirect:/spitter/" + spitter.getUsername();
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spitter());
    log.info("==============================Test================================");
    return "registerForm";
  }

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public String showSpitterProfile(@PathVariable(value = "username") String username, Model model) {

    if(!model.containsAttribute("spitter")){
      model.addAttribute( spitterRepository.findByUsername(username));
    }

    return "profile";
  }
}
