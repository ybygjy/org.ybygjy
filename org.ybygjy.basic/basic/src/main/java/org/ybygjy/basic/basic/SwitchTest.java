package org.ybygjy.basic.basic;

/**
 * Created by leye on 2017/10/18.
 */
public class SwitchTest {
    public void doWork(EnumEntity enumEntity) {
        for (int i = 0; i < 100; i++) {
            switch (enumEntity) {
                case SUCCESS:
                    break;
                default:
                    continue;
            }
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        new SwitchTest().doWork(EnumEntity.FAILED);
        new SwitchTest().doWork(EnumEntity.SUCCESS);
        new SwitchTest().doWork(EnumEntity.UNKNOWN);
    }
}
enum EnumEntity {
    UNKNOWN, FAILED, SUCCESS
}
