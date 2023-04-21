package com.ebanma.cloud.dao;

import com.ebanma.cloud.domain.Account;

public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}