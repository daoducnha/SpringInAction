package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;

import static
    org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
    org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
    org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SpitterControllerTest {

  @Test
  public void shouldShowRegistration() throws Exception {
    SpitterController controller = new SpitterController();
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
  }
}
