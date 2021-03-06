package spittr.web;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.Spittle;
import spittr.SpittleForm;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("spittles")
public class SpittleController {
//  private static final String MAX_LONG_AS_STRING = Long.toString(Long.toString(Long.MAX_VALUE);
  private static final String MAX_LONG_AS_STRING = "1000";

  private SpittleRepository spittleRepository;

  @Autowired
  public SpittleController(SpittleRepository spittleRepository) {
    this.spittleRepository = spittleRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String spittles(Model model) {
    model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
    return "spittles";
  }
//
//  @RequestMapping(method = RequestMethod.GET)
//  public List<Spittle> spittles(
//      @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
//      @RequestParam(value = "count", defaultValue = "50") int count) {
//    return spittleRepository.findSpittles(max, count);
//  }

  public String saveSpittle(SpittleForm form, Model model){
//        spittleRepository.save(
//            new Spittle(null, form.getMessage(), new Date(),
//                form.getLongitude(), form.getLatitude()));

    return "redirect:/spittles";
  }

  @ExceptionHandler(DuplicateSpittleException.class)
  public String handleDuplicateSpittle(){
    return "error/duplicate";
  }

  @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
  public String showSpittle(@PathVariable(value = "spittleId") long spittleId, Model model){
    model.addAttribute(spittleRepository.findOne(spittleId));
    return "spittle";
  }
}
