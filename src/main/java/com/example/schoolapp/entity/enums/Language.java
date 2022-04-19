package com.example.schoolapp.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum Language {
    UZBEK("uzbek"), RUSSIAN("russian"), ENGLISH("english");

    Language(String lang) {
        this.lang = lang;
    }

    private final String lang;

    public String getLang() {
        return lang;
    }

    private static final Map<String, Language> map;

    static {
        map = new HashMap<String, Language>();
        for (Language v : Language.values()) {
            map.put(v.lang, v);
        }
    }

    public static Language findByKey(String i) {
        return map.get(i);
    }
}
