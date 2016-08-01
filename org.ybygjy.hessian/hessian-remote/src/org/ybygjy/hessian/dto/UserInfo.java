package org.ybygjy.hessian.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * UserInfo
 * @author WangYanCheng
 * @version 2016年7月26日
 */
public class UserInfo implements Serializable {

	/** serial number*/
    private static final long serialVersionUID = 1L;
    private String userName;
    private String userNumber;
    private Map<String, String> extData = new HashMap<String, String>();
    private Map<String, String> extData3 = new HashMap<String, String>();
    private int age = 20;
    private Map<String, String> extData2 = new HashMap<String, String>();
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
    public void setExtData(String key, String value) {
    	this.extData.put(key, value);
    }
    public String getExtDataValue(String key) {
    	return this.extData.get(key);
    }
    
    public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setExtData3(String key, String value) {
    	if (this.extData3 == null) {
    		System.out.println("ExtData3 NullpointerException");
    	} else {
    		this.extData3.put(key, value);
    	}
    }
    public String getExtDataValue3(String key) {
    	if (this.extData3 == null) {
    		System.out.println("ExtData3 NullpointerException");
    	} else {
    		return this.extData3.get(key);
    	}
    	return null;
    }
    
	public Map<String, String> getExtData2() {
		return extData2;
	}
	public void setExtData2(Map<String, String> extData2) {
		this.extData2 = extData2;
	}
	@Override
    public String toString() {
	    return "UserInfo [userName=" + userName + ", userNumber=" + userNumber + ", extData=" + extData + ", extData3=" + extData3
	            + ", age=" + age + ", extData2=" + extData2 + "]";
    }
}
