package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class CalcTest {
    private final Calc calcTest = new Calc();

    @Nested
    @DisplayName("Тести для першого кроку")
    class Step1Tests{
        @Test
        void moreThanTwoNumbers() {
            assertThrows(IllegalArgumentException.class, () -> calcTest.add("1,2,3"));
        }

        @Test
        void noNumbers() {
            assertEquals(calcTest.add(""), 0);
        }

        @Test
        void oneNumber() {
            assertEquals(calcTest.add("1"), 1);
        }

        @Test
        void twoNumbers() {
            assertEquals(calcTest.add("1,2"), 3);
            assertEquals(calcTest.add("-1,-2"), -3);
        }
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"1,,3", "2,d", "d,2", ",,23", ",2ad"})
        void DelimitersOrSymbols(String numbers) {
            assertThrows(IllegalArgumentException.class, () -> calcTest.add(numbers));
        }
    }





}