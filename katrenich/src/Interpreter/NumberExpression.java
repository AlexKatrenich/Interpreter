package Interpreter;

/**
 * В класі описується термінальний вираз. Клас характеризує число.
 */
public class NumberExpression implements Expression {
	int number;

	public NumberExpression(int number){
		this.number = number;
	}

	public int interpret() {
		return number;
	}

}
