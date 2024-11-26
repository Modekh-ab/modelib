package net.modekh.modelib.utils.messages;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.modekh.modelib.utils.messages.base.IMessageSender;

public enum ModeMessageSender implements IMessageSender {
    NO_ONE(Component.empty()),
    DEV(Component.literal("Modekh")),
    SERVER(Component.translatable("message.modelib.sender.server")),
    UNKNOWN(Component.literal("???")),
    UNKNOWN_1(Component.literal("??1")),
    UNKNOWN_2(Component.literal("??!")),
    UNKNOWN_3(Component.literal("?!!")),
    NOTIFICATION_SYSTEM(Component.translatable("message.modelib.sender.notification"), "gold"),
    NOTIFICATION_SYSTEM_AQUA(Component.translatable("message.modelib.sender.notification"), "aqua");

    private final MutableComponent name;
    private final String color;

    ModeMessageSender(MutableComponent name) {
        this.name = name;
        this.color = MessageUtils.NAME_COLOR;
    }

    ModeMessageSender(MutableComponent name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public MutableComponent get() {
        return this.name;
    }

    @Override
    public MutableComponent getName() {
        if (this.name.equals(ModeMessageSender.NO_ONE.get())) {
            return Component.empty();
        } else {
            if (this.ordinal() > UNKNOWN_3.ordinal()) {
                return MessageUtils.formatMessageSender(this.name,
                        this.color, this.color, "<>", "reset");
            }

            return MessageUtils.formatMessageSender(this.name);
        }
    }
}
