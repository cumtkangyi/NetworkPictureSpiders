package com.childhood.picture.spiders;

import java.util.HashSet;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ArrayList<User> list = new ArrayList<User>();
		HashSet<User> list = new HashSet<User>();
		list.add(new User("name1", "age1"));
		list.add(new User("name2", "age2"));
		list.add(new User("name3", "age3"));
		list.add(new User("name4", "age4"));
		list.add(new User("name5", "age5"));

		for (int i = 1; i < 5; i++) {
			User user = new User("name" + i, "age" + i);
			if (!list.contains(user)) {
				// list.add(user);
			}
			list.add(user);
		}

		for (User u : list) {
			System.out.println(u);
		}
	}

	static class User {
		String name;
		String age;

		public User(String name, String age) {
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "name: " + name + " , age: " + age;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (this == obj) {
				return true;
			}
			User other = (User) obj;
			if (this.name.equals(other.name)) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.name.hashCode();
		}
	}
}
