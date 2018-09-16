package org.ybygjy.basic.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leye on 2017/8/3.
 */
public class RegexTest {
    private Pattern pattern = Pattern.compile("(\\d{4}(-\\d{2}){2}\\s\\d{2}(:\\d{2}){2}[\\.,]\\d+)[\\s\\|]?");
    private void doWork(String str) {
        Matcher matcher = pattern.matcher(str);
        System.out.println(pattern.pattern());
        while(matcher.find()) {
            System.out.println(matcher.group(1));
        }
        System.out.println(matcher.matches());
    }
    public static void main(String[] args) {
        File fileInst = new File("/Users/leye/tmp/t");
        StringBuffer fileContent = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileInst));
            String str = null;
            int i = 1;
            while ((str = bufferedReader.readLine()) != null) {
                fileContent.append(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RegexTest regexTest = new RegexTest();
        regexTest.doWork(fileContent.toString());
    }
}
