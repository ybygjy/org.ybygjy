package org.ybygjy.hessian.service.impl;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.ybygjy.hessian.dto.UserInfo;
import org.ybygjy.hessian.service.UserService;

import sun.misc.Unsafe;

/**
 * UserServiceImpl
 * @author WangYanCheng
 * @version 2016年7月27日
 */
@SuppressWarnings("restriction")
public class UserServiceImpl implements UserService {
	private static Lock reentrantLocks = new ReentrantLock(false);
	private static Object lock = new Object();
    @Override
    public UserInfo getUserDetail(UserInfo userInfo) {
		reentrantLocks.lock();
		try {
			System.out.println("接收到的UserInfo=>" + userInfo);
			userInfo = new UserInfo();
			userInfo.setUserName("ExtKey1");
			userInfo.setUserNumber(String.valueOf(Math.random()));
			userInfo.setExtData("ext_key_1", "ext_key_1");
			userInfo.setExtData("ext_key_2", "ext_key_2");
			userInfo.setExtData3("ext_key_3", "ext_value_3");
			System.out.println("响应UserInfo=>" + userInfo);
			this.getUnsafeInst().park(true, 10000);
			synchronized(lock) {
				try {
	                lock.wait(1000);
                } catch (InterruptedException e) {
	                e.printStackTrace();
                }
			}
			return userInfo;
		} finally {
			reentrantLocks.unlock();
		}
    }
    private Unsafe getUnsafeInst() {
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
}
