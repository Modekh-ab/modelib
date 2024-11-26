package net.modekh.modelib.utils.messages;

import net.minecraft.ChatFormatting;

public enum ModeChatFormatting {
    LIGHT_DEEP_GREEN("LIGHT_DEEP_GREEN", "007A3F"),
    LIGHT_TURQUOISE("LIGHT_TURQUOISE", "40E0D0"),
    ROYAL_BLUE("ROYAL_BLUE", "4169E1"),
    UNIQUE_DARK_RED("UNIQUE_DARK_RED", "640D0D"),
    DARK_PISTACHIO("DARK_PISTACHIO", "69A147"),
    GOLDEN_BROWN("GOLDEN_BROWN", "712F26"),
    DARK_YELLOW_GREEN("DARK_YELLOW_GREEN", "7AA327"),
    DARK_LIME("DARK_LIME", "84B701"),
    YELLOW_GREEN("YELLOW_GREEN", "9ACD32"),
    PISTACHIO("PISTACHIO", "BEF574"),
    LIME("LIME", "BFFF00"),
    UNIQUE_LIGHT_PURPLE("UNIQUE_LIGHT_PURPLE", "C088EB"),
    SANDY("SANDY", "dabdab"),
    ALMOND("ALMOND", "EFDECD"),
    DARK_ALMOND("DARK_ALMOND", "F7AE72"),
    TRUE_GOLD("TRUE_GOLD", "FFD700");

    private final String name;
    private final String codeHex;
    private final int code;

    ModeChatFormatting(String colorName, String colorHexCode) {
        this.name = colorName;
        this.code = toDec(colorHexCode);
        this.codeHex = colorHexCode.toUpperCase();
    }

    public int getColorCode() {
        return this.code;
    }

    public String getColorHex() {
        return "#" + this.codeHex;
    }

    public static int getByName(String name) {
        for (ModeChatFormatting format : values()) {
            if (name.toUpperCase().equals(format.name))
                return format.code;
        }

        return ChatFormatting.WHITE.getColor().intValue();
    }

    private static int toDec(String hex) {
        int dec = 0;

        for (int i = 0; i < hex.length(); i++) {
            dec = 16 * dec + Character.digit(hex.charAt(i), 16);
        }

        return dec;
    }
}
