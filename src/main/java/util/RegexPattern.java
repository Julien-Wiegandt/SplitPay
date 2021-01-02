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

    public static Pattern numberPattern = Pattern.compile("\\d{16}");
    public static Pattern nameOwnerPattern = Pattern.compile("[A-Z][a-z]*");
    public static Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    public static Pattern cvvPattern = Pattern.compile("\\d{3}");

    public static Pattern ibanPattern = Pattern.compile("^[a-zA-Z0-9@#$%^&-+=():;.,?/!§%*µ£#{}@]+$");
    public static Pattern bicPattern = Pattern.compile("^[a-zA-Z0-9@#$%^&-+=():;.,?/!§%*µ£#{}@]+$");
    public static Pattern labelPattern =Pattern.compile("^[a-zA-Z0-9@#$%^&-+=():;.,?/!§%*µ£#{}@]+$");
}