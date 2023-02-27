package haloofblocks.projectarsenal.datagen;

import haloofblocks.projectarsenal.ProjectArsenal;
import haloofblocks.projectarsenal.core.registry.ArsenalSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

/**
 * Generator class for sound definitions.
 *
 * @author Autovw
 */
public class ArsenalSoundDefinitionsGenerator extends SoundDefinitionsProvider
{
    public ArsenalSoundDefinitionsGenerator(PackOutput packOutput, ExistingFileHelper helper)
    {
        super(packOutput, ProjectArsenal.MOD_ID, helper);
    }

    @Override
    public void registerSounds()
    {
        // ===== REGULAR FIRE ===== //
        regularFire(ArsenalSounds.AA_TWELVE.get(), "item/aa12");
        regularFire(ArsenalSounds.AUTO_NINE.get(), "item/auto9");
        regularFire(ArsenalSounds.DESERT_EAGLE.get(), "item/deserteagle");
        regularFire(ArsenalSounds.CLASSIC_DESERT_EAGLE.get(), "item/classic_desert_eagle");
        regularFire(ArsenalSounds.DP_TWENTY_SEVEN.get(), "item/dp27");
        regularFire(ArsenalSounds.GOLDEN_HAWK.get(), "item/goldenhawk");
        regularFire(ArsenalSounds.M_TWO_FOUR_NINE.get(), "item/m249");
        regularFire(ArsenalSounds.MOSSOU.get(), "item/mossou");
        regularFire(ArsenalSounds.MP_FIVE_A_FOUR.get(), "item/mp5a4");
        regularFire(ArsenalSounds.INTEGRAL_SUPPRESSION.get(), "item/integral_suppression");
        regularFire(ArsenalSounds.SHRIKE_FIRE.get(), "item/shrike");
        regularFire(ArsenalSounds.P_TWO_FIFTY.get(), "item/p250");
        regularFire(ArsenalSounds.MARKXIX.get(), "item/markxix");
        regularFire(ArsenalSounds.VECTOR.get(), "item/vector");
        regularFire(ArsenalSounds.CZ_SEVEN_FIVE.get(), "item/cz75");
        regularFire(ArsenalSounds.M_NINETEEN_ELEVEN.get(), "item/m1911");
        regularFire(ArsenalSounds.PRISMATIC.get(), "item/prismatic");
        regularFire(ArsenalSounds.SCARH.get(), "item/scarh");
        regularFire(ArsenalSounds.P_NINETY.get(), "item/p90");
        regularFire(ArsenalSounds.FAMAS.get(), "item/famas");
        regularFire(ArsenalSounds.MISC.get(), "item/misc");
        regularFire(ArsenalSounds.AUG_SMG.get(), "item/aug_smg");
        regularFire(ArsenalSounds.SMG_ONE.get(), "item/smg1");
        regularFire(ArsenalSounds.UNICA.get(), "item/unica");

        // ===== SUPPRESSED FIRE ===== //
        suppressedFire(ArsenalSounds.SHRIKE_SUPPRESSED.get(), "item/shrike_suppressed");
        suppressedFire(ArsenalSounds.VECTOR_SUPPRESSED.get(), "item/vector_suppressed");
        suppressedFire(ArsenalSounds.FAMAS_SUPPRESSED.get(), "item/famas_suppressed");
        suppressedFire(ArsenalSounds.MISC_SUPPRESSED.get(), "item/misc_suppressed");
        suppressedFire(ArsenalSounds.AUG_SMG_SUPPRESSED.get(), "item/aug_smg_suppressed");
        suppressedFire(ArsenalSounds.SMG_ONE_SUPPRESSED.get(), "item/smg1_suppressed");
        suppressedFire(ArsenalSounds.M_TWO_FOUR_NINE_SUPPRESSED.get(), "item/m249_suppressed");

        // ===== ENCHANTED FIRE ===== //

        // ===== MISCELLANEOUS ===== //
        addSound(ArsenalSounds.SWITCH_FIRE_MODE.get(), "subtitle.projectarsenal.switch_fire_mode", "item/switch_fire_mode");
        addSound(ArsenalSounds.GUNFIRE_BLOCKED.get(), "subtitle.projectarsenal.gunfire_blocked", "item/gunfire_blocked");
    }

    /**
     * Helper method for adding sounds to the sound definitions json
     * @param soundEvent The sound event
     * @param subtitle The sound subtitle
     * @param soundLoc The location of the sound
     */
    private void addSound(SoundEvent soundEvent, String subtitle, String soundLoc)
    {
        add(soundEvent, definition().subtitle(subtitle).with(sound(new ResourceLocation(ProjectArsenal.MOD_ID, soundLoc))));
    }

    private void regularFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.regular_fire", soundLoc);
    }

    private void suppressedFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.suppressed_fire", soundLoc);
    }

    private void enchantedFire(SoundEvent soundEvent, String soundLoc)
    {
        addSound(soundEvent, "subtitle.projectarsenal.enchanted_fire", soundLoc);
    }
}
