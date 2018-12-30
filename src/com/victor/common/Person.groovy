package com.victor.common

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Person implements Comparable<Person> {

	int age
	String firstName
	String lastName


	public Person() {
	}

	@Override
	public int compareTo(Person o) {
		return age.compareTo(o.age)
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}