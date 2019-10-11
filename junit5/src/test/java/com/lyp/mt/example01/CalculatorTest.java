package com.lyp.mt.example01;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-09 13:24
 *
 * https://blog.csdn.net/ryo1060732496/article/details/80792246
 *
 * https://junit.org/junit5/docs/current/user-guide/#overview
 *
 */
class CalculatorTest {
    /**
     * 只执行一次，执行时机是在所有测试和 @BeforeEach 注解方法之前
     *
     * 因为框架会为每个测试创建一个单独的实例，
     * 在 @BeforeAll/@AfterAll 方法执行时尚无任何测试实例诞生。
     * 因此，这两个方法必须定义为静态方法。
     */
    @BeforeAll
    static void init(){
        System.out.println("init......BeforeAll.......");
    }

    /**
     * 只执行一次，执行时机是在所有测试和 @AfterEach 注解方法之后
     */
    @AfterAll
    static void clean(){
        System.out.println("clean....AfterAll......");
    }

    /**
     * 在每个测试执行之前执行
     */
    @BeforeEach
    void setUp() {
        System.out.println("setUp.......BeforeEach.....");
    }

    /**
     * 在每个测试执行之后执行
     */
    @AfterEach
    void tearDown() {
        System.out.println("tearDown....AfterEach.....");
    }

    @Test
    void add() {
        System.out.println("add .......");
    }


    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbers() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "0,    1,   1",
            "1,    2,   3",
            "49,  51, 100",
            "1,  100, 101"
    })
    void add(int first, int second, int expectedResult) {
        Calculator calculator = new Calculator();
        assertEquals(expectedResult, calculator.add(first, second),
                () -> first + " + " + second + " should equal " + expectedResult);
    }


    //=======断言=================
    @Test
    void standardAssertions(){
        assertEquals(2,2);
        assertEquals(2,2,"number not  is 2");
        assertNotEquals(2,3);
    }

    @Test
    public void testNull(){
        assertNull(null);
        assertNull(null,"must be null");
        assertNotNull(100);
    }

    @Test
    public void testBool(){
        assertTrue(2 == 2);
        assertTrue(2 == 3);
    }

    @Test
    public void groupedAssertions(){
        // In a grouped assertion all assertions are executed, and any
        // failures will be reported together.
        String  str = "aabbcc";
        assertAll(str,
                () -> assertEquals("aabbcc",str),
                () -> assertTrue(str.length() > 5),
                () -> assertTrue(str.startsWith("aa"))
        );
    }


    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        String str = "aabbcc";
        assertAll("properties",
                () -> {
//                    String firstName = person.getFirstName();
                    String firstName = str;
                    assertNotNull(firstName);

                    // Executed only if the previous assertion is valid.
                    assertAll("first name",
                            () -> assertTrue(firstName.startsWith("a")),
                            () -> assertTrue(firstName.endsWith("c"))
                    );
                },
                () -> {
                    // Grouped assertion, so processed independently
                    // of results of first name assertions.
//                    String lastName = person.getLastName();
                    String lastName = str.substring(2,4);
                    assertNotNull(lastName);

                    // Executed only if the previous assertion is valid.
                    assertAll("last name",
                            () -> assertTrue(lastName.startsWith("b")),
                            () -> assertTrue(lastName.endsWith("b"))
                    );
                }
        );
    }

    @Test
    void testException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("a message");
        });
        assertEquals("a message", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(Duration.ofSeconds(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(Duration.ofSeconds(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

}