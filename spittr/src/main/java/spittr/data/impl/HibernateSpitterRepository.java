package spittr.data.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Transactional
@Repository
public class HibernateSpitterRepository implements SpitterRepository {
	private final static Logger log = LoggerFactory.getLogger(HibernateSpitterRepository.class);
	private SessionFactory sessionFactory;

	@Inject
	public HibernateSpitterRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public long count() {
		return findAll().size();
	}

	@Override
	public Spitter save(Spitter spitter) {
		log.info(spitter.toString());

		Serializable id = getSession().save(spitter);

		return new Spitter((Long) id, spitter.getUsername(), spitter.getPassword(), spitter.getFullName(),
				spitter.getEmail(), spitter.isUpdateByEmail());
	}

	@Override
	public Spitter findByUsername(String username) {
		return (Spitter) getSession().createCriteria(Spitter.class).add(Restrictions.eq("username", username)).list()
				.get(0);
	}

	@Override
	public Spitter findOne(long id) {

		return (Spitter) getSession().get(Spitter.class, id);
	}

	@Override
	public List<Spitter> findAll() {
		return (List<Spitter>) getSession().createCriteria(Spitter.class).list();
	}

}
