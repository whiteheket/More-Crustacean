package white_heket.more_crustacean.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Map;

public class Xian extends StatusEffect {
    public Xian(StatusEffectCategory category, int color) {
        super(category, color);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier){
        if(entity.hasStatusEffect(StatusEffects.STRENGTH) && entity.getStatusEffect(StatusEffects.STRENGTH).getAmplifier()< 1){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,100,entity.getStatusEffect(ModEffects.XIAN).getAmplifier()+1,entity.getStatusEffect(StatusEffects.STRENGTH).isAmbient(),entity.getStatusEffect(StatusEffects.STRENGTH).shouldShowParticles()));
        }
        if(entity.hasStatusEffect(StatusEffects.REGENERATION) && entity.getStatusEffect(StatusEffects.REGENERATION).getAmplifier()< 1){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,100,entity.getStatusEffect(ModEffects.XIAN).getAmplifier()+1,entity.getStatusEffect(StatusEffects.REGENERATION).isAmbient(),entity.getStatusEffect(StatusEffects.REGENERATION).shouldShowParticles()));
        }
        if(entity.hasStatusEffect(StatusEffects.HASTE) && entity.getStatusEffect(StatusEffects.REGENERATION).getAmplifier()< 1){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,100,entity.getStatusEffect(ModEffects.XIAN).getAmplifier()+1,entity.getStatusEffect(StatusEffects.HASTE).isAmbient(),entity.getStatusEffect(StatusEffects.HASTE).shouldShowParticles()));
        }
        if(entity.hasStatusEffect(ModEffects.CRABS_TOUGHNESS) && entity.getStatusEffect(StatusEffects.REGENERATION).getAmplifier()< 1){
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.CRABS_TOUGHNESS,100,entity.getStatusEffect(ModEffects.XIAN).getAmplifier()+1,entity.getStatusEffect(ModEffects.CRABS_TOUGHNESS).isAmbient(),entity.getStatusEffect(ModEffects.CRABS_TOUGHNESS).shouldShowParticles()));
        }
    }
}