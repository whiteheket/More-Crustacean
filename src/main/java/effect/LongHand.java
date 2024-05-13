package effect;

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class LongHand extends StatusEffect {
    protected LongHand(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(ReachEntityAttributes.REACH,"dfbf7b21-4a28-43bf-a896-db2e29af1931",2, EntityAttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(ReachEntityAttributes.ATTACK_RANGE,"3a95376c-8219-43ee-a832-4c12d47419c8",0.35, EntityAttributeModifier.Operation.ADDITION);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier){
    }
}
