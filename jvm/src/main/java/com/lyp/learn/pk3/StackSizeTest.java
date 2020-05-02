package com.lyp.learn.pk3;

/**
 * @author: liyapu
 * @description:
 * @date 2020-05-01 22:31
 *
 *   https://docs.oracle.com/en/java/javase/14/docs/specs/man/java.html
 *   -Xss size
 *       Sets the thread stack size (in bytes). Append the letter k or K to indicate KB,
 *       m or M to indicate MB, or g or G to indicate GB.
 *       The default value depends on the platform:
 *
 *      Linux/x64 (64-bit): 1024 KB
 *      macOS (64-bit): 1024 KB
 *      Oracle Solaris (64-bit): 1024 KB
 *      Windows: The default value depends on virtual memory
 *
 *      The following examples set the thread stack size to 1024 KB in different units:
 *         -Xss1m
 *         -Xss1024k
 *         -Xss1048576
 *
 *     -------------------------------
 *     测试:
 *       默认不设置栈大小    count = 6284
 *       -Xss256k         count = 1632
 *       -Xss2m           count = 12093
 *
 *
 */
public class StackSizeTest {
    private static  int count = 0;

    public static void main(String[] args) {
        count++;
        System.out.println("count = " + count);
        main(args);
    }
}
