import java.sql.SQLException;

import com.mysql.admin.dao.UserDao;
import com.mysql.admin.model.User;

public class Main {

	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		try {
			User user = userDao.fetchAdmin("joanvasquez", "Password2288_");
			if (user != null) {
				System.out.println("User name --> " + user.getName());
				System.out.println("User password --> " + user.getPassword());
			} else {
				System.out.println("User not found...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
