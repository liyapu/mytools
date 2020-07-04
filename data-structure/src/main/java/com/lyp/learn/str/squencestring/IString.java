package com.lyp.learn.str.squencestring;

public interface IString {
    /**
     * 将一个已经存在的串置成空串
     */
    void clear();

    /**
     * 判断当前串是否为空
     * @return 若为空，返回true; 否则，返回 false
     */
    boolean isEmpty();

    /**
     * 获取字符串长度
     * 返回串中的字符个数
     */
    int length();

    /**
     * 取字符操作
     * 读取并返回串中的第index [0,length-1]个字符值
     * @param index
     * @return
     */
    char charAt(int index) throws Exception;

    /**
     * 截取字符串
     * 返回值为当前串中 [begin,end) 的子串
     * @param begin 开始位置，包含， 范围 [0,length-1]
     * @param end 结束位置，不包含，范围 [1,length]
     * @return
     */
    IString subString(int begin, int end) throws Exception;

    /**
     * 插入操作
     * 在当前串的第offer个字符之前插入串str
     * @param offset [0,length]
     * @param str
     * @return
     */
    IString insert(int offset, IString str) throws Exception;

    /**
     * 删除当前串 [begin,end)的子串
     * @param begin 开始位置，包含，[0,length-1]
     * @param end 结束位置，不包含，[1,length)
     * @return
     */
    IString delete(int begin, int end) throws Exception;

    /**
     * 连接字符串
     * 把str串连接到当前串的后面
     * @param str
     * @return
     */
    IString concat(IString str) throws Exception;

    /**
     * 比较操作
     * 将当前串与目标串str进行比较
     * 若当前串大于 str,则返回一个正整数
     * 若当前串等于 str,则返回0
     * 若当前串小于 str,则返回一个负整数
     * @param str
     * @return
     */
    int compareTo(IString str) throws Exception;

    /**
     * 查找操作
     * 在当前串中从begin位置开始搜索与str相等的子串
     * 若搜索成功，则返回str在当前串的位置
     * 否则返回 -1
     *
     * @param str : 待匹配的字符串，不能为空串
     * @param begin
     * @return
     */
    int index(IString str, int begin) throws Exception;


    /**
     * 查找操作
     * Brute-Fource 算法
     * 在当前串中从begin位置开始搜索与str相等的子串
     * 若搜索成功，则返回str在当前串的位置
     * 否则返回 -1
     *
     * @param str : 待匹配的字符串，不能为空串
     * @param begin
     * @return
     */
    int index_BF(IString str, int begin) throws Exception;

    /**
     * 查找操作
     * KMP 算法
     * 在当前串中从begin位置开始搜索与str相等的子串
     * 若搜索成功，则返回str在当前串的位置
     * 否则返回 -1
     *
     * @param str : 待匹配的字符串，不能为空串
     * @param begin
     * @return
     */
    int index_KMP(IString str, int begin) throws Exception;


    /**
     * 展示
     */
    void display();
}
