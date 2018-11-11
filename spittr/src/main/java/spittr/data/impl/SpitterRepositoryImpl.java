package spittr.data.impl;

import org.springframework.stereotype.Component;
import spittr.Spitter;
import spittr.data.SpitterRepository;

@Component
public class SpitterRepositoryImpl implements SpitterRepository {

  @Override
  public Spitter save(Spitter spitter) {
    return spitter;
  }

  @Override
  public Spitter findByUsername(String username) {
    return new Spitter("Dao","Duc Nha","ducnha","123456");
  }
}
