package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Util.*;
import Model.*;


public class MessageDao {

    public static List<Message> getAll() {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("SELECT * FROM message;");

            ResultSet result = sql.executeQuery();
            List<Message> messages = new ArrayList<Message>();
            while (result.next()) messages.add(new Message(result.getInt("message_id"), result.getInt("posted_by"), result.getString("message_text"), result.getLong("time_posted_epoch")));
            return messages;
        } catch (Exception e) {
            return null;
        }
    }

    private static Message get(Message message) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("SELECT * FROM message WHERE posted_by = ? AND message_text = ? AND time_posted_epoch = ?;");
            sql.setInt(1, message.getPosted_by());
            sql.setString(2, message.getMessage_text());
            sql.setLong(3, message.getTime_posted_epoch());

            ResultSet result = sql.executeQuery();
            result.next();
            return new Message(result.getInt("message_id"), result.getInt("posted_by"), result.getString("message_text"), result.getLong("time_posted_epoch"));
        } catch (Exception e) {
            return null;
        }
    }

    public static Message getFromID(int message_id) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("SELECT * FROM message WHERE message_id = ?;");
            sql.setInt(1, message_id);

            ResultSet result = sql.executeQuery();
            result.next();
            return new Message(result.getInt("message_id"), result.getInt("posted_by"), result.getString("message_text"), result.getLong("time_posted_epoch"));
        } catch (Exception e) {
            return null;
        }
    }

    public static Message delete(int message_id) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("DELETE FROM message WHERE message_id = ?;");
            sql.setInt(1, message_id);

            Message toDelete = getFromID(message_id);
            if (toDelete != null) sql.executeUpdate();
            return toDelete;
        } catch (Exception e) {
            return null;
        }
    }

    public static Message add(Message message) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);");
            sql.setInt(1, message.getPosted_by());
            sql.setString(2, message.getMessage_text());
            sql.setLong(3, message.getTime_posted_epoch());

            if (AccountDao.idExists(message.getPosted_by())) sql.executeUpdate();
            
            return get(message);
        } catch (Exception e) {
            return null;
        }
    }

    public static Message update(int message_id, Message message) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("UPDATE message SET message_text = ? WHERE message_id = ?;");
            sql.setString(1, message.getMessage_text());
            sql.setInt(2, message_id);

            sql.executeUpdate();
            return getFromID(message_id);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Message> queryAccountID(int account_id) {
        try {
            Connection db = ConnectionUtil.getConnection();

            PreparedStatement sql = db.prepareStatement("SELECT * FROM message where posted_by = ?;");
            sql.setInt(1, account_id);

            ResultSet result = sql.executeQuery();
            List<Message> messages = new ArrayList<Message>();
            while (result.next()) messages.add(new Message(result.getInt("message_id"), result.getInt("posted_by"), result.getString("message_text"), result.getLong("time_posted_epoch")));
            return messages;
        } catch (Exception e) {
            return null;
        }
    }
    
}
