package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Files {

	public static List<String> readFileByLine(String string) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(string))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			return lines;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
