package Interpreter;

/**
 * В класі описується логіка роботи додатку.
 * 1) Заданий рядок переглядається з ліва на право.
 * 2) Кожна дія обраховується в окремому виразі.
 * 3) За допомогою рекурсії кожен підвираз рахується окремо та підставляєтья як операнда.
 */
public class Context {

	public Expression evaluate(String data) throws NumberFormatException{
		// Рядок переводиться в нижній регістр та видаляються зайві пробіли
		data = data.toLowerCase();
		data = data.replaceAll(" ", "");

		// викликається приватний метод для розрахунку виразу
			return evaluation1(data);

	}

	private Expression evaluation1(String data) throws NumberFormatException{
		// ініціалізація змінних для розрахунку меж символів в рядку
		int from = 0;
		int to = data.length() - 1;

		/* при знаходженні в рядку символвів розрахунку квадратного кореня, в рядку обрізаються 5 - символів з початку
		 * та 1 символ з кінця. Потім шукається рішення Виразу, що знаходився в дужках кореня.
		 *  Далі розраховується корінь від отриманого числа, приводиться до int та обгортається в Вираз числа.
		 */
		if(data.contains("sqrt(")){
			return new NumberExpression((int) Math.sqrt(evaluation1(data.substring(5, data.length() - 1)).interpret()));
		}

		// розроблено перебір та розрахунок виразів в дужках
		if (data.charAt(from) == '(') {
			if (data.charAt(to) == ')') {
				return evaluation1(data.substring(from + 1, to));
			} else {
				int pos = to;
				while (data.charAt(pos) != ')') {
					pos--;
				}
				return evaluation1(String.valueOf(evaluation1(data.substring(from + 1, pos)).interpret()) + data.substring(pos + 1, to + 1));
			}
		} else {
			int pos = data.length() - 1;
			while (pos > 0){
				if(Character.isDigit(data.charAt(pos))){
					pos--;
				} else {
					Expression left = evaluation1(data.substring(0, pos));
					Expression right = new NumberExpression(Integer.valueOf(data.substring(pos+1, data.length())));
					char operator = data.charAt(pos);
					switch (operator){
						case '-' : return new MinusExpression(left, right);
						case '+' : return new PlusExpression(left, right);
						case '*' : return new MultiplicExpression(left, right);
						case '/' : return new DivisionExpression(left, right);
						case '^' : return new ExpExpression(left, right);
					}
				}
			}
			int result = Integer.valueOf(data);
			return new NumberExpression(result);
		}

	}

}
