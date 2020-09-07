/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heroku.todomvcprimefaces.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author rafael Classe de conexao com o banco de dados mysql
 */
public class ConnectionFactory implements Serializable {

	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory connectionFactory = null;

	static {
		if (connectionFactory == null) {
			connectionFactory = Persistence.createEntityManagerFactory("Todo");
		}
	}

	public static EntityManager getConexao() {
		return connectionFactory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {
		return connectionFactory.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
