package spittr.web;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import static
    org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
    org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
    org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SpitterControllerTest {

  @Test
  public void shouldShowRegistration() throws Exception {
    SpitterRepository repository = Mockito.mock(SpitterRepository.class);
    SpitterController controller = new SpitterController(repository);
    MockMvc mockMvc = standaloneSetup(controller).build();
    mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
  }

//  @Test
//  public void shouldProcessRegistration() throws Exception{
//    SpitterRepository mockreRepository = Mockito.mock(SpitterRepository.class);
//    Spitter unsaved =
//        new Spitter("jbauer", "24hours", "Jack", "Bauer","example@email.com");
//    Spitter saved =
//        new Spitter(24L, "jbauer", "24hours", "Jack", "Bauer","example@email.com");
//    Mockito.when(mockreRepository.save(unsaved)).thenReturn(saved);
//    SpitterController controller = new SpitterController(mockreRepository);
//    MockMvc mockMvc = standaloneSetup(controller).build();
//    mockMvc.perform(post("/spitter/register").param("firstName", "Jack").param("lastName", "Bauer")
//        .param("username", "jbauer")
//        .param("password", "24hours")).andExpect(redirectedUrl("/spitter/jbauer"));
//    Mockito.verify(mockreRepository, Mockito.atLeastOnce()).save(unsaved);
//  }
}
