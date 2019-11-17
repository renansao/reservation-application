package br.com.reservation.court.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="PlayerCollection")
public class PlayerDomain {
	
	@Id
	private String playerID;
	
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void getPlayerId(String playerID) {
		this.playerID = playerID;
	}
	
}