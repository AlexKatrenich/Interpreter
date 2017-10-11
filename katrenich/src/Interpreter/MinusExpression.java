package Interpreter;

/**
 * В класі описується операція віднімання правої операнди від лівої
 */
public class MinusExpression implements Expression {
	Expression leftOperand;
	Expression rightOperand;

	public MinusExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	public int interpret() {
		return leftOperand.interpret() - rightOperand.interpret();
	}
}
