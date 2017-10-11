package Interpreter;

/**
 * В класі описана операція множення лівої операнди на праву
 */
public class MultiplicExpression implements Expression {
	Expression leftOperand;
	Expression rightOperand;

	public MultiplicExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret() {
		return leftOperand.interpret() * rightOperand.interpret();
	}
}
