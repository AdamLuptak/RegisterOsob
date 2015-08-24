package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Person;
import junit.framework.Assert;

public class RegisterTest {
	/**
	 * pre zle zadanie cisla
	 */
	@Test(expected = RuntimeException.class)
	public void test() {
		Person person = new Person("ada das", "asfsf4554");
	}

	/**
	 * pre zle zadanie mena
	 */
	@Test(expected = RuntimeException.class)
	public void test2() {
		Person person = new Person("adadas", "0908 555 456");
	}

	/**
	 * pre zle zadanie mena
	 */
	public void test3() {
		Person person = new Person("adam luptak", "0908 555 456");
		Person person1 = new Person("adam luptak", "0908 555 456");
		boolean helper = (person.compareTo(person1) == 0) ? true : false;

		assertTrue(helper);

	}

	/**
	 * pre korektne zadanie mena
	 */
	public void test4() {
		String name = "adam Luptak";
		Person person = new Person(name, "0908 555 456");
		assertEquals(name, person.getName());
	}

	/**
	 * pre korektne zadanie cisla
	 */
	public void test5() {
		String cislo = "0908 654 456";
		Person person = new Person("adam luptak", cislo);
		assertEquals(cislo, person.getPhoneNumber());
		cislo = "055 65 64 857";
		person.setPhoneNumber(cislo);
		assertEquals(cislo, person.getPhoneNumber());
		cislo = "+421 919 647 789";
		person.setPhoneNumber(cislo);
		assertEquals(cislo, person.getPhoneNumber());
	}

}
