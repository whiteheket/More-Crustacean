package white_heket.more_crustacean.item;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import white_heket.more_crustacean.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

import java.util.ResourceBundle;
import java.util.function.Supplier;

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
    public static final FoodComponent LOBSTER;
    public static final FoodComponent COOKED_BROWN_CRAB;
    public static final FoodComponent COOKED_SWIMMER_CRAB;
    public static final FoodComponent COOKED_HAIRY_CRAB;
    public static final FoodComponent COOKED_GIANT_MUD_CRAB;
    public static final FoodComponent COOKED_KING_CRAB;
    public static final FoodComponent COOKED_CLAWSTER;
    public static final FoodComponent COOKED_LOBSTER;
    public static final FoodComponent OYSTER;
    //菜肴
    public static final FoodComponent CLAM_CHOWDER;
    public static final FoodComponent SPICY_CRAYFISH;
    public static final FoodComponent CRAB_CAKE;
    public static final FoodComponent CRAB_BUTTER_SOUP_DUMPLING;
    public static final FoodComponent WATER_CRAB_PORRIDGE;
    public static final FoodComponent MARINATED_CRAB;

;
    static {
        CRAB_MEAT = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB, 600, 0), 0.3F).meat().build();
        COOKED_CRAB_MEAT = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.6F).meat().build();
        CRAB_BUTTER = (new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,300,0),1.0F).meat().build();
        BROWN_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        SWIMMER_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        HAIRY_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED,300,0),0.4F).meat().build();
        GIANT_MUD_CRAB = (new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.3F).meat().build();
        KING_CRAB = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,200,0),0.5F).meat().build();
        CLAWSTER =(new FoodComponent.Builder()).hunger(5).saturationModifier(0.3F).meat().build();
        LOBSTER =(new FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).meat().build();
        OYSTER =(new FoodComponent.Builder()).hunger(2).saturationModifier(0.2F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,100,0),0.6F).build();
        COOKED_BROWN_CRAB = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).meat().build();
        COOKED_SWIMMER_CRAB = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,300,0),0.3F).meat().build();
        COOKED_HAIRY_CRAB = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,300,0),0.6F).meat().build();
        COOKED_GIANT_MUD_CRAB = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.8F).meat().build();
        COOKED_KING_CRAB = (new FoodComponent.Builder()).hunger(8).saturationModifier(1.0F).meat().build();
        COOKED_CLAWSTER = (new FoodComponent.Builder()).hunger(8).saturationModifier(1.0F).meat().build();
        COOKED_LOBSTER = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.8F).meat().build();
        //菜谱
        CLAM_CHOWDER = (new FoodComponent.Builder()).hunger(6).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,7200,0),1.0F).statusEffect(Effects.COMFORT.get(),1.0F).build();
        SPICY_CRAYFISH = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,3600,0),1.0F).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,3600,0),1.0F).build();
        CRAB_CAKE = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED,14400,0),1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,14400,0),1.0F).build();
        CRAB_BUTTER_SOUP_DUMPLING = (new FoodComponent.Builder()).hunger(5).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,8000,0),1.0F).build();
        WATER_CRAB_PORRIDGE = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,3600,1),1.0F).statusEffect(Effects.COMFORT.get(),1.0F).build();
        MARINATED_CRAB = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.3F).statusEffect(new StatusEffectInstance(ModEffects.XIAN,3600,1),1.0F).statusEffect(new StatusEffectInstance(ModEffects.ALLERGIC_TO_CRAB,3600,0),0.5F).build();
    }
    private static final class Effects{
        private static final Supplier<StatusEffectInstance> NOURISHMENT = add("farmersdelight:nourishment", 6000, 0);
        private static final Supplier<StatusEffectInstance> COMFORT = add("farmersdelight:comfort", 3600, 0);
        private static final Supplier<StatusEffectInstance> COMFORT_LONG = add("farmersdelight:comfort", 8000, 0);
        @Nullable
        private static Supplier<StatusEffectInstance> add(String id, int duration, int amplifier){
            return () -> {
                StatusEffect effect = Registries.STATUS_EFFECT.get(Identifier.tryParse(id));
                if (effect == null) {
                    effect = ModEffects.NULL;
                }
                return new StatusEffectInstance(effect, duration, amplifier);
            };
        }
    }
}
