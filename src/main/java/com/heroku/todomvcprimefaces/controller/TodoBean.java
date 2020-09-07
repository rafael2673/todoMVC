package com.heroku.todomvcprimefaces.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.heroku.todomvcprimefaces.DAO.GenericDAO;
import com.heroku.todomvcprimefaces.model.Todo;
import com.heroku.todomvcprimefaces.util.ConnectionFactory;

@Named("todoBean")
@SessionScoped
public class TodoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Todo todo = new Todo();

	@Inject
	private GenericDAO<Todo> todoDAO = new GenericDAO<Todo>();

	private List<Todo> todos = new ArrayList<Todo>();

	public String salvar() {
		todoDAO.createOrUpdate(todo);
		listar();
		todo = new Todo();
		return "";
	}

	@PostConstruct
	public void listar() {
		todos = todoDAO.read(Todo.class);
	}

	public String listarIndex() {
		todos = todoDAO.read(Todo.class);
		return "/pages/index.xhtml?i=0";
	}

	public void edit(Todo todo) {
		todo.setEditing(true);
	}

	public String editar() {
		todos.forEach(todo -> {
			if (todo.isEditing()) {
				todoDAO.createOrUpdate(todo);
				todo.setEditing(false);
			}
		});
		listar();
		return "";
	}

	public String remover(Todo todo) {
		todoDAO.delete(todo);
		listar();
		return "";
	}

	@SuppressWarnings("unchecked")
	public String getByFinished(String f) {
		EntityManager connection = ConnectionFactory.getConexao();
		EntityTransaction transaction = connection.getTransaction();

		transaction.begin();
		String hql = "from Todo where finished = '" + f + "' order by id asc";

		todos = connection.createQuery(hql).getResultList();

		transaction.commit();
		connection.close();
		String retorno = "";
		if(f.equals("YES")) {
			retorno= "/pages/completed.xhtml?i=2";
		} else if(f.equals("NO")) {
			retorno= "/pages/actives.xhtml?i=1";
		}
		return retorno;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public GenericDAO<Todo> getTodoDAO() {
		return todoDAO;
	}

	public void setTodoDAO(GenericDAO<Todo> todoDAO) {
		this.todoDAO = todoDAO;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

}
