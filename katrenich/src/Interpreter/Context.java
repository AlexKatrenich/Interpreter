package Interpreter;

/**
 * В класі описується логіка роботи додатку.
 * 1) Заданий рядок переглядається з ліва на право.
 * 2) Кожна дія обраховується в окремому виразі.
 * 3) За допомогою рекурсії кожен підвираз рахується окремо та підставляєтья як операнда.
 */
public class Context {
	public Expression evaluate(String data){
		// Перевожу рядок в нижній регістр та видаляю зайві пробіли в заданому виразі
		data = data.toLowerCase();
		data = data.replaceAll(" ", "");

		return evaluate1(data, 0, data.length());
	}

	private Expression evaluate1(String data, int from, int to) {
		int pos = from;
		if(data.charAt(from) == '('){
			while (data.charAt(pos)!=')'){
				pos++;
			}
			return evaluate1(data, from + 1, pos);
		} else {
			System.out.println(data.substring(from, to));
		}
		return null;
	}
}
