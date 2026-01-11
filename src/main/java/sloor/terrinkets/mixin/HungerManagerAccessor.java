package sloor.terrinkets.mixin;

import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HungerManager.class)
public interface HungerManagerAccessor {
    // This creates a "getter" for the private exhaustion field
    @Accessor("exhaustion")
    float getExhaustion();

    // This creates a "setter" for the private exhaustion field
    @Accessor("exhaustion")
    void setExhaustion(float exhaustion);
}