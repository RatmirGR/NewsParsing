package com.ereed.webservice.utils;

public class JSONValidator {
    public static String getStringJSONValid(String strDate){
        int i = 0;

        if (strDate == null)
            return "";

        String[] arr = strDate.split("\"");
        for (String str : arr)
            if (str.contains("date"))
                i++;
        if (i > 1)
            return "";
        return strDate;
    }
}
