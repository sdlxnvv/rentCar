package uz.app.enums;

public enum CarColor {
    RED("Qizil"),
    BLUE("Ko'k"),
    BLACK("Qora"),
    WHITE("Oq"),
    GREY("Kulrang"),
    GREEN("Yashil"),
    YELLOW("Sariq"),
    BROWN("Jigarrang");

    private final String displayName;

    CarColor(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
