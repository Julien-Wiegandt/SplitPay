package util;

import java.util.regex.Pattern;

public class RegexPattern {
    public static Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    public static Pattern phonePattern = Pattern.compile("^[0-9]+$");
    public static Pattern textPattern = Pattern.compile("^[a-zA-Z]+$");
    public static Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9@#$%^&-+=():;.,?/!§%*µ£#{}@]+$");
    public static Pattern nicknamePattern = Pattern.compile("^[a-zA-Z0-9@#$%^&-+=():;.,?/!§%*µ£#{}@]+$");
    public static Pattern siretPattern = Pattern.compile("^[a-zA-Z0-9]{14}$");
    public static Pattern decimalPattern = Pattern.compile("^[0-9]{1,9}([,.][0-9]{1,2})?$");
}