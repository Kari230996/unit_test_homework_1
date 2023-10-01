/*
 * Задание 1. ** В классе Calculator создайте метод calculateDiscount, который принимает сумму покупки и процент скидки и возвращает сумму с учетом скидки. 
 * Ваша задача - проверить этот метод с использованием библиотеки AssertJ. 
 * Если метод calculateDiscount получает недопустимые аргументы, он должен выбрасывать исключение ArithmeticException. 
 * Не забудьте написать тесты для проверки этого поведения.
 */


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Calculator {
    public double calculateDiscount(double purchaseAmount, double discountPercentage) {
        if (purchaseAmount < 0 || discountPercentage < 0 || discountPercentage > 100) {
            throw new ArithmeticException("Invalid arguments");
        }
        double discountAmount = purchaseAmount * (discountPercentage / 100);
        return purchaseAmount - discountAmount;
    }
}

public class CalculatorTest {
    @Test
    public void testCalculateDiscountWithValidArguments() {
        Calculator calculator = new Calculator();
        
        // Проверка скидки для суммы 100 и 10% скидки
        double result = calculator.calculateDiscount(100, 10);
        Assertions.assertThat(result).isEqualTo(90);
        
        // Проверка скидки для суммы 200 и 20% скидки
        result = calculator.calculateDiscount(200, 20);
        Assertions.assertThat(result).isEqualTo(160);
    }

    @Test
    public void testCalculateDiscountWithInvalidArguments() {
        Calculator calculator = new Calculator();

        // Попытка применить отрицательную скидку
        Assertions.assertThatThrownBy(() -> calculator.calculateDiscount(100, -10))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Invalid arguments");

        // Попытка применить слишком большую скидку
        Assertions.assertThatThrownBy(() -> calculator.calculateDiscount(100, 110))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Invalid arguments");

        // Попытка применить отрицательную сумму покупки
        Assertions.assertThatThrownBy(() -> calculator.calculateDiscount(-100, 10))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("Invalid arguments");
    }
}
