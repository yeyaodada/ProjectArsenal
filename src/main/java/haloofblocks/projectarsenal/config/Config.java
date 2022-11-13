package haloofblocks.projectarsenal.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Autovw
 */
public class Config
{
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static
    {
        final Pair<Client, ForgeConfigSpec> clientConfigPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientConfigPair.getRight();
        CLIENT = clientConfigPair.getLeft();
    }

    /**
     * Client config for Project Arsenal
     */
    public static class Client
    {
        public final GunTooltipInfo gunTooltipInfo;
        public final ForgeConfigSpec.BooleanValue enableGunEnchantmentGlint;

        public Client(ForgeConfigSpec.Builder builder)
        {
            builder.push("client");
            {
                this.gunTooltipInfo = new GunTooltipInfo(builder);

                this.enableGunEnchantmentGlint = builder
                        .comment("If true, renders enchanted guns from Project Arsenal with the purple enchantment glint. True by default.")
                        .define("enableGunEnchantmentGlint", true);
            }
            builder.pop();
        }

        public static class GunTooltipInfo
        {
            public final ForgeConfigSpec.BooleanValue showReloadAmount;
            public final ForgeConfigSpec.BooleanValue showFireRate;
            public final ForgeConfigSpec.BooleanValue showFireMode;
            public final ForgeConfigSpec.BooleanValue showProjectileSpeed;
            public final ForgeConfigSpec.BooleanValue showProjectileSpread;

            public GunTooltipInfo(ForgeConfigSpec.Builder builder)
            {
                builder.push("gun_tooltip_info");
                {
                    this.showReloadAmount = builder
                            .comment("If true, shows Reload Amount of the gun on the tooltip. True by default.")
                            .define("showReloadAmount", true);
                    this.showFireRate = builder
                            .comment("If true, shows Fire Rate of the gun on the tooltip. True by default.")
                            .define("showFireRate", true);
                    this.showFireMode = builder
                            .comment("If true, shows the gun's Fire Mode on the tooltip. True by default.")
                            .define("showFireMode", true);
                    this.showProjectileSpeed = builder
                            .comment("If true, shows the Projectile Speed of projectiles fired from the gun on the tooltip. False by default.")
                            .define("showProjectileSpeed", false);
                    this.showProjectileSpread = builder
                            .comment("If true, shows the Projectile Spread of projectiles fired from the gun on the tooltip. False by default.")
                            .define("showProjectileSpread", false);
                }
                builder.pop();
            }
        }
    }
}
