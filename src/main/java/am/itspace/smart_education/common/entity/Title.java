package am.itspace.smart_education.common.entity;

public enum Title {
    JAVA("Java"),
    JS("Java script"),
    C_PLUS("C++"),
    C("C"),
    PHP("Php"),
    PYTHON("Python");

    private String displayName;

    Title(String displayName) {
        this.displayName = displayName;
    }
}
