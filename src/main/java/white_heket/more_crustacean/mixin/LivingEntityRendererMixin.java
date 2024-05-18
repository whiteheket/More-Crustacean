package white_heket.more_crustacean.mixin;

import white_heket.more_crustacean.effect.ModEffects;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin{
    @Inject(at = @At("RETURN"),method = "isShaking",cancellable = true)
    public void isShaking(LivingEntity entity, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(cir.getReturnValue() || entity.hasStatusEffect(ModEffects.ALLERGIC_TO_CRAB));
    }
}
