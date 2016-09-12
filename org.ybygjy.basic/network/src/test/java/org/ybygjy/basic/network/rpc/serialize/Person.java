package org.ybygjy.basic.network.rpc.serialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

class Person implements Serializable {
    private String userName;
    private String password;
    private transient String privateKey;
    private static String level;
    private Map<String, String> extMap = new HashMap<String, String>();
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Map<String, String> getExtMap() {
        return extMap;
    }
    public void setExtMap(Map<String, String> extMap) {
        this.extMap = extMap;
    }
    public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    public static String getLevel() {
        return level;
    }
    public static void setLevel(String level) {
        Person.level = level;
    }
    
}
