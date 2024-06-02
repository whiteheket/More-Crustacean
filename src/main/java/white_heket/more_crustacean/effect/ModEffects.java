package white_heket.more_crustacean.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;

public class ModEffects {
    public static final StatusEffect ALLERGIC_TO_CRAB = registerStatusEffect("allergic_to_crab", new AllergicToCrab(StatusEffectCategory.HARMFUL,0xa7684a));
    public static final StatusEffect LONG_HAND = registerStatusEffect("long_hand", new LongHand(StatusEffectCategory.BENEFICIAL,0x3c5184));
    public static final StatusEffect CRABS_TOUGHNESS = registerStatusEffect("crabs_toughness",new CrabsToughness(StatusEffectCategory.BENEFICIAL,0xa76c50));
    public static final StatusEffect XIAN = registerStatusEffect("xian",new Xian(StatusEffectCategory.BENEFICIAL,0x000000));

    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(MoreCrustacean.MOD_ID, name), statusEffect);
    }
    public static void init() {

    }
}
