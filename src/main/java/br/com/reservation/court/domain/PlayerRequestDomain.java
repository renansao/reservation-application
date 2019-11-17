package br.com.reservation.court.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PlayerRequestDomain {
	
	private String name;
	
	@NotNull
	@Min(value = 18)
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
}
