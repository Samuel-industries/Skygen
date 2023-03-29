package pl.samuel.skygen.utils;

import org.bukkit.entity.*;
import org.bukkit.potion.*;

import java.util.ArrayList;
import java.util.Collection;

public class PotionUtil {
	public static Collection<PotionEffect> deserializePotionEffects(String potionEffectString) {
		final Collection<PotionEffect> rtrn = new ArrayList<PotionEffect>();
		potionEffectString = potionEffectString.replace(";", "");
		String[] split;
		for (int length = (split = potionEffectString.split("e@")).length, j = 0; j < length; ++j) {
			final String serializedEffect = split[j];
			final String effectName = serializedEffect.split(":d@")[0];
			String effectDuration = "";
			String effectAmplifier = "";
			String[] split2;
			for (int length2 = (split2 = serializedEffect.split(":d@")).length, k = 0; k < length2; ++k) {
				final String serializedEffectSplit = split2[k];
				if (!serializedEffectSplit.equals(effectName)) {
					final String[] getDurAndAmp = serializedEffectSplit.split(":a@");
					for (int i = 0; i < getDurAndAmp.length; ++i) {
						if (i == 0 && !getDurAndAmp[i].equals("")) {
							effectDuration = getDurAndAmp[i];
						}
						if (i == 1 && !getDurAndAmp[i].equals("")) {
							effectAmplifier = getDurAndAmp[i];
						}
					}
				}
			}
			if (!effectName.equals("") && !effectDuration.equals("") && !effectAmplifier.equals("")) {
				rtrn.add(new PotionEffect(PotionEffectType.getByName(effectName), Integer.valueOf(effectDuration),
						Integer.valueOf(effectAmplifier)));
			}
		}
		return rtrn;
	}

	public static String serializePotionEffects(final Player player) {
		final Collection<PotionEffect> effects = player.getActivePotionEffects();
		String rtrn = ";";
		for (final PotionEffect effect : effects) {
			final String effectName = effect.getType().getName();
			final int duration = effect.getDuration();
			final int amplify = effect.getAmplifier();
			rtrn = rtrn + "e@" + effectName + ":d@" + duration + ":a@" + amplify;
		}
		rtrn = rtrn + ";";
		return rtrn;
	}
}
