package filesSavers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import consoleUI.ConsoleUI;
import core.Person;
import core.Register;

public class FileLoader implements RegisterLoader {
	private static final String ADDRESS_BIN = "address.bin";
	private static final String REGISTER_FILE = "addres.bin";

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.RegisterLoader#save(Register.Register1)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see Savers.RegisterLoader#save(core.Register)
	 */

	@Override
	public void save(Register register) {
		try (FileOutputStream fout = new FileOutputStream(ADDRESS_BIN);
				ObjectOutputStream oos = new ObjectOutputStream(fout)) {
			oos.writeObject(register);
			ConsoleUI.clc(1000);
			System.out.println("Ulozil do suboru");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Register.RegisterLoader#load()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see Savers.RegisterLoader#load()
	 */
	@Override
	public Register load() {
		File file = new File(ADDRESS_BIN);

		if (!file.exists()) {
			return null;
		}
		try (FileInputStream fin = new FileInputStream(ADDRESS_BIN);
				ObjectInputStream ois = new ObjectInputStream(fin)) {
			Register register = (Register) ois.readObject();
			return register;
		} catch (Exception e) {
			System.out.println("neexistuje subor");
			return null;
		}
	}

	/*
	 * @see Register.RegisterLoader#fill(Register.Register1)
	 */

	@Override
	public void fill(Register register) {
		register.addPerson(new Person("Aankolist ht", "0900123456"));
		register.addPerson(new Person("Aasht", "0900123456"));
		register.addPerson(new Person("Zht", "0900123456"));
		register.addPerson(new Person("Daddnkolistht", "0900123456"));
		register.addPerson(new Person("B", "0"));
	}

}
