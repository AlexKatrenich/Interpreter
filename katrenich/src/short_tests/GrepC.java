package short_tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Потрібно написати програму, що вирішує наступні задачі:
 * Необходимо реализовать консольную программу, которая бы фильтровала поток текстовой информации подаваемой на вход и
 * на выходе показывала лишь те строчки, которые содержат слово передаваемое программе на вход в качестве аргумента.

 * Варианты усложнения:
 * Программа не должна учитывать регистр
 * В качестве аргумента может быть задано не конкретное слово, а регулярное выражение
 */
public class GrepC {
	public static void grep(String[] args){
		if (args != null && args.length > 0){
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String inputData = "";

			try	{
				System.out.println("Введіть речення");
				inputData = br.readLine().toLowerCase();
				boolean accept = false;
				while(!inputData.equals("quit")){
					for(String s: args){
						if(matcher(inputData, s)){
							accept = true;
						} else {
							accept = false;
							break;
						}
					}
					if(accept){
						System.out.println(inputData);
					}
					inputData = br.readLine().toLowerCase();
				}
			} catch (IOException e) {
				System.err.println("Помилка вводу даних!");
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Помилка закриття потоку вводу!");
				}
			}
		} else {
			System.out.println("Задайте регулярні вирази в якості аргументів программи та запустіть спочатку");
		}
	}

	private static boolean matcher(String data, String regex) throws IOException {
		Pattern p;

		try{
			p = Pattern.compile(regex);
		} catch (PatternSyntaxException ex){
			throw new IOException();
		}

		Matcher m = p.matcher(data);

		return m.matches();
	}
}
