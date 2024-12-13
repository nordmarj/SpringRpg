package com.example.SpringRpg.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 * A user is a unique login name that controls an actor in the game.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "name", nullable = false, unique = true)
	private String name;

	private String email;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id", referencedColumnName = "id")
	private Actor actor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Actor getActor(){
		return this.actor;
	}

	public void setActor(Actor actor){
		this.actor = actor;
	}
}
