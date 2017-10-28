package short_tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

/** Задача 2
 *  Написати програму, що буде перевіряти створювати список файлів в заданій папці, створювати нову папку та копіювати
 *  файли з заданої папки в створену. Файли повинні бути рандомно перемішані, імена файлів повинні бути задані нумерацією.
 *
 */

public class FileIOTest_02 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String dirInPath = null,
				dirOutPath = null;

		System.out.println("Введіть шлях до папки для зчитування файлів");
		try {
			dirInPath = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Введіть шлях та назву папки призначення");
		try {
			dirOutPath = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//copyFilesDirectory("D:\\JAVA\\TestDirectory", "D:\\JAVA\\DirectoryOUT6");
		copyFilesDirectory(dirInPath, dirOutPath);
	}

	public static void copyFilesDirectory(String dirInPath, String dirOutPath){
		File dirSRC = new File(dirInPath);

		if (dirSRC.exists() && dirSRC.isDirectory()){

			//формуємо список файлів для переносу в іншу папку
			File[] files = dirSRC.listFiles();
			ArrayList<File> listFiles = new ArrayList<File>();

			for (File file : files) {
				if(file.isFile()){
					listFiles.add(file);
				}
			}

			// створюємо папку для копіювання файлів
			File dirOut = new File(dirOutPath);
			if(!dirOut.exists())
				dirOut.mkdir();

			FileIOTest_02.shuffle(listFiles);

			// записуємо файли з масиву в нову папку
			for (int i = 0; i < listFiles.size(); i++) {
				File fileOut = new File(dirOutPath + "\\" + String.valueOf(i) + getFileExtension(listFiles.get(i).getName()));

				for (int j = 1; ; j++) {
					if(fileOut.exists()) {
						fileOut = new File(dirOutPath + "\\" + String.valueOf(i + j) + getFileExtension(listFiles.get(i).getName()));
					} else {
						break;
					}
				}

				try {
					Files.copy(listFiles.get(i).toPath(), fileOut.toPath());
				} catch (FileAlreadyExistsException e){
					System.out.println("FileAlreadyExist: " + fileOut.getName());
					continue;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("File do not EXIST!");
		}
	}

	// метод повертає розширення файлу у вигляді рядка
	public static String getFileExtension(String fileName){
		int index = fileName.lastIndexOf('.');
		return index == -1 ? null : fileName.substring(index);
	}

	// метод перемішує елементи масива в довільному порядку
	public static boolean shuffle(ArrayList list){
		if(list.size() > 2){
			for (int i = 0; i < list.size(); i++) {
				int rnd = ((int)(Math.random() * 100))% list.size();
				Collections.swap(list, i, rnd);
			}
			return true;
		} else {
			return false;
		}
	}
}
