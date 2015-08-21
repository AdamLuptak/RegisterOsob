package filesSavers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import consoleUI.ConsoleUI;
import core.ListRegister;
import core.Person;
import core.Register;

public class TxtFileLoader implements RegisterLoader {
	@Override
	public void save(Register register) {

		try (PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");) {
			for (int i = 0; i < register.getCount(); i++) {
				Person p = register.getPerson(i);
				writer.println(p.getName());
				writer.println(p.getPhoneNumber());
			}
			ConsoleUI.clc(1000);
			System.out.println("Ulozil do suboru");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Register load() {

		int pocitadlo = 0;
		Register register = new ListRegister(pocitadlo);
		try (BufferedReader r = new BufferedReader(new FileReader("the-file-name.txt"))) {
			String line;
			int pocitadlo_cislo = 0;
			String meno = "";
			String cislo = "";

			while ((line = r.readLine()) != null) {// da sa jednoduchsie dalo sa
													// readline dva razi zasebou
				line = line.trim();
				if (!"".equals(line)) {
					meno = line;
					cislo = r.readLine();
					Person p = new Person(meno, cislo);
					register.addPerson(p);
				}
			}
			return register;
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		System.out.println("sdsds");
		return null;
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	@Override
	public void fill(Register register) {
		PrintWriter writer = null;
		try {

			writer = new PrintWriter("the-file-name.txt", "UTF-8");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.println("Aladar");
		writer.println("020555");
		writer.println("Aladadsdssdr");
		writer.println("020555");
		writer.close();

	}

	public String readFile(String filename) throws IOException {
		String content = null;
		File file = new File(filename); // for ex foo.txt
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return content;
	}
}
