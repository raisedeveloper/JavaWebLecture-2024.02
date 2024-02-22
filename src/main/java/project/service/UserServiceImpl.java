package project.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import project.dao.UserDao;
import project.entity.User;

public class UserServiceImpl  implements UserService{

	private UserDao userDao = new UserDao();

	@Override
	public User getUserByUid(String uid) {
		User user = userDao.getUserByUid(uid);
		return user;
	}

	@Override
	public List<User> getUserList(int page) {
		int offset = (page - 1) * count_per_page;
        return userDao.getUserList(count_per_page, offset);
	}

	@Override
	public void registerUser(User user) {
		User u = userDao.getUserByUid(user.getUid());
		if (u != null) {
			return;
		}

		String hashedPwd = BCrypt.hashpw(user.getPwd(), BCrypt.gensalt());
		user.setPwd(hashedPwd);
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void deleteUser(String uid) {
		userDao.deleteUser(uid);
	}

	@Override
	public int login(String uid, String pwd) {		// business logic 이 들어간 곳
		User user = userDao.getUserByUid(uid);
		if (user==null) {
			return USER_NOT_EXIST;
		}
		if (BCrypt.checkpw(pwd, user.getPwd())) { // hashedPwd : 어떤 값인가? 변수로 알수있다. pwd 는 사용자가 입력한 plain text 우리가 들어가야하는건 hashed pwd 그건 DB에 들어있다. DB코드를 이미 가져왔으니 user에서 빼오면됨
			return correct_login;
		}
		return WRONG_PASSWORD;
	}

	@Override
	public int getUserCount() {
		return userDao.getUserCount();
	}


}
