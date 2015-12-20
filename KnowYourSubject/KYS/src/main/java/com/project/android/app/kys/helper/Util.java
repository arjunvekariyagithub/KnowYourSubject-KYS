package com.project.android.app.kys.helper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private static Pattern pattern;
    private static Matcher matcher;
    //Email Pattern
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validate(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNotNull(String txt) {
        return (txt != null && txt.trim().length() > 0);
    }

    public static boolean isNull(String txt) {
        return (txt == null || txt.trim().length() <= 0);
    }

    public static String getInitials(String name) {
        if (name == null) return name;

        name = name.trim();

        if (name.length() == 1) return name;


        String ini = String.valueOf(name.charAt(0));
        if (name.indexOf(" ") < 0) {
            ini = name.substring(0, 1);
        } else {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) == ' ' && (i + 1 < name.length()) && name.charAt(i + 1) != ' ') {
                    ini += name.charAt(i + 1);
                    if(ini.length() == 3) {
                        break;
                    }
                }
            }
        }
        return ini.toUpperCase();
    }

    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
