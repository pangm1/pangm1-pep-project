package Service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.MessageDao;
import Model.Message;

public class MessageService {
    static final ObjectMapper om = new ObjectMapper();

    public static Message add(String body) {
        try {
            Message message = om.readValue(body, Message.class);
            if (!message.getMessage_text().isBlank()) return MessageDao.add(message);
            else return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static List<Message> getAll() {
        return MessageDao.getAll();
    }

    public static Message get(String pathParam) {
        try {
            int message_id = Integer.parseInt(pathParam);
            return MessageDao.getFromID(message_id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Message delete(String pathParam) {
        try {
            int message_id = Integer.parseInt(pathParam);
            return MessageDao.delete(message_id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Message update(String pathParam, String body) {        
        try {
            int message_id = Integer.parseInt(pathParam);
            Message message = om.readValue(body, Message.class);
            if (!message.getMessage_text().isBlank()) return MessageDao.update(message_id, message);
            else return null;
        } catch (NumberFormatException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static List<Message> getFromAccountID(String pathParam) {   
        try {
            int account_id = Integer.parseInt(pathParam);
            return MessageDao.queryAccountID(account_id);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
}
