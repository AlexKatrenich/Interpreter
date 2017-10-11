package Interpreter;

/**
 * В класі описується логіка роботи додатку.
 * 1) Заданий рядок переглядається з ліва на право.
 * 2) Кожна дія обраховується в окремому виразі.
 * 3) За допомогою рекурсії кожен підвираз рахується окремо та підставляєтья як операнда.
 */
public class Context {
	public Expression evaluation(String data){
		// Перевожу рядок в нижній регістр та встановлюю поля для початку перебору символів
		data = data.toLowerCase();
		int from = 0;
		int to = data.length() - 1;

		if(data.charAt(from) >= 'a' && data.charAt(to) <= 'z') {

		}
		return null;
	}
}
