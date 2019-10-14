package com.lyp.learn.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;
import com.lyp.learn.bean.Person;
import org.junit.jupiter.api.Test;

/**
 * 在介绍散列包中的类之前，让我们先来看下面这段代码范例：
 * HashFunction hf = Hashing.md5();
 * HashCode hc = hf.newHasher()
 *         .putLong(id)
 *         .putString(name, Charsets.UTF_8)
 *         .putObject(person, personFunnel)
 *         .hash();
 *
 * 1、HashFunction
 * HashFunction 是一个单纯的（引用透明的）、无状态的方法，它把任意的数据块映射到固定数目的位指，并且保证相同的输入一定产生相同的输出，不同的输入尽可能产生不同的输出。
 *
 * 2、Hasher
 * HashFunction 的实例可以提供有状态的 Hasher，Hasher提供了流畅的语法把数据添加到散列运算，然后获取散列值。Hasher 可以接受所有原生类型、字节数组、字节数组的片段、字符序列、特定字符集的字符序列等等，或者任何给定了 Funnel 实现的对象。
 * Hasher 实现了 PrimitiveSink 接口，这个接口为接受原生类型流的对象定义了 fluent 风格的API。
 *
 * 3、Funnel
 * Funnel 描述了如何把一个具体的对象类型分解为原生字段值，从而写入 PrimitiveSink。
 *
 * ==========================================
 * Hashing 类提供了若干散列函数，以及运算 HashCode 对象的工具方法。
 *
 * 1、已提供的散列函数
 *  md5()
 *  murmur3_128()
 *  murmur3_32()
 *  sha1()
 *  sha256()
 *  sha512()
 *
 */
public class HashingTest {

    @Test
    public void test() {
        String input = "hello, world";
        // 计算MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());

        System.out.println(Hashing.farmHashFingerprint64().hashBytes(input.getBytes()));
        System.out.println(Hashing.farmHashFingerprint64().hashUnencodedChars(input).toString());

        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.sha256().hashUnencodedChars(input).toString());

        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.sha512().hashUnencodedChars(input).toString());

        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.crc32().hashUnencodedChars(input).toString());

        // 计算adler32
        System.out.println(Hashing.adler32().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.adler32().hashUnencodedChars(input).toString());

        // 计算crc32c
        System.out.println(Hashing.crc32c().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.crc32c().hashUnencodedChars(input).toString());

        // 计算murmur3_32
        System.out.println(Hashing.murmur3_32().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.murmur3_32().hashUnencodedChars(input).toString());

        // 计算murmur3_128
        System.out.println(Hashing.murmur3_128().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.murmur3_128().hashUnencodedChars(input).toString());

        // 计算sha384
        System.out.println(Hashing.sha384().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.sha384().hashUnencodedChars(input).toString());

        // 计算sipHash24
        System.out.println(Hashing.sipHash24().hashBytes(input.getBytes()).toString());
        System.out.println(Hashing.sipHash24().hashUnencodedChars(input).toString());
    }

    @Test
    public void test2() {
//        HashFunction function_0 = Hashing.md5();
//        HashFunction function_1 = Hashing.md5();
        HashFunction function_0 = Hashing.murmur3_128();
        HashFunction function_1 = Hashing.murmur3_128();

        Person person = new Person();
        person.setAge(27);
        person.setName("hahahah");
        person.setAddress("北京三里屯");

        HashCode code_0 = function_0.newHasher()
                .putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .hash();

        HashCode code_1 = function_1.newHasher()
                .putInt(person.getAge())
                .putString(person.getName(), Charsets.UTF_8)
                .putString(person.getAddress(), Charsets.UTF_8)
                .hash();

        System.out.println(code_0.asLong());
        System.out.println(code_1.asLong());

        System.out.println(code_0.equals(code_1));
    }
}
