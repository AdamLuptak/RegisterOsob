package consoleUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Formatter;

import core.ArrayrRegister;
import core.ListRegister;
import core.Person;
import core.Register;
import filesSavers.DBFileLoader;
import filesSavers.FileLoader;
import filesSavers.TxtFileLoader;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** register.Register of persons. */
	private Register register;
	private boolean semafor = false;
	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT, SETTINGS,
	};

	public ConsoleUI(Register register) {
		this.register = register;
	}

	/**
	 * Spustenie ceelho programu
	 */
	public void run() {
		clc(1000);
		while (true) {
			if (!semafor) {
				welcomScreen();
			}

			if (semafor) {
				switch (showMenu()) {
				case PRINT:
					clc(1000);
					printRegister();
					break;
				case ADD:
					addToRegister();
					break;
				case UPDATE:
					updateRegister();
					break;
				case REMOVE:
					removeFromRegister();
					break;
				case FIND:
					findInRegister();
					break;
				case SETTINGS:
					settings();
					break;
				case EXIT:
					registerSave();
				default:
					System.out.println("Musite zadat spravnu volbu ste mimo");
				}
			}

		}
	}

	/**
	 * zmeni nacitany register
	 */
	private void settings() {
		registerLoader();
	}

	/**
	 * ulozenie na konci
	 */
	private void registerSave() {
		System.out.println("Save[Y/N]");
		String input = readLine();
		input = input.toUpperCase();
		switch (input) {
		case "Y":
			System.out.printf(
					"Zadajte typ ulozenia\n1: Ulozenie do textoveho suboru\n2: Ulozenie do suboru\n3: Ulozenie do DB");
			String input2 = readLine();
			int selection = 0;
			try {
				handlingInput(input2, "1|2|3");
				selection = Integer.parseInt(input2);
				save(selection);
			} catch (NumberFormatException e) {
				System.err.println("zle si zadal");
				return;
			}
			System.exit(0);
			break;
		case "N":
			System.out.println("Subor sa neulozil Dovidenia");
			break;
		default:
			System.err.println("zle si zadal");
		}

	}

	private void zmenaRegistra() {

		System.out.printf("Zadajte typ registra\n1: Array\n2: List");
		String input2 = readLine();
		int selection = 0;
		try {
			handlingInput(input2, "1|2");
			selection = Integer.parseInt(input2);
			registerChanger(selection);
		} catch (NumberFormatException e) {
			System.err.println("zle si zadal");
			return;
		}

	}

	/**
	 * zmeni register z lisut na array alebo opacne
	 */
	private void registerChanger(int typ) {
		clc(1000);
		nazovPritf();
		switch (typ) {
		case 1:
			register = (ArrayrRegister) register;
			break;
		case 2:
			register = (ListRegister) register;
			break;
		}
	}

	/**
	 * formular pre nacitanie z registra
	 */
	private void registerLoader() {

		System.out.printf(
				"Zadajte typ Nacitania\n1: Nacitanie z textoveho suboru\n2: Nacitanie zo suboru\n3: Nacitanie z DB\4: Zmena registra");
		String input2 = readLine();
		int selection = 0;
		try {
			handlingInput(input2, "1|2|3|4");
			selection = Integer.parseInt(input2);
			load(selection);
		} catch (NumberFormatException e) {
			System.err.println("zle si zadal");
			return;
		}

	}

	/**
	 * Ulozi podla vyberu
	 */
	private void save(int typ) {
		switch (typ) {
		case 1:
			TxtFileLoader txt = new TxtFileLoader();
			txt.save(register);
			break;
		case 2:
			FileLoader file = new FileLoader();
			file.save(register);
			break;
		case 3:
			DBFileLoader dbFileLoader = new DBFileLoader();
			dbFileLoader.save(register);
			System.out.println("Ulozil do Databazi");
			break;

		}
	}

	/**
	 * vytvori novy register
	 */
	private void vytvorRegister(String typ) {
		clc(1000);
		nazovPritf();
		switch (typ) {
		case "2":
			semafor = true;
			register = new ArrayrRegister(20);
			break;
		case "1":
			semafor = true;
			break;
		}
	}

	/**
	 * Nacita podla vyberu
	 */
	private void load(int typ) {
		clc(1000);
		nazovPritf();
		switch (typ) {
		case 1:
			TxtFileLoader txt = new TxtFileLoader();
			register = txt.load();
			break;
		case 2:
			FileLoader file = new FileLoader();
			register = file.load();
			break;
		case 3:
			DBFileLoader dbFileLoader = new DBFileLoader();
			register = dbFileLoader.load();
			break;
		case 4:
			zmenaRegistra();
			break;

		}

	}

	/**
	 * na zaciatku programu bude vypysovat inicializacne formulare
	 */
	public void welcomScreen() {
		nazovPritf();
		System.out.printf(
				"Vitajte %s, %s%n-------------------------------------------------%n%-1d: Vytvor novy register%n%-1d: Load Register%n",
				System.getProperty("user.name"), new Date(), 1, 2);
		String input = readLine();
		try {
			handlingInput(input, "1|2");
			String inputFilePath;
			int selection = 0;

			selection = Integer.parseInt(input);
			switch (selection) {
			case 1:
				clc(1000);
				nazovPritf();
				System.out.println("Novy Register, zadajte typ Registra s ktorym chcete pracovat\nLIST: 1\nArray: 2");
				vytvorRegister(readLine());
				break;
			case 2:
				clc(1000);
				nazovPritf();
				semafor = true;
				registerLoader();
				break;

			}
		} catch (Exception e) {
			try {
				System.err.println("zle zadanay vstup Pockajte 1s");
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return;
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * zobrazi menu
	 */
	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			try {
				selection = Integer.parseInt(readLine());
				clc(1000);
			} catch (Exception e) {

				try {
					System.err.println("zle zadanay vstup Pockajte 1s");
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	/**
	 * vrati dlzku nadlhsieho mena
	 */
	public int getLongestString() {
		int maxLength = 0;
		for (int index = 0; index < register.getCount(); index++) {
			if (register.getPerson(index).getName().length() > maxLength) {
				maxLength = register.getPerson(index).getName().length();
			}
		}
		return maxLength + 5;
	}

	/**
	 * vrati dlzku nadlhsieho cisla
	 */
	public int getLongestPhone() {
		int maxLength = 0;
		for (int index = 0; index < register.getCount(); index++) {
			if (register.getPerson(index).getPhoneNumber().length() > maxLength) {
				maxLength = register.getPerson(index).getPhoneNumber().length();
			}
		}
		return maxLength + 3;
	}

	/**
	 * zobrazi register
	 */
	private void printRegister() {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		System.out.println(" ");
		// System.out.println(getLongestPhone());
		riadokCiarky(formatter);
		formatter.format("| %-2s | %2$-" + getLongestString() + "s | %3$" + getLongestPhone() + "s %4$s%n", "id",
				"Meno a Priezvisko", "  Telefone cislo", "|");
		riadokCiarky(formatter);

		for (int index = 0; index < register.getCount(); index++) {
			formatter.format("| ");
			formatter.format("%-2s | %2$-" + getLongestString() + "s | %3$" + getLongestPhone() + "s |%n", index + 1,
					register.getPerson(index).getName(), register.getPerson(index).getPhoneNumber());
		}
		riadokCiarky(formatter);

		if (register.getCount() == 0) {
			System.out.println("prazdny zoznam");
		} else {
			System.out.println(sb.toString());
		}

		System.out.println(" ");
	}

	/**
	 * pomocna funkcia pri tlaceni tabulky
	 */
	private void riadokCiarky(Formatter formatter) {
		formatter.format("+");
		for (int i = 0; i < 4; i++) {
			formatter.format("-");
		}
		formatter.format("+");
		for (int i = 0; i < getLongestString() + 2; i++) {
			formatter.format("-");
		}
		formatter.format("+");
		for (int i = 0; i < getLongestPhone() + 2; i++) {
			formatter.format("-");
		}
		formatter.format("+%n");
	}

	/**
	 * prida Persona do registra
	 */
	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();
		if (register.ochrana(name, phoneNumber)) {
			try {
				register.addPerson(new Person(name, phoneNumber));
			} catch (RuntimeException e) {
				System.err.println(e);
			}
		} else {
			System.out.println("Takyto pouzivatel uz ecxistuje");
		}
	}

	/**
	 * zmeni udaje pri pouzivatelovi
	 */
	private void updateRegister() {
		System.out.println("Zadajte index pouzivatela ktoreho chcete zmenit: ");
		int index = 0;
		try {
			index = Integer.parseInt(readLine());
		} catch (Exception e) {
			System.out.println("Zadana neplatna volba");
			return;
		}
		System.out.println("Zmena mena zadajte Meno:");
		String meno = readLine();
		Person person = register.getPerson(index - 1);
		try {
			person.setName(meno);
		} catch (Exception e) {
			System.err.println("Zadali ste zle meno");
		}
		System.out.println("Zmena Telcisla zadajte cislo:");
		String cislo = readLine();
		try {
			person.setPhoneNumber(cislo);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/**
	 * bude hladat v regitri podla toho co zadame
	 */
	private void findInRegister() {
		System.out.println("Meno hladaneho:");
		String meno = readLine();
		System.out.println("Cislo Hladaneho");
		String cislo = readLine();
		if (meno.equals("") && cislo.equals("")) {
			try {
				System.err.println("zle zadanay vstup Pockajte 1s");
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} else if (meno.equals("")) {
			if (register.findPersonByPhoneNumber(cislo) != null) {
				System.out.print("Meno cislo hladaneho :   ");
				System.out.println(register.findPersonByPhoneNumber(cislo).toString());
				System.out.println(" ");
			} else {
				System.out.println("nenadchadza sa tune");
				System.out.println(" ");
			}

		} else if (cislo.equals("")) {
			if (register.findPersonByName(meno) != null) {
				System.out.print("Meno cislo hladaneho :   ");
				System.out.println(register.findPersonByName(meno).toString());
				System.out.println(" ");
			} else {
				System.out.println("nenadchadza sa tune");
				System.out.println(" ");
			}
		} else {
			if (register.findPersonByName(meno) != null && register.findPersonByPhoneNumber(cislo) != null) {
				System.out.print("Meno cislo hladaneho :   ");
				System.out.println(register.findPersonByName(meno).toString());
				System.out.println(" ");
			} else {
				System.out.println("nenadchadza sa tune");
				System.out.println(" ");
			}
		}

	}

	/**
	 * zmaze z registra
	 */
	private void removeFromRegister() {
		System.out.println("Enter index: ");

		try {
			int index = Integer.parseInt(readLine());
			handlingInput(Integer.toString(index), "\\d");

			Person person = register.getPerson(index - 1);
			register.removePerson(person);
		} catch (Exception e) {
			try {
				System.err.println("zle zadanay vstup Pockajte 1s");
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * reset consoli CLEAR
	 */
	public static void clc(int pocetMedzier) {

		for (int clear = 0; clear < pocetMedzier; clear++) {
			System.out.println("\b");
		}

	}

	/**
	 * pre overenie vsputu univerzalna funkcia
	 */
	private boolean handlingInput(String input, String regex) {
		return input.matches(regex);
	}

	/**
	 * Tlaci uvodny velky nadpis
	 */
	public static void nazovPritf() {
		int width = 200;
		int height = 30;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("SansSerif", Font.CENTER_BASELINE, 12));

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString("R E G I S T E R", 0, 20);
		for (int y = 0; y < height; y++) {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < width; x++) {
				sb.append(image.getRGB(x, y) == -16777216 ? " " : "|");
			}

			if (sb.toString().trim().isEmpty()) {
				continue;
			}
			System.out.println(sb);
		}
		System.out.println("");
	}

}
