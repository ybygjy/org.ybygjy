package org.hessian.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.ybygjy.hessian.dto.UserInfo;

import sun.misc.Unsafe;

public class UnsafeTest {
	public static Unsafe getUnsafeInst() {
		Field field;
        try {
	        field = Unsafe.class.getDeclaredField("theUnsafe");
	        field.setAccessible(true);
	        Unsafe unsafeInst = (Unsafe) field.get(null);
	        return unsafeInst;
        } catch (NoSuchFieldException e) {
	        e.printStackTrace();
        } catch (SecurityException e) {
	        e.printStackTrace();
        } catch (IllegalArgumentException e) {
	        e.printStackTrace();
        } catch (IllegalAccessException e) {
	        e.printStackTrace();
        }
        return null;
	}
	public static void main(String[] args) {
		Unsafe unsafe = UnsafeTest.getUnsafeInst();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String className = "org.ybygjy.hessian.dto.UserInfo";
		Class clazz = null;
        try {
	        clazz = classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        }
        Object objInst = null;
		try {
	        objInst = unsafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
	        e.printStackTrace();
        }
		Method[] methods = objInst.getClass().getMethods();
		for (Method method : methods) {
			if ("toString".equals(method.getName())) {
				try {
					Object obj = method.invoke(objInst, null);
					System.out.println(obj);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		UserInfo userInfo = new UserInfo();
		System.out.println(userInfo);
	}
}
