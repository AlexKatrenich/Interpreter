package Interpreter;

/**
 * Клас описує вираз піднесення лівої операнди в степінь що характеризує права операнда
 */
public class ExpExpression implements Expression {
	Expression leftOperand;
	Expression rightOperand;

	public ExpExpression(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}


	@Override
	public int interpret() {
		return (int) Math.pow(leftOperand.interpret(), rightOperand.interpret());
	}
}
