package core;

/**
 * register.Person register.
 */
public class ArrayrRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayrRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/* (non-Javadoc)
	 * @see core.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see core.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/* (non-Javadoc)
	 * @see core.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/* (non-Javadoc)
	 * @see core.Register#addPerson(core.Person)
	 */
	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	// TODO: Implement the method findPersonByName
	/* (non-Javadoc)
	 * @see core.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		for (int i = 0; i < count; i++) {
			String h = persons[i].getName();
			if (name.equals(h)) {
				return persons[i];
			}
		}
		return null;

	}

	/* (non-Javadoc)
	 * @see core.Register#ochrana(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean ochrana(String name, String phoneNumber) {
		for (int i = 0; i < getCount(); i++) {
			if (name.equals(persons[i].getName()) && phoneNumber.equals(persons[i].getPhoneNumber())) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see core.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int i = 0; i < count; i++) {
			String h = persons[i].getPhoneNumber();
			if (phoneNumber.equals(h)) {
				return persons[i];
			}
		}
		return null;

	}

	// TODO: Implement the method removePerson
	/* (non-Javadoc)
	 * @see core.Register#removePerson(core.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int index = 0; index < persons.length; index++) {
			if (this.persons[index].equals(person)) {
				for (int j = index; j < count; j++) {
					persons[j] = persons[j + 1];
				}
				count--;
				break;
			}
		}
	}

}
