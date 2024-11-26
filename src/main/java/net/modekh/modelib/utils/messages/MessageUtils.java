package net.modekh.modelib.utils.messages;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.modekh.modelib.utils.messages.base.IMessageSender;

import java.util.List;

public class MessageUtils {
    public static final String BRACKET_COLOR = "dark_lime";
    public static final String COLON_COLOR = "almond";
    public static final String NAME_COLOR = "lime";
    public static final String WHISPER_COLOR = "almond";
    public static final String SERVER_COLOR = "light_turquoise";
    public static final String DEV_COLOR = "golden_brown";

    public static void send(Player player, IMessageSender sender, MutableComponent message, String color, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (sender.equals(ModeMessageSender.NO_ONE)) {
                player.displayClientMessage(color.equals("none") ? message : format(message, color), false);
            } else {
                player.displayClientMessage(sender.getName()
                        .append(color.equals("none") ? message : format(message, color)), false);
            }
        }).start();
    }

    public static void send(Player player, IMessageSender sender, MutableComponent message, String color) {
        send(player, sender, message, color, 100);
    }

    public static void send(Player player, IMessageSender sender, MutableComponent message) {
        send(player, sender, message, "white", 100);
    }

    public static void send(Player player, IMessageSender sender, MutableComponent message, int delay) {
        send(player, sender, message, "white", delay);
    }

    public static MutableComponent build(IMessageSender sender, MutableComponent message, String color) {
        return sender.getName().append(color.equals("none") ? message : format(message, color));
    }

    public static MutableComponent build(IMessageSender sender, String message, String color) {
        return build(sender, Component.literal(message), color);
    }

    public static MutableComponent build(IMessageSender sender, MutableComponent message) {
        return build(sender, message, "none");
    }

    public static MutableComponent build(IMessageSender sender, String message) {
        return build(sender, Component.literal(message), "npne");
    }

    public static void addTooltip(List<Component> components, MutableComponent tooltip, String color) {
        components.add(format(tooltip, color));
    }

    public static void addTooltip(List<Component> components, MutableComponent tooltip) {
        addTooltip(components, tooltip, "none");
    }

    public static void addTooltip(List<Component> components, String tooltipTranslatable, String color) {
        addTooltip(components, Component.translatable(tooltipTranslatable), color);
    }

    public static void addTooltip(List<Component> components, String tooltip) {
        addTooltip(components, tooltip, "gray");
    }

    public static void addDefaultTooltip(List<Component> components) {
        if (Screen.hasShiftDown()) {
            MessageUtils.addRightClickTooltip(components);
        } else {
            MessageUtils.addShiftTooltip(components);
        }
    }

    public static void addShiftTooltip(List<Component> components) {
        addCommonTooltip(components, "tooltip.cheaks.hold_shift",
                "almond", "yellow_green", "shift");
    }

    public static void addRightClickTooltip (List<Component> components) {
        addCommonTooltip(components, "tooltip.cheaks.right_click",
                "sandy", "royal_blue", "right click");
    }

    private static void addCommonTooltip(List<Component> components, String tooltipTranslatable,
                                         String color, String keyColor, String specialWord) {
        MutableComponent toAdd = Component.empty();
        String[] toAddArray = Component.translatable(tooltipTranslatable).getString().split("_");

        for (String toAddElem : toAddArray) {
            String elemColor = toAddElem.toLowerCase().equals(specialWord) ? keyColor : color;

            toAdd.append(format(toAddElem, elemColor));
        }

        addTooltip(components, toAdd);
    }

    public static MutableComponent format(MutableComponent component, String formatting) {
        ChatFormatting chatFormatting = ChatFormatting.getByName(formatting.toUpperCase());

        if (chatFormatting != null) {
            return component.withStyle(chatFormatting);
        } else {
            int cheaksColorCode = ModeChatFormatting.getByName(formatting);

            return component.setStyle(component.getStyle().withColor(cheaksColorCode));
        }
    }

    public static MutableComponent format(String s, String formatting) {
        return format(Component.literal(s), formatting);
    }

    public static MutableComponent formatMessage(String username, String message) {
        MutableComponent output = Component.empty();
        String outputRaw = "[%username%]%:% %message%";

        for (String word : outputRaw.split("%")) {
            String color = switch (word) {
                case "[", "]" -> BRACKET_COLOR;
                case ":" -> COLON_COLOR;
                case "username" -> NAME_COLOR;
                default -> "none";
            };
            String toAppend = switch (word) {
                case "username" -> username;
                case "message" -> message;
                default -> word;
            };

            output.append(format(toAppend, color));
        }

        return output;
    }

    public static MutableComponent formatMessageSender(MutableComponent sender, String nameColor, String bracketColor,
                                                       String brackets, String colonColor) {
        return format(brackets.substring(0, 1), bracketColor)
                .append(format(sender, nameColor))
                .append(format(brackets.substring(1), bracketColor))
                .append(format(": ", colonColor));
    }

    public static MutableComponent formatMessageSender(MutableComponent sender) {
        return formatMessageSender(sender, NAME_COLOR, BRACKET_COLOR, "[]", COLON_COLOR);
    }

    public static Component clientChatComponent(Component messageComponent) {
        String message = messageComponent.getString();
        String[] messageArray = message.split("> ");

        if (!message.startsWith("<") || !message.contains("> ") || messageArray.length < 2)
            return messageComponent;

        String username = messageArray[0].substring(1);
        String messageNew = message.replace(messageArray[0] + "> ", "");

        return formatMessage(username, messageNew);
    }
}
