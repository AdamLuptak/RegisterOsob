package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListRegister implements Register, Serializable, Iterable<Person> {

	private List<Person> persons = new ArrayList<Person>();;
	private int count;

	public Iterator<Person> iterator() {
		return persons.iterator();
	}

	public ListRegister(int size) {
		// persons = new ArrayList<Person>();
		count = 0;
	}

	/*
	 * vrati pocet v registri
	 */
	@Override
	public int getCount() {
		return count;
	}

	/*
	 * vrati celkovu velksot registra
	 */
	@Override
	public int getSize() {
		return persons.size();
	}

	/*
	 * vrati pozadovaneho uzivatela
	 */
	@Override
	public Person getPerson(int index) {
		Collections.sort(persons);
		return this.persons.get(index);

	}

	/*
	 * prida uzivatela
	 */
	@Override
	public void addPerson(Person person) {
		persons.add(person);
		count++;
	}

	/*
	 * ochrana aby sa nemohol pridat uzivatel s rovnakym menom a heslo
	 */
	@Override
	public boolean ochrana(String name, String phoneNumber) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		while (it.hasNext()) {
			Person p = it.next();
			if (name.equals(p.getName()) && phoneNumber.equals(p.getPhoneNumber())) {
				return false;
			}
		}
		return true;
	}

	/*
	 * najde pouzivatela podla mena
	 */
	@Override
	public Person findPersonByName(String name) {
		return persons.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
	}

	/*
	 * najde pouzivatela podla telefonenho cisla
	 */
	@Override
	public Person findPersonByPhoneNumber(String number) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		while (it.hasNext()) {
			Person p = it.next();
			if (number.equals(p.getPhoneNumber())) {
				return p;
			}
		}
		return null;
	}

	/*
	 * odstrani vybranneho pozuivatela
	 */
	@Override
	public void removePerson(Person person) {
		Iterator<Person> it = iterator();// bez toho vracia len objekty
		int pocitadlo = 0;

		while (it.hasNext()) {
			Person p = it.next();
			if (p.equals(person)) {
				pocitadlo++;
			}
		}
		count--;
		if (count >= 0) {
			persons.remove(pocitadlo - 1);
		}
	}
}
