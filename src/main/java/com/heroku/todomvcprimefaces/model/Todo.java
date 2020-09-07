package com.heroku.todomvcprimefaces.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.heroku.todomvcprimefaces.model.enums.Finished;


/**
*
* @author rafael Modelo do banco
*/
@Entity
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String assignment;
	
	@Enumerated(value = EnumType.STRING)
	@Column(length = 3)
	private Finished finished;
	
	@Transient
	private transient boolean editing;
	
	public Todo() {
		super();
		this.finished = Finished.NO;
		this.editing = false;
	}

	public Todo(String assignment, Finished finished) {
		super();
		this.assignment = assignment;
		this.finished = finished;
	}

	public Long getId() {
		return id;
	}
	
	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public Finished getFinished() {
		return finished;
	}

	public void setFinished(Finished finished) {
		this.finished = finished;
	}

	@Override
	public String toString() {
		return "Todo [assignment=" + assignment + ", finished=" + finished + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}