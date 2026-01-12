import static org.junit.jupiter.api.Assertions.*;

import org.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(8.0, calculator.add(5.0, 3.0), 0.0001);
        assertEquals(0.0, calculator.add(-5.0, 5.0), 0.0001);
    }

    @Test
    public void testSubtract() {
        assertEquals(2.0, calculator.subtract(5.0, 3.0), 0.0001);
        assertEquals(-10.0, calculator.subtract(-5.0, 5.0), 0.0001);
    }

    @Test
    public void testMultiply() {
        assertEquals(15.0, calculator.multiply(5.0, 3.0), 0.0001);
        assertEquals(0.0, calculator.multiply(5.0, 0.0), 0.0001);
    }

    @Test
    public void testDivide() {
        assertEquals(5.0, calculator.divide(10.0, 2.0), 0.0001);
        assertEquals(2.5, calculator.divide(5.0, 2.0), 0.0001);
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10.0, 0.0);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }
}
