package org.ybygjy.basic.basic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Created by MLS on 2016-11-06.
 */
public class AtomicReferenceStampTest {
    public static void main(String[] args) {
        DBEntity dbEntity = new DBEntity();
        AtomicStampedReference<DBEntity> atomicStampedReference = new AtomicStampedReference<DBEntity>(dbEntity, Integer.MAX_VALUE);
        atomicStampedReference.attemptStamp(dbEntity, 1);
    }
    static class DBEntity {
        private String userName;
        private String password;
        private String connUrl;

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

        public String getConnUrl() {
            return connUrl;
        }

        public void setConnUrl(String connUrl) {
            this.connUrl = connUrl;
        }
    }
}

