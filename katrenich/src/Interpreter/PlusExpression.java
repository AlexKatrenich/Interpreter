package Interpreter;

/**
 * В класі описується додавання операнд
 */

public class PlusExpression implements Expression {
	Expression leftOperand;
	Expression rightOperand;

	public PlusExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret() {
		return leftOperand.interpret() + rightOperand.interpret();
	}
}
