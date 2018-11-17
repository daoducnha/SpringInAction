package spittr.web;

import java.io.File;
import java.io.IOException;
import javax.validation.Valid;
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
import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

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
      @RequestPart("profilePicture") MultipartFile profilePicture, Errors error) throws IOException {
    if (error.hasErrors()) {
      return "registerForm";
    }
    profilePicture.transferTo(
        new File("/home/duc-nha/GitHub/SpringInAction" + profilePicture.getOriginalFilename()));
    spitterRepository.save(spitter);
    return "redirect:/spitter/" + spitter.getUsername();
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spitter());
    return "registerForm";
  }

  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  public String showSpitterProfile(@PathVariable(value = "username") String username, Model model) {
    Spitter spitter = spitterRepository.findByUsername(username);
    if(spitter == null){
      throw new SpittleNotFoundException();
    }
    model.addAttribute(spitter);
    return "profile";
  }
}
