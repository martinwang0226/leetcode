package com.example.lib.ArrayString;

/**
 * Create by chenlong.wang
 * on 2020/9/3
 * 利用字符重复出现的次数，编写一个方法，实现基本的字符串压缩功能。如字符串aabcccccaaa会变成a2b1c5a3。若压缩后的字符串没有变短，则返回原字符串。
 */
public class compress {

    /**
     * 直观的解法
     * @param str
     * @return
     */
    public String compressBad(String str)
    {
        String mystr = "";
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++)
        {
            if (str.charAt(i) ==last)
            {
                count++;
            }
            else
            {
                mystr += last + "" + count;
                last = str.charAt(i);
                count = 1;
            }
        }
        return mystr + last + count;
    }

    /**
     * 优化的解法
     * @param str
     * @return
     */
    String compressBetter(String str)
    {
        int size = countCompression(str);
        //长度检查
        if (size >= str.length())
        {
            return str;
        }

        StringBuffer mystr = new StringBuffer();
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++)
        {
            if (str.charAt(i) == last)
            {
                count++;
            }
            else
            {
                mystr.append(last);
                mystr.append(count);
                last = str.charAt(i);
                count = 1;
            }
        }

        mystr.append(last);
        mystr.append(count);
        return mystr.toString();
    }
    //专门用来数长度
    static int countCompression(String str)
    {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        char last = str.charAt(0);
        int size = 0;
        int count = 1;
        for (int i = 1; i <str.length(); i++)
        {
            if (str.charAt(i) == last)
            {
                count++;
            }
            else
            {
                last = str.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();
        return size;
    }

    public static String compressAlternate(String str) {
        int size = countCompression(str);
        if (size >= str.length()) {
            return str;
        }
        char[] array = new char[size];
        int index = 0;
        char last = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                index = setChar(array, last, index, count);
                last = str.charAt(i);
                count = 1;
            }
        }
        index = setChar(array, last, index, count);
        return String.valueOf(array);
    }

    public static int setChar(char[] array, char c, int index, int count) {
        array[index] = c;
        index++;
        char[] cnt = String.valueOf(count).toCharArray();
        for (char x : cnt) {
            array[index] = x;
            index++;
        }
        return index;
    }

}
