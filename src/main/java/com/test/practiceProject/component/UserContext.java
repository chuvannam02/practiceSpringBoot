package com.test.practiceProject.component;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Components  *
 * @Author: ChuVanNam
 * @Date: 10/2/2025
 * @Time: 12:05 AM
 */

public class UserContext {
	private static final java.lang.ThreadLocal<String> currentUser = new java.lang.ThreadLocal<>();

	public static void setUser(String user) {
		currentUser.set(user);
	}

	public static String getUser() {
		return currentUser.get();
	}

	// ❌ Nếu quên remove, data sẽ dính lại trên thread trong pool
	 public static void clear() { currentUser.remove(); }
}
