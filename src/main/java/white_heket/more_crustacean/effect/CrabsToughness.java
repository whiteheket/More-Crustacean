package white_heket.more_crustacean.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CrabsToughness extends StatusEffect {
    protected CrabsToughness(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, "a297295c-5db2-44ff-8308-f4fdb333112e", 0.3, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, "ccbdeebe-4c1b-44ae-aa80-924abaae1ca3", 0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier){
    }
}
