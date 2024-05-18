package white_heket.more_crustacean.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AllergicToCrab extends StatusEffect {
    protected AllergicToCrab(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED,"6c9857fc-2bc1-4a64-9195-c8db0b0b033d",-0.5, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
