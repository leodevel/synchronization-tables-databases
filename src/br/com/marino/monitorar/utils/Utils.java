package br.com.marino.monitorar.utils;

import br.com.marino.monitorar.enums.ColumnTypeEnum;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Utils {

    public static String toMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
            String sen = hash.toString(16);
            return sen;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public static boolean isNullOrEmpty(Object... values) {
        for (Object value : values) {
            if (value == null) {
                return true;
            }
            if (value instanceof String) {
                if (value.toString().trim().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Object getValue(Object target, List<String> properties) throws Exception {

        if (properties.size() == 1) {

            Field field = target.getClass().getDeclaredField(properties.get(0));
            field.setAccessible(true);
            return field.get(target);

        } else {

            Field field = target.getClass().getDeclaredField(properties.get(0));
            field.setAccessible(true);
            Object newTarget = field.get(target);
            properties.remove(0);

            return getValue(newTarget, properties);

        }

    }

    public static void setValue(Object target, Object value, List<String> properties) throws Exception {

        if (properties.size() == 1) {

            Field field = target.getClass().getDeclaredField(properties.get(0));
            field.setAccessible(true);
            field.set(target, value);

        } else {

            Field field = target.getClass().getDeclaredField(properties.get(0));
            field.setAccessible(true);
            Object newTarget = field.get(target);
            properties.remove(0);
            setValue(newTarget, value, properties);

        }

    }

    public static ColumnTypeEnum getColumnTypeEnum(String columnType) {
        String newColumnType = columnType.toLowerCase();
        switch (newColumnType) {
            case "int":
            case "integer":
                return ColumnTypeEnum.INTEGER;
            case "datetime":
            case "timestamp":
                return ColumnTypeEnum.DATETIME;
            case "float":
                return ColumnTypeEnum.FLOAT;
            case "double":
                return ColumnTypeEnum.DOUBLE;
            case "tinyint":
            case "boolean":
                return ColumnTypeEnum.BOOLEAN;
            case "text":
            case "varchar":
            case "string":
                return ColumnTypeEnum.TEXT;
            default:
                return null;
        }

    }

}
