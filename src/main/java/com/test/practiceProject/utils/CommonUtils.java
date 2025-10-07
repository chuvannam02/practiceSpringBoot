package com.test.practiceProject.utils;
import org.apache.commons.lang3.StringUtils;
public class CommonUtils {
    public static String GENDER = "GENDER";

    public static String ETHNIC = "ETHNIC";

    public static String RELIGION = "RELIGION";

    public static String EDUCATION_LEVEL = "EDUCATION_LEVEL";

    public static String trimNullUpper(String k) {
        return StringUtils.upperCase(StringUtils.trimToNull(k));
    }
}
