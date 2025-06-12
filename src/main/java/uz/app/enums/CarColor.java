package uz.app.enums;

import lombok.Getter;

@Getter
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

}
