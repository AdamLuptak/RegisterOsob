package programLoader;

import consoleUI.ConsoleUI;
import core.ArrayrRegister;
import core.ListRegister;
import core.Person;

/**
 * Created by Adam Luptak
 */
public class Main {

	public static void main(String[] args) throws Exception {

		ArrayrRegister register = new ArrayrRegister(20);
		ListRegister register1 = new ListRegister(20);
		
		
//		
//		Person p1 = new Person("Adam Luptak", "0902 325 456");
//		Person p2 = new Person("Peter farkas", "055 63 62 518");
//		Person p3 = new Person("Figus luper", "+421 905 632 632");
//		Person p4 = new Person("Figus kaliko", "+421 905 632 632");
//		register.addPerson(p1);
//		register.addPerson(p2);
//		register.addPerson(p3);
//		register.addPerson(p4);
//		register1.addPerson(p1);
//		register1.addPerson(p2);
//		register1.addPerson(p3);
//		register1.addPerson(p4);
		
		// register.addPerson(new Person("Janko Hrasko", "0900123456"));
		ConsoleUI ui = new ConsoleUI(register1);
		ui.run();
	}

	
}
