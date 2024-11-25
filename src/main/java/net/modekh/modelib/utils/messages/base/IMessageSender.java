package net.modekh.modelib.utils.messages.base;

import net.minecraft.network.chat.MutableComponent;

public interface IMessageSender {
    MutableComponent get();
    MutableComponent getName();
}
