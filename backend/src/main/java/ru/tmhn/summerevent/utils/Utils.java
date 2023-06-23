package ru.tmhn.summerevent.utils;

public class Utils {

    public static Byte bool2byte(Boolean value) {
        if (value == null) return null;
        return value ? (byte) 1 : (byte) 0;
    }

    public static Boolean byte2bool(Byte value) {
        if (value == null) return null;
        return value == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }
}
