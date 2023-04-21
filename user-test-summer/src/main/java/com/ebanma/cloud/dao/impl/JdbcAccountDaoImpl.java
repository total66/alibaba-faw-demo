package com.ebanma.cloud.dao.impl;
  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;

import com.ebanma.cloud.dao.AccountDao;
import com.ebanma.cloud.domain.Account;
import com.ebanma.cloud.summer.util.ConnectionUtils;  
  
public class JdbcAccountDaoImpl implements AccountDao {
  
    private ConnectionUtils connectionUtils;  
  
    public void setConnectionUtils(ConnectionUtils connectionUtils) {  
        this.connectionUtils = connectionUtils;  
    }  
  
    public void init() {  
        System.out.println("初始化方法.....");  
    }  
  
    public void destory() {  
        System.out.println("销毁方法......");  
    }  
  
    @Override  
    public Account queryAccountByCardNo(String cardNo) throws Exception {  
        //从连接池获取连接  
        Connection con = connectionUtils.getCurrentThreadConn();  
        String sql = "select * from tb_account where cardNo=?";  
        PreparedStatement preparedStatement = con.prepareStatement(sql);  
        preparedStatement.setString(1,cardNo);  
        ResultSet resultSet = preparedStatement.executeQuery();  
  
        Account account = new Account();  
        while(resultSet.next()) {  
            account.setCardNo(resultSet.getString("cardNo"));  
            account.setName(resultSet.getString("name"));  
            account.setMoney(resultSet.getInt("money"));  
        }  
  
        resultSet.close();  
        preparedStatement.close();  
        //con.close();  
  
        return account;  
    }  
  
    @Override  
    public int updateAccountByCardNo(Account account) throws Exception {  
  
        // 从连接池获取连接  
        // 改造为：从当前线程当中获取绑定的connection连接  
        Connection con = connectionUtils.getCurrentThreadConn();  
        String sql = "update tb_account set money=? where cardNo=?";  
        PreparedStatement preparedStatement = con.prepareStatement(sql);  
        preparedStatement.setInt(1,account.getMoney());  
        preparedStatement.setString(2,account.getCardNo());  
        int i = preparedStatement.executeUpdate();  
  
        preparedStatement.close();  
        //con.close();  
        return i;  
    }  
}