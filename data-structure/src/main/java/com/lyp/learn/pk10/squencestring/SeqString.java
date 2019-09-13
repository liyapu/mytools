package com.lyp.learn.pk10.squencestring;

public class SeqString implements IString {
    //底层存储字符串的 char 型数据
    private char [] charElem;
    //当前实际使用长度
    private int curLength;

    //默认初始化长度
    private static int DEFAULT_LENGTH = 8;

    //初始化一个空串
    public SeqString(){
        charElem = new char[0];
        curLength = 0;
    }

    public SeqString(int length){
        charElem = new char[length];
        curLength = 0;
    }

    public SeqString(String str){
        curLength = str.length();
        charElem = new char[curLength];
        for(int i = 0; i < curLength; i++){
            charElem[i] = str.charAt(i);
        }
    }

    public SeqString(char [] value){
        int tempLength = value.length;
        //复制数组
        this.charElem = new char[tempLength];
        for(int i = 0; i < tempLength; i++){
            this.charElem[i] = value[i];
        }
        curLength = tempLength;
    }

    @Override
    public void clear() {
        charElem = new char[0];
        curLength = 0;
    }

    @Override
    public boolean isEmpty() {
        return curLength == 0;
    }

    @Override
    public int length() {
        return curLength;
    }

    @Override
    public char charAt(int index) throws Exception {
        if(index < 0 || index >= curLength){
            throw new Exception("charAt 元素下标越界 index = " + index);
        }
        return charElem[index];
    }

    @Override
    public IString subString(int begin, int end) throws Exception {
        if(begin < 0 || begin >= curLength - 1){
            throw new Exception("subString 下标越界 begin = " + begin);
        }
        if(end < 1 || end > curLength){
            throw new Exception("subString 下标越界 end = " + end);
        }
        if(begin >= end){
            throw  new Exception("subString 下标错误 begin = " + begin + ", end = " + end);
        }
        if(begin == 0 && end == curLength){
            return this;
        }else{
            int tempLength = end - begin;
            char [] tempChar = new char[tempLength];
            for(int i = begin; i < end; i++){
                tempChar[i-begin] = charElem[i];
            }
            return new SeqString(tempChar);
        }
    }

    @Override
    public IString insert(int offset, IString str) throws Exception {
        if(offset < 0 || offset > curLength){
            throw new Exception("insert 下标越界 offset = " + offset);
        }
        if(str == null){
            throw new Exception("insert 字符串不能为null");
        }
        int tempLength = str.length();
        int newLength = curLength + tempLength;
        char [] tempChar = new char[newLength];
        for(int i = 0; i < offset; i++){
            tempChar[i] = charElem[i];
        }
        for(int i = 0; i < tempLength; i++){
            tempChar[offset + i] = str.charAt(i);
        }
        int moveLength = curLength - offset;
        for(int i = 0; i < moveLength; i++){
            tempChar[offset + tempLength + i] = charElem[offset + i];
        }
        return new SeqString(tempChar);
    }

    @Override
    public IString delete(int begin, int end) throws Exception {
        if(begin < 0 ){
            throw new Exception("delete 下标错误 begin = " + begin);
        }
        if(end > curLength){
            throw new Exception("delete 下标错误，end = " + end);
        }
        if(begin > end){
            throw new Exception("delete 下标错误 begin = " + begin + " , end = " + end);
        }
        int count = end - begin;
        for(int i = 0 ;i < count && end + i < curLength; i++){
            charElem[begin + i] = charElem[end + i];
        }
        curLength = curLength - count;
        return this;
    }

    @Override
    public IString concat(IString str) throws Exception {
        int tempLength = str.length();
        int newLength = curLength + tempLength;
        char [] tempChar = new char [newLength];
        for(int i = 0; i < curLength; i++){
            tempChar[i] = charElem[i];
        }
        for(int i = 0; i < tempLength; i++){
            tempChar[curLength + i] = str.charAt(i);
        }
        return new SeqString(tempChar);
    }

    @Override
    public int compareTo(IString str) throws Exception {
        int len1 = curLength;
        int len2 = str.length();
        int min = Math.min(len1,len2);
        for(int i = 0 ; i < min; i++){
            if(charElem[i] != str.charAt(i)){
                return charElem[i] - str.charAt(i);
            }
        }
        return len1 - len2;
    }

    @Override
    public int index(IString str, int begin) throws Exception {
        if(begin < 0 || begin > curLength){
            throw  new Exception("index 下标错误 begin = " + begin);
        }
        int tempLength = str.length();
        int newLength = curLength - begin;
        if(tempLength > newLength){
            return -1;
        }
        boolean isFind = false;
        for(int i = begin; i + tempLength <= curLength; i++){
            for(int j = 0; j  < tempLength; j++){
                if(charElem[i + j] != str.charAt(j)){
                    isFind = false;
                    break;
                }
                if(j == tempLength-1){
                    isFind = true;
                }
            }
            if(isFind){
                return i ;
            }
        }

        return -1;
    }

    @Override
    public int index_BF(IString str, int begin) throws Exception {
        if(this == null || str == null || str.length() > this.length()){
            return -1;
        }

        //主串的长度
        int slen = curLength;
        //模式串的长度
        int tlen = str.length();
        int i = 0; //主串的下标
        int j = 0; //模式串的下标

        while( i < slen && j < tlen){
            if(charAt(i) == str.charAt(j)){
                i++;
                j++;
            }else{
                //继续比较主串中的下一个子串
                i = i - j + 1;
                j = 0;
            }
        }

        if(j >= tlen){
            //成功匹配，返回子串序号
            return i - tlen;
        }else{
            return  -1;
        }
    }

    @Override
    public int index_KMP(IString str, int begin) throws Exception {
        int [] next = getNextVal(str);
        int slen= this.length(); //主串长度
        int tlen = str.length(); //模式串长度
        int i = begin;   //主串指针
        int j = 0;       //模式串指针
        while(i < slen && j < tlen){
            //j== -1 表示S[i] != str[0]
            if(j == -1 || this.charAt(i) == str.charAt(j)){
                //比较下一个字符
                i++;
                j++;
            }else{
                //模式串后移
                j = next[j];
            }
        }

        if(j < tlen){
            return -1;
        }else{
            return i - tlen;
        }
    }

    private int [] getNextVal(IString  T) throws Exception {
        int [] nextVal = new int [T.length()];
        int j = 0;
        int k = -1;
        nextVal[0] = -1;
        while(j < T.length() -1){
            if(k == -1 || T.charAt(j) == T.charAt(k)){
                j++;
                k++;
                if(T.charAt(j) != T.charAt(k)){
                    nextVal[j] = k;
                }else{
                    nextVal[j] = nextVal[k];
                }
            }else{
                k = nextVal[k];
            }
        }
        return nextVal;
    }

    @Override
    public void display() {
        for(int i = 0; i < curLength; i++){
            System.out.print(charElem[i]);
        }
        System.out.println();
    }
}
