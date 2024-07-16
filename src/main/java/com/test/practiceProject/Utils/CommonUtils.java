package com.test.practiceProject.Utils;
import org.apache.commons.lang3.StringUtils;
public class CommonUtils {
    public static String trimNullUpper(String k) {
        return StringUtils.upperCase(StringUtils.trimToNull(k));
    }
}
