package spittr.data;

import java.util.List;

import spittr.Spitter;

public interface SpitterRepository {
		long count();

	  Spitter save(Spitter spitter);

	  Spitter findOne(long id);

	  Spitter findByUsername(String username);

	  List<Spitter> findAll();
}
