package am.itspace.smart_education.common.entity;

public enum Title {
    JAVA("Java"),
    JS("Java script"),
    C_PLUS("C++"),
    C("C"),
    PHP("Php"),
    PYTHON("Python");

    private final String displayName;

    Title(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
