package com.kodilla.hibernate.manytomany.facade;

public class SearchProcessingException  extends Exception {
    public static String ERR_NO_ARGS = "No arguments";

    public SearchProcessingException(String message) {
        super(message);
    }
}
