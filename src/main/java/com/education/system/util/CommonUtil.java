package com.education.system.util;

import java.util.ArrayList;
import java.util.List;

public class CommonUtil {

    public static <T> List<T> convertIterableToList(Iterable<T> iterable) {
        List<T> list = new ArrayList<>();
        for (T item : iterable) {
            list.add(item);
        }
        return list;
    }
}
