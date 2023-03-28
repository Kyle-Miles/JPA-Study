package com.example.users.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	private String userName;
	
	@ManyToMany
	@JoinTable(name = "user_role",
	joinColumns = {
			@JoinColumn(name = "user_id")},
	inverseJoinColumns = {
			@JoinColumn(name = "role_id")
	})
	@JsonIgnore
	private Set <Role> role = new HashSet<>();
	

}
