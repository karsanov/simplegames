package com.karsanov.simplegames.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "userName")
	private String name;
	
	@Column(name = "userPassword")
	private String password;
	
	@Column(name = "userAvatarFilename")
	private String avatarFilename;
	
	@Autowired
	public User() {}
	
	@Autowired
	public User(Integer id, String name) throws IllegalArgumentException {
		checkId(id);
		checkName(name);
		
		this.id = id;
		this.name = name;
	}
	
	private void checkId(Integer id) throws IllegalArgumentException {
		if (!(id >= 0 && id <= Integer.MAX_VALUE)) throw new IllegalArgumentException(""
				+ "id должно быть целым положительным числом");
	}
	
	private void checkName(String name) throws IllegalArgumentException {
		if (name == null || name.equals("")) throw new IllegalArgumentException(""
				+ "name не может быть null или пустой строкой");
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) throws IllegalArgumentException  {
		checkId(id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws IllegalArgumentException  {
		checkName(name);
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarFilename() {
		return avatarFilename;
	}

	public void setAvatarFilename(String avatarFilename) {
		this.avatarFilename = avatarFilename;
	}

	@Override
	public int hashCode() {
		return (name == null) ? 0 : name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof User)) return false;
		
		User other = (User) obj;
		
		if (id != other.id) return false;
		if (name == null ^ other.name == null) return false;
		if (name != null && !name.equals(other.name)) return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
