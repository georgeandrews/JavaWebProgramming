/**
 * 
 */
package com.iteachcoding.web.model;

import java.io.Serializable;

/**
 * @author george
 *
 */
public class Person implements Serializable {
	
	private static final long serialVersionUID = -5716410110891306590L;

	private String firstName;
	private String lastName;
	private Integer age;
	private String favoriteColor;
	
	public Person() {
	}
	
	public Person(final String firstName, final String lastName, final int age, final String favoriteColor) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.favoriteColor = favoriteColor;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", favoriteColor="
				+ favoriteColor + "]";
	}

}
