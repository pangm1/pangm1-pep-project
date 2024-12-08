package DAO;

import Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.h2.command.Prepared;

import Model.*;

public class AccountDao {

    public static Account add(Account account) {
        try {
            Connection db = ConnectionUtil.getConnection();

            if (get(account) != null) throw new Exception();

            PreparedStatement sql = db.prepareStatement("INSERT INTO account (username, password) VALUES (?, ?);");
            sql.setString(1, account.getUsername());
            sql.setString(2, account.getPassword());
            sql.executeUpdate();
            
            return get(account);
        } catch (Exception e) {
            return null;
        }
    }

    public static Account get(Account account) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("SELECT * FROM account WHERE username=? AND password=?;");
            sql.setString(1, account.getUsername());
            sql.setString(2, account.getPassword());

            ResultSet result = sql.executeQuery();
            result.next();
            return new Account(result.getInt("account_id"), result.getString("username"), result.getString("password"));
        } catch (Exception e) {
            return null;
        }
    }
    
}
