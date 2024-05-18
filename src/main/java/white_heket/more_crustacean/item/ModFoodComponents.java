package white_heket.more_crustacean.item;

import white_heket.more_crustacean.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent CRAB_MEAT;
    public static final FoodComponent COOKED_CRAB_MEAT;
    public static final FoodComponent CRAB_BUTTER;
    public static final FoodComponent BROWN_CRAB;
    public static final FoodComponent SWIMMER_CRAB;
    public static final FoodComponent HAIRY_CRAB;
    public static final FoodComponent GIANT_MUD_CRAB;
    public static final FoodComponent KING_CRAB;
    public static final FoodComponent CLAWSTER;
    public static final FoodComponent COOKED_BROWN_CRAB;
    public static final FoodComponent COOKED_SWIMMER_CRAB;
    public static final FoodComponent COOKED_HAIRY_CRAB;
    public static final FoodComponent COOKED_GIANT_MUD_CRAB;
    public static final FoodComponent COOKED_KING_CRAB;
    public static final FoodComponent COOKED_CLAWSTER;

    static {
        CRAB_MEAT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB, 600, 0), 0.3F).meat().build();
        COOKED_CRAB_MEAT = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.6F).meat().build();
        CRAB_BUTTER = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED,300,0),1.0F).meat().build();
        BROWN_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        SWIMMER_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        HAIRY_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED,300,0),0.4F).meat().build();
        GIANT_MUD_CRAB = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        KING_CRAB = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.5F).meat().build();
        CLAWSTER =(new FoodComponent.Builder()).hunger(5).saturationModifier(0.3F).meat().build();
        COOKED_BROWN_CRAB = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).meat().build();
        COOKED_SWIMMER_CRAB = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).meat().build();
        COOKED_HAIRY_CRAB = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED,300,0),0.9F).meat().build();
        COOKED_GIANT_MUD_CRAB = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.8F).meat().build();
        COOKED_KING_CRAB = (new FoodComponent.Builder()).hunger(8).saturationModifier(1.0F).meat().build();
        COOKED_CLAWSTER = (new FoodComponent.Builder()).hunger(8).saturationModifier(1.0F).meat().build();
    }

}
