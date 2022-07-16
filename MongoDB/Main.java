import java.util.ArrayList;
import java.util.HashMap;

public class asdfasdf {

	public static void main(String[] args) throws Exception {

		// Ustawienie polaczenia z baza
		BazaMongoDB baza = new BazaMongoDB("jdbc:mariadb://192.168.55.111:3306", "dddd", "dddd");
		baza.showMetaData();

		// Utworzenie bazy danych
		boolean databaseCreate = baza.createDatabase("BaseName");

		// Wykonanie zapytania SQL do bazy
		boolean question = baza.question(
				"CREATE TABLE BaseName.TableName(jeden varchar(20), dwa varchar(30));");
		question = baza.question("INSERT INTO BaseName.TableName(jeden, dwa) values ('dane1', '1dane'),('dane2', '2dane');");

		// Wyswietla informacje w konsoli o wybranej tabeli
		baza.tableInfo("BaseName.TableName");

		// Wyswietlenie metadanych w konsoli
		baza.showMetaData();

		// Pobranie dwuch kolumn z tabeli i zapisanie w HashMap
		HashMap<String, String> map = baza.getColumn("BaseName.TableName", "jeden", "dwa");

		// Pobranie danych z kolumny i zapisanie w liscie
		ArrayList<String> list = baza.getColumn("BaseName.TableName", "jeden");
		
		System.out.println("Finish programm");
	}

}
