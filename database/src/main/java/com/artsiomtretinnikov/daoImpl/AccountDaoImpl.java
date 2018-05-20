package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.AccountDao;
import com.artsiomtretinnikov.entity.Account;

public final class AccountDaoImpl extends BaseDaoImpl<Long, Account> implements AccountDao {

    private static final AccountDaoImpl INSTANCE = new AccountDaoImpl(Account.class);

    private AccountDaoImpl(Class<Account> clazz) {
        super(clazz);
    }

    public static AccountDaoImpl getInstance() {
        return INSTANCE;
    }
}
