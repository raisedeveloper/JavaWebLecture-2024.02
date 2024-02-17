package ch99_PracticeProject;

import java.util.List;

import com.mysql.cj.protocol.Message;

public interface MessageDao {

	List<Message> getmessageList();
	
	Message getMessage(int mid);

	void insertMessage(Object message);
	
	void updateMessage(Message message);
	
	void deleteMessage(Message message);

	void insertMessage(Message message);
}