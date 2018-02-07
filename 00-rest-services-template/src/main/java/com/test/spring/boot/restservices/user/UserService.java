package com.test.spring.boot.restservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class UserService {
	private static List<User> users = new ArrayList<>();
	private static int counter = 100;
	static {
		users.add(new User(counter, "Adam", new Date()));
		users.add(new User(++counter, "John", new Date()));
		users.add(new User(++counter, "Chen", new Date()));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (Objects.isNull(user.getId()) || (user.getId() == 0)) {
			user.setId(++counter);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User delete(int id) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User user = it.next();
			if (user.getId() == id) {
				it.remove();
				return user;
			}
		}
		return null;
	}

}
