package org.ybygjy.basic.algorithm.lightcode;

import java.util.*;

/**
 * 乱序字符串
 * <pre>
 *     给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)，如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序也不同的字符串也在S中。
 *     所有字符串都是小写字母
 * </pre>
 * Created by leye on 2017/1/25.
 */
public class AnagramString {
    /**
     * 算法主入口
     * @param strs args
     * @return rtnList
     */
    public List<String> anagram(String[] strs) {
        //1.构造Map
        //2.字符串排序
        //3.存储到Map，存下标
        //4.遍历Map，取下标数量大于1的
        Map<String, List<Integer>> dataMap = new HashMap<String, List<Integer>>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            byte[] bytes = str.getBytes();
            //数组排序
            Arrays.sort(bytes);
            str = new String(bytes);
            if (dataMap.containsKey(str)) {
                dataMap.get(str).add(i);
            } else {
                final int tmpI = i;
                dataMap.put(str, new ArrayList<Integer>(){
                    {
                        add(tmpI);
                    }
                });
            }
        }
        List<String> result = new ArrayList<String>();
        for (List<Integer> tmpList : dataMap.values()) {
            if (tmpList.size() > 1) {
                for (int i = 0; i < tmpList.size(); i++) {
                    result.add(strs[tmpList.get(i)]);
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        List<String> resultList = new AnagramString().anagram(new String[]{"lint", "intl", "inlt", "code"});
        System.out.println(Arrays.toString(resultList.toArray()));
    }
}
