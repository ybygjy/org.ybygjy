package org.ybygjy.basic.network.ntlm;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class DefaultAuthentication extends Authenticator {
    public DefaultAuthentication() {
        Authenticator.setDefault(this);
    }
    public PasswordAuthentication getPasswordAuthentication() {
        PasswordAuthentication passAuthentication = new PasswordAuthentication("USER", "PASSWORD".toCharArray());
        return passAuthentication;
    }
}
