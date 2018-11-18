package spittr.web;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadFileController {

  @RequestMapping(method = RequestMethod.GET)
  public String showUploadForm(){
    return "uploadFile";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String uploadFile( @RequestPart("profilePicture") MultipartFile profilePicture)throws IOException {
    profilePicture.transferTo(
        new File("/home/duc-nha/GitHub/SpringInAction" + profilePicture.getOriginalFilename()));
    return "redirect:/";
  }
}
