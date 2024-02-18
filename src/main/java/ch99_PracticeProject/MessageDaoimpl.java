package ch99_PracticeProject;

import java.sql.Connection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.protocol.Message;

public class MessageDaoimpl implements MessageDao {
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/\" + \"jdbc/world\"");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> getmessageList() {
		return null;
	}

	@Override
	public Message getMessage(int mid) {
		return null;
	}

	@Override
	public void insertMessage(Object message) {
	}

	@Override
	public void updateMessage(Message message) {
	}

	@Override
	public void deleteMessage(Message message) {
	}

	@Override
	public void insertMessage(Message message) {
	}
}