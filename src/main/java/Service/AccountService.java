package Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.AccountDao;
import Model.Account;

public class AccountService {
    static final ObjectMapper om = new ObjectMapper();

    public static Account add(String body) {
        try {
            Account account = om.readValue(body, Account.class);
            System.out.println(account);
            if (!account.getUsername().isBlank() && account.getPassword().length() > 4){
                return AccountDao.add(account);
            } else throw new Exception();
        } catch (Exception e) {
            return null;
        }
    }

    public static Account get(String body) {
        try {
            return AccountDao.get(om.readValue(body, Account.class));
        } catch (Exception e) {
            return null;
        }
    } 
}
