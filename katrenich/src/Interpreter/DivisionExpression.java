package Interpreter;

/**
 * В класі описана операція ділення лівої операнди на праву.
 */
public class DivisionExpression implements Expression {
	Expression leftOperand;
	Expression rightOperand;

	public DivisionExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret() {
		return leftOperand.interpret() / rightOperand.interpret();
	}
}
