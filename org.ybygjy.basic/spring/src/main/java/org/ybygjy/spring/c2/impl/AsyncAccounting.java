package org.ybygjy.spring.c2.impl;

import org.ybygjy.spring.c2.AccountingHelper;
import org.ybygjy.spring.c2.Accounting;

public class AsyncAccounting implements Accounting {
    private AccountingHelper accountingHelper;
    public void setAccountingHelper(AccountingHelper accountingHelper) {
        this.accountingHelper = accountingHelper;
    }
    @Override
    public AccountingHelper getAccountingHelper() {
        return this.accountingHelper;
    }

    @Override
    public void setExtAttr(String key, String value) {
        this.accountingHelper.transferAccounts();
    }
}
