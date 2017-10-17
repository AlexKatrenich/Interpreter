package Interpreter;

/**
 * В класі побудована логіка роботи інтерпретатора для обчислення математичних виразів.
 * Логіка роботи Інтерпретатора побудована на 2-х методах публічному evaluate та приватному evaluate1
 *
 */
public class Context {

	// метод переводить всі літери рядка в нижній регістр, прибирає всі пробіли в рядку та викликає приватний метод evaluate1
	public static Expression evaluate(String data) throws NumberFormatException{
		data = data.toLowerCase();
		data = data.replaceAll(" ", "");

		return evaluate1(data);

	}

	// в методі проводиться безпосередній перебір елементів виразу(рекурсією) та його розрахунок
	private static Expression evaluate1(String data) throws NumberFormatException{
		// межі розрахунку виразу
		int from = 0;
		int to = data.length() - 1;

		/* Перевірка на обчислення квадратного кореню рівняння та відповідно обчислення.
		 */
		if(data.contains("sqrt(")){
			return new NumberExpression((int) Math.sqrt(evaluate1(data.substring(5, data.length() - 1)).interpret()));
		}

		// розбір підвиразів в дужках
		if (data.charAt(from) == '(') {
			if (data.charAt(to) == ')') {
				return evaluate1(data.substring(from + 1, to));
			} else {
				// якщо у виразі є одна пара вкладених дужок, то повертається обрахований вираз в цих дужках
				int pos = to;
				while (data.charAt(pos) != ')') {
					pos--;
				}
				return evaluate1(String.valueOf(evaluate1(data.substring(from + 1, pos)).interpret()) + data.substring(pos + 1, to + 1));
			}
		} else {
			//перебір простої частини виразу, права операнда - це число, ліва - це розрахований вираз лівої частини виразу
			int pos = to;
			while (pos > from){
				if(Character.isDigit(data.charAt(pos))){
					pos--;
				} else {
					Expression left = evaluate1(data.substring(0, pos));
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
