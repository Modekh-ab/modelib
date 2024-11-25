package net.modekh.modelib;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ModeLib.MOD_ID)
public class ModeLib {
    public static final String MOD_ID = "modelib";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ModeLib() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
