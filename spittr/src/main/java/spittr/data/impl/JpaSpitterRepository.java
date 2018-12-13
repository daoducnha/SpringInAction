package spittr.data.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spittr.Spitter;
import spittr.data.SpitterRepository;
@Repository
@Transactional
public class JpaSpitterRepository implements SpitterRepository{

	@PersistenceUnit
	EntityManagerFactory emf;
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Spitter save(Spitter spitter) {
		emf.createEntityManager().persist(spitter);
		return null;
	}

	@Override
	public Spitter findOne(long id) {
		// TODO Auto-generated method stub
		return emf.createEntityManager().find(Spitter.class, id);
	}

	@Override
	public Spitter findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spitter> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
