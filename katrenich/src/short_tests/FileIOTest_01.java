package short_tests;

import java.io.*;
import java.util.ArrayList;


/** Задача 1
 * Написати програму, що читає текст з одного файлу та записує цей текст в інший файл в порядку зростання.
 */
public class FileIOTest_01 {
	public static void main(String[] args) {
		File fileOut = new File("D:\\JAVA\\fileOut.txt");
		File fileIn = new File("D:\\JAVA\\fileIn.txt");
		copySortingFileToFile(fileOut, fileIn);
	}

	// метод для сортування та копіювання рядків з файлу в файл
	public static void copySortingFileToFile(File fileOut, File fileIn){
		ArrayList<String> list; // масив рядків для збереження та сортування зчитаної інформації з файлу

		if(fileOut != null && fileIn != null){
			list = new ArrayList<>();
			BufferedReader reader = null;
			FileWriter writer = null;

			// Зчитуємо рядки з файлу в масив
			try {
				reader = new BufferedReader(new FileReader(fileOut));

				String s = reader.readLine();
				while(s != null) {
					list.add(s);
					s = reader.readLine();
				}

			} catch (FileNotFoundException e) {
				System.err.println("Файл для зчитування інформації - не знайдено!");
			} catch (IOException e) {
				System.err.println("Помилка зчитування з файлу!");
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println("Помилка при закритті файла зчитування");
				}
			}

			// Ощищаємо файл перед записом(насправді видаляємо старий та створюємо новий з таким же іменем)
			if(fileIn.exists()){
				fileIn.delete();
				try {
					fileIn.createNewFile();
				} catch (IOException e) {
					System.out.println("Помилка створення нового файлу виводу інформації!");
				}
			}

			// сортуємо масив рядків за допомогою стандартного методу списків
			arrayListSort(list);

			try {
				writer = new FileWriter(fileIn, true);


				for (String s: list) {
					if(s != null){
						writer.write(s + "\r\n");
					}
				}

			} catch (IOException e) {
				System.err.println("Помилка запису в файл!");
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("Помилка при закритті файлу виводу");
				}
			}

		}
	}


	// сортування рядків методом вставки
	private static void arrayListSort(ArrayList<String> list){
		if(list.size() > 0){
			for (int i = 0; i < list.size() ; i++) {
				String key = list.get(i);
				int j = i - 1;
				while (j >= 0 && (list.get(j).compareToIgnoreCase(key) > 0)){
					list.set(j + 1, list.get(j));
					j--;
				}
				list.set(j + 1, key);
			}
		}
	}

}
