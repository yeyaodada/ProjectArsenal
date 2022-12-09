package haloofblocks.projectarsenal.common;

import com.mrcrayfish.guns.common.Gun;

/**
 * @author Autovw
 */
public enum FireModes
{
    /**
     * Semi-Automatic fire mode.
     * Equivalent of {@link Gun.General#isAuto()} when false.
     */
    SEMI_AUTOMATIC,
    /**
     * Full-Automatic fire mode.
     * Equivalent of {@link Gun.General#isAuto()} when true.
     */
    FULL_AUTOMATIC,
    /**
     * Burst fire mode.
     * Fires 3 rounds (number can be changed through {@link FireMode#setBurstCount(int)}) before requiring input again.
     */
    BURST,
    /**
     * Safety fire mode that will prevent the weapon from firing a round.
     */
    SAFETY
}
