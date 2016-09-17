package org.ybygjy.spring.c2.impl;

import org.ybygjy.spring.c2.AccountingHelper;
import org.ybygjy.spring.c2.Accounting;

public abstract class GeneralAccounting implements Accounting {

    @Override
    public abstract AccountingHelper getAccountingHelper();

    @Override
    public void setExtAttr(String key, String value) {
        this.getAccountingHelper().transferAccounts();
    }
}
