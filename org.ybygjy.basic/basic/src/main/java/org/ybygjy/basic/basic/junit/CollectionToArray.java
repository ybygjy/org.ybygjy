package org.ybygjy.basic.basic.junit;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by leye on 2017/10/7.
 */
public class CollectionToArray {
    @Test
    public void collectionToArrayTest() {
        Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
        for (int i = 0; i < 10; i++) {
            dataMap.put(String.valueOf(i), null);
        }
        final String[] strArr = dataMap.keySet().toArray(new String[0]);
        System.out.println(Arrays.toString(strArr));
        List<String> dataList = new ArrayList<>(10);
        ThreadLocalRandom.current().ints(strArr.length * 10, 0, strArr.length).distinct().limit(strArr.length).forEach(s->{
            dataList.add(strArr[s]);
        });
        System.out.println(dataList);
    }
}
