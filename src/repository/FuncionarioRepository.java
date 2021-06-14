package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.RepositoryException;
import factory.JPAFactory;
import model.Funcionario;

public class FuncionarioRepository extends Repository<Funcionario> {

	public FuncionarioRepository() {
		super(JPAFactory.getEntityManager());
	}

	public FuncionarioRepository(EntityManager em) {
		super(em);
	}

	public List<Funcionario> findAll() throws RepositoryException {
		EntityManager em = getEntityManager();
		StringBuffer jpql = new StringBuffer();
		jpql.append("SELECT ");
		jpql.append(" f ");
		jpql.append("FROM ");
		jpql.append(" Funcionario f ");

		Query query = em.createQuery(jpql.toString());
		return (List<Funcionario>) query.getResultList();
	}

}
