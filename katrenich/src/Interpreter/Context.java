package Interpreter;

/**
 * В класі описується логіка роботи додатку.
 * 1) Заданий рядок переглядається з ліва на право.
 * 2) Кожна дія обраховується в окремому виразі.
 * 3) За допомогою рекурсії кожен підвираз рахується окремо та підставляєтья як операнда.
 */
public class Context {
	public Expression evaluate(String data){
		// Перевожу рядок в нижній регістр та видаляю зайві пробіли
		data = data.toLowerCase();
		data = data.replaceAll(" ", "");

		return evaluate1(data);
	}

	private Expression evaluate1(String data) {
		int pos = data.length() - 1;
		while (pos > 0){
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
				}
			}
		}
		int result = Integer.valueOf(data);
		return new NumberExpression(result);
	}
}
