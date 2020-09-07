package com.heroku.todomvcprimefaces.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
//import javax.persistence.Query;

import com.heroku.todomvcprimefaces.util.ConnectionFactory;

public class GenericDAO<E> implements Serializable {

	private static final long serialVersionUID = 1L;

	public void createOrUpdate(E entidade) {
		EntityManager connection = ConnectionFactory.getConexao();
		EntityTransaction transaction = connection.getTransaction();

		transaction.begin();

		connection.merge(entidade);
		transaction.commit();
		connection.close();
	}

	@SuppressWarnings("unchecked")
	public List<E> read(Class<E> entidade) {
		EntityManager connection = ConnectionFactory.getConexao();
		EntityTransaction transaction = connection.getTransaction();

		transaction.begin();
		String hql = "from "+ entidade.getName() +" order by id asc";
		
		List<E> entidades = connection.createQuery(hql).getResultList();
		
		transaction.commit();
		connection.close();
		return entidades;
	}
	
	public void delete(E entidade) {
		EntityManager connection = ConnectionFactory.getConexao();
		EntityTransaction transaction = connection.getTransaction();
		
		transaction.begin();
		
		Object id = ConnectionFactory.getPrimaryKey(entidade);
		String hql = "delete from "+ entidade.getClass().getCanonicalName() +" where id = "+id;
		
		 connection.createQuery(hql).executeUpdate();
		
		transaction.commit();
		connection.close();
		
	}
	
}
