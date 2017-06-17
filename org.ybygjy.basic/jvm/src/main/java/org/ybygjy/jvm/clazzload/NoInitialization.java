package org.ybygjy.jvm.clazzload;

/**
 * Created by leye on 2017/5/14.
 */
public class NoInitialization {
    public static void main(String[] args) {
        /**　子类引用父类的静态字段不会导致子类初始化*/
        //System.out.println(SubClass.value);
        /** 通过数组定义来引用类不会导致此类的初始化*/
        //SuperClass[] superClasses = new SuperClass[10];
        /** 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化*/
        System.out.println(ConstantClass.SLOGAN_TEXT);
        //类加载过程需要讨论2种场景，非数组类的加载阶段和数组类的加载阶段，非数组类的加载阶段可控性很强，可通过自定义加载器处理；数组类加载器本身不通过类加载器创建，它是由
        //Java虚拟机直接创建的，但数组中的元素类型最终是要靠类加载器去创建，有以下规则:
        //-如果数组的组件类是引用类型，则走类加载器加载过程，数组使用加载该组件类的类加载器名称空间
        //-如果数据的组件类是基本类型，则Java虚拟机将会把数组标记为与引导类加载器关联
        SubClass[] subClasses = new SubClass[10];
        int[] basicTypeArr = new int[10];
        ClassLoader classLoader = basicTypeArr.getClass().getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }
}
