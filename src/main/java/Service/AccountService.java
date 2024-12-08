package Service;

import java.util.List;

import DAO.AccountDao;
import Model.Account;

public class AccountService {

    public static Account add(String body) {
        // TODO
        return AccountDao.add(new Account(/* extract variables from json body */));

    }

    public static Account get(String body) {
        // TODO
        return AccountDao.get(new Account(/* extract variables from json body */));
    }

    
}
