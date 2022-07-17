import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pliki {

	private void Save(String path, String text) throws FileNotFoundException {

		PrintWriter f = new PrintWriter(path.toString());

		f.println(text);
		f.close();

	}

	private String Load(String path) throws FileNotFoundException {

		File f = new File(path);
		Scanner fo = new Scanner(f);

		String dane = "";

		while (fo.hasNextLine())
			dane += fo.nextLine();

		return dane;

	}

	public void Zapisz(String text) throws FileNotFoundException {
		FileDialog fd = new FileDialog(new Frame("null"), "Zapisz", FileDialog.SAVE);
		fd.setVisible(true);
		String path = fd.getDirectory();
		String file = fd.getFile();
		String zapis = path + file + ".txt";

		if (!(zapis.equals("nullnull.txt")))
			Save(zapis, text);
	}

	public String Wczytaj(String text) throws FileNotFoundException {

		FileDialog fd = new FileDialog(new Frame("null"), "Wczytaj", FileDialog.LOAD);
		fd.setVisible(true);

		String path = fd.getDirectory();
		String file = fd.getFile();
		String odczyt = path + file; 

		String data;

		if (!odczyt.equals("nullnull"))
			data = Load(odczyt);
		else
			data = text;

		return data;
	}

}
