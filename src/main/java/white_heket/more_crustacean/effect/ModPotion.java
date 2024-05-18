package white_heket.more_crustacean.effect;

import white_heket.more_crustacean.item.ModItems;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;

public class ModPotion {
    public static Potion LONG_HAND_POTION = registerPotion("long_hand_potion",3600,0,ModEffects.LONG_HAND);
    public static Potion LONG_LONG_HAND_POTION = registerPotion("long_long_hand_potion",9600,0,ModEffects.LONG_HAND);
    public static Potion STRONG_LONG_HAND_POTION = registerPotion("strong_long_hand_potion",1800,1,ModEffects.LONG_HAND);
    public static Potion CRABS_TOUGHNESS_POTION = registerPotion("crabs_toughness_potion",3600,0,ModEffects.CRABS_TOUGHNESS);
    public static Potion LONG_CRABS_TOUGHNESS_POTION = registerPotion("long_crabs_toughness_potion",9600,0,ModEffects.CRABS_TOUGHNESS);
    public static Potion STRONG_CRABS_TOUGHNESS_POTION = registerPotion("strong_crabs_toughness_potion",1800,1,ModEffects.CRABS_TOUGHNESS);

    public static Potion registerPotion(String name, int duration, int amplifier, StatusEffect statusEffects){
        return Registry.register(Registries.POTION, new Identifier(MoreCrustacean.MOD_ID, name), new Potion(new StatusEffectInstance(statusEffects, duration, amplifier)));
    }
    public static void registerBrewingRecipe(){
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModItems.BLUE_LAND_CRAB_CLAW), ModPotion.LONG_HAND_POTION);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotion.LONG_HAND_POTION, Ingredient.ofItems(Items.REDSTONE), ModPotion.LONG_LONG_HAND_POTION);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotion.LONG_HAND_POTION, Ingredient.ofItems(Items.GLOWSTONE_DUST), ModPotion.STRONG_LONG_HAND_POTION);
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(ModItems.CRAB_SHELL), ModPotion.CRABS_TOUGHNESS_POTION);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotion.CRABS_TOUGHNESS_POTION, Ingredient.ofItems(Items.REDSTONE), ModPotion.LONG_CRABS_TOUGHNESS_POTION);
        FabricBrewingRecipeRegistry.registerPotionRecipe(ModPotion.CRABS_TOUGHNESS_POTION, Ingredient.ofItems(Items.GLOWSTONE_DUST), ModPotion.STRONG_CRABS_TOUGHNESS_POTION);
    }
}
