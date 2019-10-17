package com.lyp.learn.guava.base;

import com.google.common.base.Throwables;
import org.junit.jupiter.api.Test;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-08 17:27
 *
 */
public class ThrowablesTest {

    @Test
    public void test1() {
        ThrowablesTest tester = new ThrowablesTest();
        try {
            tester.showcaseThrowables();
        } catch (InvalidInputException e) {
            //get the root cause
            System.out.println(Throwables.getRootCause(e));
        } catch (Exception e) {
            //get the stack trace in string format
            System.out.println(Throwables.getStackTraceAsString(e));
        }

        System.out.println("-----------------");
        try {
            tester.showcaseThrowables1();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }
    }

    public void showcaseThrowables() throws InvalidInputException {
        try {
            sqrt(-3.0);
        } catch (Throwable e) {
            //check the type of exception and throw it
            Throwables.propagateIfInstanceOf(e, InvalidInputException.class);
            Throwables.propagate(e);
        }
    }

    public void showcaseThrowables1() {
        try {
            int[] data = {1, 2, 3};
            getValue(data, 4);
        } catch (Throwable e) {
            Throwables.propagateIfInstanceOf(e, IndexOutOfBoundsException.class);
            Throwables.propagate(e);
        }
    }

    public double sqrt(double input) throws InvalidInputException {
        if (input < 0) {
            throw new InvalidInputException();
        }
        return Math.sqrt(input);
    }

    public double getValue(int[] list, int index) throws IndexOutOfBoundsException {
        return list[index];
    }

    class InvalidInputException extends Exception {
    }

}

