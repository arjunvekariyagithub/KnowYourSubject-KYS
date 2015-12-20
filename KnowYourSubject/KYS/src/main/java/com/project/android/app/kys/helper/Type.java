package com.project.android.app.kys.helper;

/**
 * Created by arjun on 11/26/15.
 */
public class Type {

    public static class UserType {
        public static final int ADMIN = 100;
        public static final int LOGIN = 200;
        public static final int GUEST = 300;

        private static int userType;

        public static void setUserType(int type) {
            userType = type;
        }

        public static boolean isAdmin() {
            return (userType == ADMIN);
        }

        public static boolean isLoginUser() {
            return (userType == LOGIN);
        }

        public  static boolean isGuest() {
            return (userType == GUEST);
        }
    }
}
