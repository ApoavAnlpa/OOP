package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class CalcTest {
    private final Calc calcTest = new Calc();

    @Nested
    @DisplayName("Tests")
    class Step1Tests{
        @Disabled("Step 1")
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
    @Nested
    @DisplayName("Step 2")
    class Step2Tests{
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"1,2,3,4,5", "5,5,5", "3,3,3,3,3", "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1"})
        void DelimitersOrSymbols(String numbers) {
            assertEquals(calcTest.add(numbers), 15);
        }
    }

    @Nested
    @DisplayName("Step 3")
    class Step3Tests {
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"1,2\\n3", "1\\n2,3", "1\\n2\\n3"})
        void NewDelimiter(String numbers) {
            assertEquals(calcTest.add(numbers), 6);
        }
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"1,2,\\n3", "1\\n\\n2,3", ",\\n12,3", "1,\\n"})
        void DelimitersOrSymbols(String numbers) {
            assertThrows(IllegalArgumentException.class, () -> calcTest.add(numbers));
        }
    }

    @Nested
    @DisplayName("Step 4")
    class Step4Tests {
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"//*\\n1,2\\n2*1", "//e\\n1e2,2\\n1", "//+\\n1+2+1+2"})
        void NewDelimiter(String numbers) {
            assertEquals(calcTest.add(numbers), 6);
        }

    }

    @Nested
    @DisplayName("Step 5")
    class Step5Tests {
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"-1,-2,-3,-4", "1,2,3,-1,4"})
        void NegativeException(String numbers) {
            assertThrows(IllegalArgumentException.class, () -> calcTest.add(numbers));
        }
    }

    @Nested
    @DisplayName("Step 6")
    class Step6Tests {
        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = {"1000,999,1001", "1500,800,199,999,1"})
        void BiggerThanThousand(String numbers) {
            assertEquals(calcTest.add(numbers), 1999);
        }
    }
}