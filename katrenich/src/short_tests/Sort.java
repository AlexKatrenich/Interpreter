package short_tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Написать консольную программу, которая бы сортировала текст поданный ей на стандартный вход по алфавиту.
 * Программа должна игнорировать регистр при сортировке
 * Программа должна сортировать не по алфавиту, а по количеству символов в строке
 * Программа в качестве аргумента может получать порядковый номер слова в строке, по которому надо сортировать строки
 * (трохи уточнень)
 * 1) сортуватись повинен список речень, що подаються на вхід
 * 2) речення повинні накопичуватись по мірі введення їх на стандартний вхід
 */
public class Sort implements Runnable{

	@Override
	public void run() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// Створюється сортована структура даних з компаратором, визначеним в лямбда-виразі
		// (для сортування порівнюється довжина рядка)
		TreeSet<String> list = new TreeSet<>((o1, o2) ->
				o1.length() > o2.length() ? 1 : (o1.length() == o2.length() ? 0 : -1));

		String data = "";
		try {
			System.out.println("Введіть речення");
			data = bufferedReader.readLine().toLowerCase();
			while(!data.equals("quit")){
				if (data.equals("clear")) {
					list.clear();
					System.out.println("Список речень - очищено!");
				} else {
					list.add(data);
				}
				printList(list);
				data = bufferedReader.readLine().toLowerCase();
			}
		} catch (IOException e) {
			System.err.println("Помилка вводу: " + e);
		}
	}

	private void printList(Set<String> list){
		Iterator<String> iter = list.iterator();
		for (;iter.hasNext();)
			System.out.println(iter.next());
	}

}
