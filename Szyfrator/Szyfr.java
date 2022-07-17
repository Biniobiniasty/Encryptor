import java.util.Random;

import javax.swing.JOptionPane;

public class Szyfr {

	private boolean checkData(String text, String klucz) {
		if ((text.length() == 0 || (klucz.length() == 0))) {
			JOptionPane.showMessageDialog(null, "Blad puste pole");
			return false;
		}
		if ((klucz.length() < 5) || (klucz.length() > 100)) {
			JOptionPane.showMessageDialog(null, "Niepoprawna dlugosc klucza");
			return false;
		}
		if (text.length() > 2000) {
			JOptionPane.showMessageDialog(null, "Blad, wiadomosc za dluga max 2000");
			return false;
		}
		if (klucz.contains(" ")) {
			JOptionPane.showMessageDialog(null, "Klucz nie moze zawierac spacji");
			return false;
		}
		return true;
	}

	private int los(int min, int max) {

		max++;
		Random r = new Random();
		int wynik = Math.abs(r.nextInt() % (max - min));
		wynik += min;
		return wynik;
	}

	public String Szyfruj(String text, String klucz) {
		if (!checkData(text, klucz))
			return text;

		// FIRST
		StringBuilder sstring = new StringBuilder();
		char znak;

		for (int z = 0; z < 4; z++) {
			sstring.setLength(0);

			znak = klucz.toCharArray()[los(0, klucz.length() - 1)];
			sstring.append(znak);

			for (int x = 0; x < text.length(); x++) {
				sstring.append(text.toCharArray()[x]);
				znak = klucz.toCharArray()[los(0, klucz.length() - 1)];
				sstring.append(znak);
			}

			text = sstring.toString();
		}

		// SECOND

		sstring.reverse();
		String result = sstring.toString();

		// THIRD

		sstring.setLength(0);
		for (int x = 0; x < result.length(); x++) {
			sstring.append(result.toCharArray()[x]);
			sstring.append((char) los(40, 90));
		}

		// FOURTH
		result = sstring.toString();
		sstring.setLength(0);

		int index = 0;

		for (int x = 0; x < result.length(); x++) {
			byte kod = (byte) result.toCharArray()[x];
			byte kodK = (byte) klucz.toCharArray()[index];
			index++;
			if (index >= klucz.length())
				index = 0;

			int sum = kod + kodK;

			sstring.append(sum);
			if (sum < 100)
				sstring.append('0');
		}

		result = sstring.toString();

		// FIVETH
		int count = los(1000, 2000); 

		for (int x = 0; x < count; x++)
			sstring.append(los(10, 99));

		sstring.append(count);

		result = sstring.toString();

		// SIXTH
		
		count = 120;
		sstring.setLength(0);
		
		for(int x=0;x<result.length();x++)
		{
			if(x == count)
			{
				sstring.append("\n");
				count += 120;
			}
			sstring.append(result.toCharArray()[x]);
		}
		
		result = sstring.toString();
		
		return result;
	}

	public String Deszyfruj(String text, String klucz) {
		String textSPR = text;
		if (text.length() > 2000)
			textSPR = text.substring(0, 2000);
		if (!checkData(textSPR, klucz))
			return text;
		if (text.length() < 100) {
			JOptionPane.showMessageDialog(null, "Niepoprawne dane wejsciowe");
			return text;
		}

		//SIXTY
		
		StringBuilder sssstring = new StringBuilder();
		StringBuilder ssstring = new StringBuilder();
		
		for(int x=0;x<text.length();x++)
			if(text.toCharArray()[x] != '\n')
				ssstring.append(text.toCharArray()[x]);
		
		text = ssstring.toString();
		
		
		// FIVETH
		
		sssstring.setLength(0);
		ssstring.setLength(0);

		int count = 0; 
		int index = 0;

		for (int x = text.length() - 4; x < text.length(); x++) {
			ssstring.append(text.toCharArray()[x]);
		}
		
		try {
			count = Integer.parseInt(ssstring.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		text = text.substring(0, text.length() - 5 - (count * 2));

		// FOURTH

		sssstring.setLength(0);
		ssstring.setLength(0);

		count = 0;
		index = 0;

		for (int x = 0; x < text.length(); x++) {
			ssstring.append(text.toCharArray()[x]);
			count++;
			if (count == 3) {

				int licz = Integer.parseInt(ssstring.toString());

				if (licz > 500)
					licz = licz / 10;

				byte kod = (byte) klucz.toCharArray()[index];
				index++;
				if (index >= klucz.length())
					index = 0;

				licz -= kod;

				sssstring.append((char) licz);

				ssstring.setLength(0);
				count = 0;
			}
		}
		text = sssstring.toString();

		// THIRD
		ssstring.setLength(0);
		for (int x = 0; x < text.length(); x++) {
			if ((x + 1) % 2 == 1)
				ssstring.append(text.toCharArray()[x]);
		}

		text = ssstring.toString();
		// SECOND

		StringBuilder sstring = new StringBuilder(text);
		sstring.reverse();
		text = sstring.toString();

		// FIRST

		char znak;
		for (int z = 0; z < 4; z++) {
			sstring.setLength(0);
			for (int x = 0; x < text.length(); x++) {
				znak = text.toCharArray()[x];

				if (((x + 1) % 2) == 0) {
					sstring.append(znak);
				} else {
					boolean check = false;
					for (int y = 0; y < klucz.length(); y++)
						if (klucz.toCharArray()[y] == znak) {
							check = true;
							break;
						}
					if (!check) {
						return "Klucz jest nie poprawny";
					}
				}
			}
			text = sstring.toString();

		}
		return sstring.toString();
	}
}
