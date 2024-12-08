package Service;

import java.util.List;

import DAO.MessageDao;
import Model.Message;

public class MessageService {
    public static Message add(String body) {
        // TODO
        return MessageDao.add(new Message(/* extract variables from json body */));
    }

    public static boolean getAll() {
        return MessageDao.getAll();
    }

    public static Message get(String body) {
        // TODO convert to integer, handle non-integers
        int message_id = -1;
        return MessageDao.get(message_id);
    }

    public static Message delete(String pathParam) {
        // TODO convert to integer, handle non-integers
        int message_id = -1;
        return MessageDao.delete(message_id);
    }

    public static Message update(String pathParam, String body) {
        // TODO convert to integer, handle non-integers
        int message_id = -1;
        return MessageDao.update(message_id, new Message(/* extract variables from json body */));
    }

    public static List<Message> getFromAccountID(String pathParam) {
        // TODO convert to integer, handle non-integers
        int account_id = -1;
        return MessageDao.queryAccountID(account_id);
    }
    
}
