package com.example.excel.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Date;
import java.util.*;

public class Util {
    private static Log log = LogFactory.getLog(Util.class);

    public static boolean isNullOrEmpty(String inStr) {
        return (inStr == null || inStr.trim().length() == 0);
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj instanceof Object[]) {
            Object[] o = (Object[]) obj;
            for (int i = 0; i < o.length; i++) {
                Object object = o[i];
                if (object instanceof Date) {
                    if (object.equals(new Date(0)))
                        return true;
                } else if ((object == null) || (("").equals(object))) {
                    return true;
                }
            }
        } else {
            if (obj instanceof Date) {
                if (obj.equals(new Date(0))) {
                    return true;
                }
            } else if (obj instanceof Map) {
                if (obj == null || ((Map) obj).isEmpty()) {
                    return true;
                }
            } else if (obj instanceof List) {
                if (obj == null || ((List) obj).isEmpty()) {
                    return true;
                }
            } else if ((obj == null) || (("").equals(obj))) {
                return true;
            }
        }
        return false;
    }
}
