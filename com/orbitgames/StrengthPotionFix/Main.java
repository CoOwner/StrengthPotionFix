package com.orbitgames.StrengthPotionFix;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import java.util.*;
import org.bukkit.event.*;

public class Main extends JavaPlugin implements Listener
{
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((Listener)this, (Plugin)this);
    }
    
    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player player = (Player)event.getDamager();
            if (player.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                for (final PotionEffect Effect : player.getActivePotionEffects()) {
                    if (Effect.getType().equals((Object)PotionEffectType.INCREASE_DAMAGE)) {
                        final double DamagePercentage = (Effect.getAmplifier() + 1) * 1.3 + 1.0;
                        int NewDamage;
                        if (event.getDamage() / DamagePercentage <= 1.0) {
                            NewDamage = (Effect.getAmplifier() + 1) * 3 + 1;
                        }
                        else {
                            NewDamage = (int)(event.getDamage() / DamagePercentage) + (Effect.getAmplifier() + 1) * 3;
                        }
                        event.setDamage((double)NewDamage);
                        break;
                    }
                }
            }
        }
    }
}
