package com.chervonnaya.library.config.constants;

public class ValidationConst {
    public static final int MAX_LENGTH_NAME = 32;
    public static final int MAX_LENGTH_PEN_NAME = 64;
    public static final int MAX_LENGTH_BOOK_TITLE = 64;
    public static final int MAX_LENGTH_BOOK_DESCRIPTION = 2000;
    public static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
}
