package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.providers.LangProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMobEffects;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class GENLangESAR extends LangProvider {
    public GENLangESAR(PackOutput output) {
        super(output, Orevolution.MODID, "es_ar");
    }

    public void addCondition(String tooltipID, String name) {
        add("condition.orevolution." + tooltipID, name);
    }

    public void addPower(String tooltipID, String name) {
        add("power.orevolution." + tooltipID, name);
    }

    public void addTier(String tooltipID, String name) {
        add("tiers.orevolution." + tooltipID, name);
    }

    public void addSmithingTemplateTips(Supplier<Item> item, String nameID, String name, String appliesTo, String ingredients, String baseSlotDescription, String additionsSlotDescription) {
        add("upgrade.orevolution." + nameID + "_upgrade", name);
        addItem(item, "Plantilla de herrería");
        add("item.orevolution.smithing_template." + nameID + "_upgrade.applies_to", appliesTo);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.ingredients", ingredients);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.base_slot_description", baseSlotDescription);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.additions_slot_description", additionsSlotDescription);
    }

    public void colors(String id, String name) {
        add("item.orevolution." + id, name);
        for (DyeColor color : DyeColor.values()) {
            add(
                    "tooltip.orevolution." + id + "." + color.getName(),
                    name + " " + elpinchicolorwei(color)
            );
        }
    }

    private String elpinchicolorwei(DyeColor color) {
        return switch (color) {
            case RED -> "rojo";
            case BLUE -> "azul";
            case WHITE -> "blanco";
            case ORANGE, YELLOW -> "naranja";
            case MAGENTA -> "magenta";
            case LIGHT_BLUE -> "azul claro";
            case LIME -> "lima";
            case PINK -> "rosa";
            case GRAY -> "gris";
            case LIGHT_GRAY -> "gris claro";
            case CYAN -> "cian";
            case PURPLE -> "purpura";
            case BROWN -> "marron";
            case GREEN -> "verde";
            case BLACK -> "negro";
            default -> color.getName();
        };
    }

    public void addEffect(Supplier<? extends MobEffect> key, String potionkey, String name) {
        add(key.get(), name);
        addPotions(potionkey, name);
    }

    public void addPotions(String key, String name) {
        add("item.minecraft.potion.effect." + key, "Poción de " + name);
        add("item.minecraft.splash_potion.effect." + key, "Poción arrojadiza de " + name);
        add("item.minecraft.lingering_potion.effect." + key, "Poción persistente de " + name);

        add("item.minecraft.potion.effect." + key + "_long", "Poción de " + name);
        add("item.minecraft.splash_potion.effect." + key + "_long", "Poción arrojadiza de " + name);
        add("item.minecraft.lingering_potion.effect." + key + "_long", "Poción persistente de " + name);

        add("item.minecraft.potion.effect." + key + "_strong", "Poción de" + name);
        add("item.minecraft.splash_potion.effect." + key + "_strong", "Poción arrojadiza de " + name);
        add("item.minecraft.lingering_potion.effect." + key + "_strong", "Poción persistente de " + name);
    }
    
    public void addAdvancement(String id, String title, String desc) {
        String advancementID = Orevolution.MODID + "." + id;
        addAdvTitle(advancementID, title);
        addAdvDesc(advancementID, desc);
    }

    @Override
    protected void addTranslations() {
        addPower("regenerates_daylight", "Mejora su durabilidad cada %s segundo(s) mientras recibas luz solar");

        addPower("duplication", "Tiene una probabilidad (%s-%s) de duplicar los drops de los bloques");
        addPower("explanation.duplication", "Lista de probabilidades dependiendo del bloque:");
        addPower("explanation.double_chance", " - Doble probabilidad (Por ej. Hojas de árbol) -> %s");
        addPower("explanation.normal_chance", " - Bloques normales (Por ej. Bloques de piedra) -> %s");
        addPower("explanation.uncommon_chance", " - Uncommon blocks (Por ej. Anvil) -> %s");
        addPower("explanation.ore_chance", " - Minerales (ej. Mineral de carbon) -> %s");
        addPower("explanation.rare_chance", " - Raros (ej. Bloque de hierro) -> %s");
        addPower("explanation.no_chance", " - Induplicables (ej. Bloque de acero etéreo) -> 0%");

        addPower("duplication_crops", "Tiene una probabilidad (Maxima de %s porciento) de duplicar los drops de las plantas");
        addPower("triplication_crops", "Tiene una probabilidad (Maxima de %s porciento) de triplicar los drops de las plantas");

        addPower("on_hit_effect", "Inflige los siguientes efecto(s) al atacar:");
        addPower("attacker_on_hit_effect", "Te da los siguientes efecto(s) al atacar");

        addPower("on_hit_effect_chance", "Tiene una probabilidad de Infligir los siguientes efecto(s) al atacar:");
        addPower("attacker_on_hit_effect_chance", "Tiene una probabilidad de darte los siguientes efecto(s) al attackar");

        addPower("undead_on_hit", "Inflige los siguientes efecto(s) al atacar a los No-muertos:");
        addPower("monster_on_hit", "Inflige los siguientes efecto(s) al atacar a cualquier Monstruo:");

        addPower("xp_increase", "Los bloques dan x%s Puntos de Experiencia");
        addPower("xp_looting", "Los mobs darán %s Puntos de Experiencia al morir");

        addPower("consecutive_hits_damage", "Atacar al mismo objetivo repetidamente aumenta el daño en 1 hasta %s veces");
        addPower("consecutive_blocks_grants", "Cada %s bloques, da y aumenta los siguientes efecto(s):");

        addPower("avoid_damage", "Evita la perdida de durabilidad al usarse");

        addPower("durability_speed", "La velocidad de minería aumenta dependiendo de la durabilidad de la herramienta");
        addPower("durability_atkspeed", "La velocidad de ataque aumenta dependiendo de la durabilidad de la herramienta");
        addPower("durability_damage", "El daño aumenta dependiendo de la durabilidad de la herramienta");

        addPower("speed_durability", "La velocidad de minería se reduce dependiendo de la durabilidad de la herramienta");
        addPower("atkspeed_durability", "La velocidad de ataque se reduce dependiendo de la durabilidad de la herramienta");
        addPower("damage_durability", "El daño se reduce dependiendo de la durabilidad de la herramienta");

        addPower("crit_damage", "Los golpes críticos causan %s de daño");

        addPower("on_hit_armored", "Daño a enemigos con defensa aumenta en %s");
        addPower("hardness_speed", "La velocidad de minería aumenta dependiendo de la dureza del bloque");

        addPower("on_hit_weakened", "Los enemigos reciben %s de daño");

        addPower("explanation.durability_speed",
                "La durabilidad funciona como un porcentaje para la velocidad de minería " +
                        "\nMientras menor la durabilidad, mayor la velocidad de minería");

        addPower("full_set_bonus", "Bono por set completo: %s");
        addPower("equipped_set", "(%s/4)");

        addPower("silver_armor", "Aumenta la Invencibilidad por %s segundos");
        addPower("silver_armor_aoe", "Enemigos no-muertos en un radio de %s bloques son infectados con los siguientes efecto(s):");

        addPower("armor_wearer_grants", "Te da los siguientes efecto(s):");
        addPower("armor_wearer_grants_daylight", "Te da los siguientes efecto(s) si recibes la luz solar:");
        addPower("armor_wearer_grants_on_hit_wearer_daylight", "te da los siguientes efecto(s) al atacar y si recibes luz solar:");
        addPower("armor_wearer_on_attacked", "te da los siguientes efecto(s) al recibir daño:");
        addPower("armor_wearer_on_attacked_target", "te da los siguientes efecto(s) al enemigo cuando te atacan:");

        addPower("armor_wearer_on_hit_wearer", "Te da los siguientes efecto(s) al atacar:");
        addPower("armor_wearer_on_hit_target", "Inflige los siguientes efecto(s) al enemigo al atacarlo:");

        addPower("armor_immunity", "Te da los siguientes efecto(s):");
        addPower("armor_immunity_daylight", "Te da inmunidad a los siguientes efecto(s) si recibes luz solar:");

        addPower("armor_extended_pickup", "Aumenta el rango de recolección de items por %s bloques");
        addPower("copper_armor", "Aumenta el alcance de construcción por %s bloques");

        addPower("netherite_armor", "Cuando tu vida esta a menos del 50%, Inflige los siguientes efecto(s) al atacar:");
        addPower("reinforced_netherite_armor", "Mientras no estas sumergido en lava, te da los siguientes efecto(s):");
        addPower("iron_armor", "Tiene una probabilidad del 30% de ignorar el daño de proyectiles");
        addPower("diamond_armor", "Reduce el daño de las caídas, explosiones y bloques que caen en un %s");
        addPower("bronze_armor", "Mientras no estas sumergido en agua, te da los siguientes efecto(s):");
        addPower("tungsten_armor", "Reduce el daño de las fuentes de fuego en un %s");

        addPower("electrum_armor", "Incrementa la Asistencia de altura");

        addPower("tool_cause_effect_on_hits", "Cada %s golpes, Inflige los siguientes efecto(s) al atacar:");
        addPower("tool_grant_effect_on_hits", "Cada %s golpes, te da los siguientes efecto(s)");

        addPower("autosmelt", "Cocina la mayoría de los minerales, arenas, troncos y cultivos automáticamente");
        addPower("explanation.autosmelt", "Este efecto se invierte mientras te agachas");

        addPower("fire_on_hit", "Incendia a los enemigos por %s segundos al atacar");

        addPower("aethersteel", "Tras morir, regresa a tu inventario");

        addPower("multi_break", "Rompe bloques en un area de 3x3");
        addPower("explanation.multi_break",
                "Cada bloque que rompas reduce la durabilidad en 1 punto\n" +
                        "Romper 9 bloques resulta en perder 9 puntos de durabilidad\n" +
                        "Pierde 4 puntos de durabilidad por cada nivel de eficiencia\n" +
                        "Romper 9 bloques con eficiencia I resulta en perder 36 puntos de durabilidad"
        );

        addCondition("chance", " - Probabilidad del %s de que no ocurra");

        addCondition("target_hp_percent.lower_than", " - Vida del enemigo debe de ser igual o menor al %s");
        addCondition("target_hp_percent.higher_than", " - Vida del enemigo debe de ser igual o mayor al %s");
        addCondition("target_hp_amount", " - Vida del enemigo debe de ser igual o menor a %s corazones");

        addCondition("player_hp_percent.lower_than", " - Tu vida debe de ser igual o menor al %s");
        addCondition("player_hp_percent.higher_than", " - Tu vida debe de ser igual o mayor al %s");
        addCondition("player_hp_amount", " - Tu vida debe de ser igual o menor a %s corazones");

        add("item.orevolution.bronze_radar.tooltip",
                "Presiona click derecho mientras te agachas para cambiar entre el modo amigos/personal\n" +
                        "Mientras este en el modo amigos, presione click derecho para cambiar el jugador mostrado"
        );

        add("item.orevolution.tungsten_reinforced", "Reforzamiento de tungsteno");
        add("item.orevolution.tungsten_coated", "Revestido en tungsteno");

        add("item.durability_multiplier", "Durabilidad");

        add("actionbar.orevolution.bronze_radar.normal_mode", "Mis coordenadas: %s");
        add("actionbar.orevolution.bronze_radar.friend_mode", "Coordenadas actuales de %s");
        add("actionbar.orevolution.bronze_radar.friend_mode.no_target", "No se pudo encontrar jugadores...");
        add("actionbar.orevolution.bronze_radar.searching", "Buscando%s");

        add("item.orevolution.totem.socket.empty", "Ranura vacía");
        add("item.orevolution.totem.socket.diamond", "Diamante - Efectos benévolos duran el doble de tiempo (tiempo min. de 3s)");
        add("item.orevolution.totem.socket.emerald", "Esmeralda - Doubles regeneration while standing still");
        add("item.orevolution.totem.socket.lapis", "Lapis Lazuli - Efectos negativos duran la mitad del tiempo (tiempo min. de 3s)");
        add("item.orevolution.totem.socket.quartz", "Cuarzo - Previene la hambruna");
        add("item.orevolution.totem.socket.celestite", "Celestina - Usar para ganar Vision Nocturna por 2 minutos");
        add("item.orevolution.totem.socket.amethyst", "Amatista - Usar para ganar Apuro por 2 minutos");
        add("item.orevolution.totem.socket.star", "Estrella de Nether - Duplica el efecto de otros 2 totems");
        add("item.orevolution.totem.hotbar", "Funciona mientras este en la Hotbar");

        add("actionbar.orevolution.cant_harvest_ore", "Tu %s es muy débil para este bloque");
        add("actionbar.orevolution.cant_harvest_block", "Este bloque requiere de %s");

        add("tool.minecraft.pickaxe", "un Pico");
        add("tool.minecraft.shovel", "una Pala");
        add("tool.minecraft.hoe", "una Azada");
        add("tool.minecraft.axe", "un Hacha");
        add("tool.c.sword", "una Espada");
        add("tool.orevolution.unknown_tool", "una herramienta desconocida");

        add("trim_material.orevolution.platinum", "Material de platino");
        add("trim_material.orevolution.tin", "Material de estaño");
        add("trim_material.orevolution.tungsten", "Material de tungsteno");
        add("trim_material.orevolution.aethersteel", "Material de acero etéreo");
        add("trim_material.orevolution.livingstone", "Material de piedra viva");
        add("trim_material.orevolution.verdite", "Material de verdita");

        addPower("press_key", "Mantén presionado %s para ver mas información");

        addPower("press_key_power", "Mantén presionado %s para ver las habilidades");

        addAdvTitle("tin_upgrade", "Realmente un pico estaño");
        addAdvDesc("tin_upgrade", "Crea un Pico de estaño");

        addAdvTitle("obtain_platinum", "El metal de los Reyes");
        addAdvDesc("obtain_platinum", "Funde un Lingote de platino");

        addAdvTitle("platinum_armor", "Atuendo filoso");
        addAdvDesc("platinum_armor", "Crea la armadura completa de platino");

        addAdvTitle("platinum_gear", "Ganando Experiencia");
        addAdvDesc("platinum_gear", "Crea un Pico de platino");

        addAdvTitle("obtain_tungsten", "El confiable");
        addAdvDesc("obtain_tungsten", "Funde o encuentra un Lingote de tungsteno");

        addAdvTitle("reinforcement", "Hecho para durar");
        addAdvDesc("reinforcement", "Refuerza un objeto en Tungsteno");

        addAdvTitle("coating", "El toque final");
        addAdvDesc("coating", "Reviste un objeto en Tungsteno");

        addAdvTitle("obtain_primitive_aetherrock", "Oculto en el vacío");
        addAdvDesc("obtain_primitive_aetherrock", "Obtén Piedra éter ea primitiva de un meteorito");

        addAdvTitle("aethersteel_armor", "Fuerza imparable");
        addAdvDesc("aethersteel_armor", "Obtén un atuendo completo de Acero etéreo");

        addAdvTitle("obtain_aethersteel_hoe", "Herramienta sin motivo");
        addAdvDesc("obtain_aethersteel_hoe", "Malgasta un Lingote de acero etéreo para mejorar una Azada de netherita");


        add(RegItems.GLOWING_BOTTLE.get(), "Botella espectral");
        add(RegItems.FIERCE_BOTTLE.get(), "Botella feroz");
        add(RegItems.LIFE_BOTTLE.get(), "Botella vital");
        add(RegItems.LIGHTNING_BOTTLE.get(), "Botella audaz");

        addEffect(RegMobEffects.PETRIFIED, "petrification", "Petrificado");
        addEffect(RegMobEffects.QUICKNESS, "quickness", "Prontitud");
        addEffect(RegMobEffects.PURIFICATION, "purification", "Purificación");
        addEffect(RegMobEffects.LESSER_PURIFICATION, "lesser_purification", "Purificación inferior");

        add("item.orevolution.reinforced", "%s reforzado en tungsteno");
        add("item.orevolution.coated", "%s revestido en tungsteno");

        addPotions("intoxication", "Intoxicación");

//        addEffect(RegMobEffects.WEAK_SOUL, "Alma debilitada");
        
        add(RegItems.BRONZE_HORSE_ARMOR.get(), "Armadura de bronce para caballo");
        add(RegItems.STEEL_HORSE_ARMOR.get(), "Armadura de acero para caballo");

        addItem(RegItems.BRONZE_TOTEM, "Tótem de bronce");
        addItem(RegItems.BRONZE_RADAR, "Radar");

        addItem(RegItems.DEAD_SEED, "Semilla muerta");
        addBlock(RegBlocks.VERDITE_CROP, "Cultivo de verdita");

        addBlock(RegBlocks.LIVINGSTONE_BLOCK, "Bloque de piedra viva");
        addItem(RegItems.PETRIFIED_SEED, "Semilla petrificada");
        addBlock(RegBlocks.LIVINGSTONE_CROP, "Cultivo de piedra viva");

        addBlock(RegBlocks.CHERT, "Sílice");
        addBlock(RegBlocks.CHERT_PILLAR, "Pilar de piedra caliza");
        addBlock(RegBlocks.POLISHED_CHERT, "Piedra caliza pulida");

        addItem(RegItems.CRUSHED_TUNGSTEN, "Trozos de tungsteno crudo");
        addItem(RegItems.CRUSHED_AETHERSTEEL, "Trozos de acero etéreo crudo");

        addItem(RegItems.FIERY_ARROW, "Flecha ardiente");

        addItem(RegItems.FOOLS_APPLE, "Manzana dorada");
        addItem(RegItems.FOOLS_CARROT, "Zanahoria dorada");

        addItem(RegItems.PLATINUM_SHIELD, "Escudo de platino");
        addItem(FDRegistry.PLATINUM_KNIFE, "Cuchillo de platino");
        addItem(RegItems.PLATINUM_SWORD, "Espada de platino");
        addItem(RegItems.PLATINUM_SHOVEL, "Pala de platino");
        addItem(RegItems.PLATINUM_PICKAXE, "Pico de platino");
        addItem(RegItems.PLATINUM_AXE, "Hacha de platino");
        addItem(RegItems.PLATINUM_HOE, "Azada de platino");
        addItem(RegItems.PLATINUM_HELMET, "Casco de platino");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Pechera de platino");
        addItem(RegItems.PLATINUM_LEGGINGS, "Pantalones de platino");
        addItem(RegItems.PLATINUM_BOOTS, "Botas de platino");

        addItem(RegItems.TIN_SHIELD, "Escudo de estaño");
        addItem(FDRegistry.TIN_KNIFE, "Cuchillo de estaño");
        addItem(RegItems.TIN_SWORD, "Espada de estaño");
        addItem(RegItems.TIN_SHOVEL, "Pala de estaño");
        addItem(RegItems.TIN_PICKAXE, "Pico de estaño");
        addItem(RegItems.TIN_AXE, "Hacha de estaño");
        addItem(RegItems.TIN_HOE, "Azada de estaño");

//        addItem(RegItems.NICKEL_SHIELD, "Escudo de niquel");
//        addItem(FDRegistry.NICKEL_KNIFE, "Cuchillo de niquel");
//        addItem(RegItems.NICKEL_SWORD, "Espada de niquel");
//        addItem(RegItems.NICKEL_SHOVEL, "Pala de niquel");
//        addItem(RegItems.NICKEL_PICKAXE, "Pico de niquel");
//        addItem(RegItems.NICKEL_AXE, "Hacha de niquel");
//        addItem(RegItems.NICKEL_HOE, "Azada de niquel");

        addItem(RegItems.AETHERSTEEL_SHIELD, "Escudo de acero etéreo");
        addItem(FDRegistry.AETHERSTEEL_KNIFE, "Cuchillo de acero etéreo");
        addItem(RegItems.AETHERSTEEL_SWORD, "Espada de acero etéreo");
        addItem(RegItems.AETHERSTEEL_SHOVEL, "Pala de acero etéreo");
        addItem(RegItems.AETHERSTEEL_PICKAXE, "Pico de acero etéreo");
        addItem(RegItems.AETHERSTEEL_AXE, "Hacha de acero etéreo");
        addItem(RegItems.AETHERSTEEL_HOE, "Azada de acero etéreo");
        addItem(RegItems.AETHERSTEEL_HELMET, "Casco de acero etéreo");
        addItem(RegItems.AETHERSTEEL_CHESTPLATE, "Pechera de acero etéreo");
        addItem(RegItems.AETHERSTEEL_LEGGINGS, "Pantalones de acero etéreo");
        addItem(RegItems.AETHERSTEEL_BOOTS, "Botas de acero etéreo");

        addItem(RegItems.MOONSTONE_SHIELD, "Escudo de gema lunar");
        addItem(FDRegistry.MOONSTONE_KNIFE, "Cuchillo de gema lunar");
        addItem(RegItems.MOONSTONE_SWORD, "Espada de gema lunar");
        addItem(RegItems.MOONSTONE_SHOVEL, "Pala de gema lunar");
        addItem(RegItems.MOONSTONE_PICKAXE, "Pico de gema lunar");
        addItem(RegItems.MOONSTONE_AXE, "Hacha de gema lunar");
        addItem(RegItems.MOONSTONE_HOE, "Azada de gema lunar");
        addItem(RegItems.MOONSTONE_HELMET, "Casco de gema lunar");
        addItem(RegItems.MOONSTONE_CHESTPLATE, "Pechera de gema lunar");
        addItem(RegItems.MOONSTONE_LEGGINGS, "Pantalones de gema lunar");
        addItem(RegItems.MOONSTONE_BOOTS, "Botas de gema lunar");

        addItem(RegItems.LIVINGSTONE_SHIELD, "Escudo de piedra viva");
        addItem(FDRegistry.LIVINGSTONE_KNIFE, "Cuchillo de piedra viva");
        addItem(RegItems.LIVINGSTONE_SWORD, "Espada de piedra viva");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Pala de piedra viva");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Pico de piedra viva");
        addItem(RegItems.LIVINGSTONE_AXE, "Hacha de piedra viva");
        addItem(RegItems.LIVINGSTONE_HOE, "Azada de piedra viva");
        addItem(RegItems.LIVINGSTONE_HELMET, "Casco de piedra viva");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Pechera de piedra viva");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Pantalones de piedra viva");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Botas de piedra viva");

        addItem(RegItems.VERDITE_SHIELD, "Escudo de verdita");
        addItem(FDRegistry.VERDITE_KNIFE, "Cuchillo de verdita");
        addItem(RegItems.VERDITE_SWORD, "Espada de verdita");
        addItem(RegItems.VERDITE_SHOVEL, "Pala de verdita");
        addItem(RegItems.VERDITE_PICKAXE, "Pico de verdita");
        addItem(RegItems.VERDITE_AXE, "Hacha de verdita");
        addItem(RegItems.VERDITE_HOE, "Azada de verdita");
        addItem(RegItems.VERDITE_HELMET, "Casco de verdita");
        addItem(RegItems.VERDITE_CHESTPLATE, "Pechera de verdita");
        addItem(RegItems.VERDITE_LEGGINGS, "Pantalones de verdita");
        addItem(RegItems.VERDITE_BOOTS, "Botas de verdita");

        addItem(RegItems.STEEL_HEAVYWORK_SWORD, "Espada pesado de acero");
        addItem(RegItems.STEEL_HEAVYWORK_PICKAXE, "Pico pesado de acero");
        addItem(RegItems.STEEL_HEAVYWORK_AXE, "Hacha pesado de acero");
        addItem(RegItems.STEEL_HEAVYWORK_SHOVEL, "Pala pesado de acero");
        addItem(RegItems.STEEL_HEAVYWORK_HOE, "Azada pesada de acero");
        addBlock(RegBlocks.STEEL_ANVIL, "Yunque pesado");

        addItem(RegItems.BRONZE_HELMET, "Mascara de bronce de buceo");
        addItem(RegItems.BRONZE_CHESTPLATE, "Pechera de bronce");
        addItem(RegItems.BRONZE_LEGGINGS, "Pantalones de bronce");
        addItem(RegItems.BRONZE_BOOTS, "Botas de bronce");

        addItem(RegItems.TUNGSTEN_HELMET, "Mascara de bronce de tungsteno");
        addItem(RegItems.TUNGSTEN_CHESTPLATE, "Pechera de tungsteno");
        addItem(RegItems.TUNGSTEN_LEGGINGS, "Pantalones de tungsteno");
        addItem(RegItems.TUNGSTEN_BOOTS, "Botas de tungsteno");

        addItem(RegItems.TUNGSTEN_SWORD, "Espada de tungsteno");
        addItem(RegItems.TUNGSTEN_PICKAXE, "Pico de tungsteno");
        addItem(RegItems.TUNGSTEN_AXE, "Hacha de tungsteno");
        addItem(RegItems.TUNGSTEN_SHOVEL, "Pala de Tungsteno");
        addItem(RegItems.TUNGSTEN_HOE, "Azada de Tungsteno");

        addItem(RegItems.PYRITE, "Pirita");
        addItem(RegItems.CELESTITE_SHARD, "Fragmento de celestina");

        addItem(RegItems.TIN_INGOT, "Lingote de estaño");
        addItem(RegItems.RAW_TIN, "Estaño crudo");
        addBlock(RegBlocks.TIN_BLOCK, "Bloque de estaño");
        addBlock(RegBlocks.RAW_TIN_BLOCK, "Bloque de estaño crudo");

//        addItem(RegItems.NICKEL_INGOT, "Lingote de niquel");
//        addItem(RegItems.RAW_NICKEL, "Niquel crudo");
//        addBlock(RegBlocks.NICKEL_BLOCK, "Bloque de niquel");
//        addBlock(RegBlocks.RAW_NICKEL_BLOCK, "Bloque de niquel crudo");
//        addBlock(RegBlocks.NICKEL_ORE, "Mineral de niquel");
//        addBlock(RegBlocks.DEEPSLATE_NICKEL_ORE, "Mineral de niquel de pizarra profunda");

        addItem(RegItems.PLATINUM_INGOT, "Lingote de platino");
        addItem(RegItems.RAW_PLATINUM, "Platino crudo");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Bloque de platino");
        addBlock(RegBlocks.RAW_PLATINUM_BLOCK, "Bloque de platino crudo");

        addItem(RegItems.TUNGSTEN_INGOT, "Lingote de tungsteno");
        addItem(RegItems.RAW_TUNGSTEN, "Tungsteno crudo");

        addItem(RegItems.BRONZE_ALLOY, "Aleación de bronce");
        addBlock(RegBlocks.BRONZE_BLOCK, "Bloque de bronce");

        addItem(RegItems.STEEL_ALLOY, "Aleación de acero");
        addBlock(RegBlocks.STEEL_BLOCK, "Bloque de acero");

        addItem(RegItems.VERDITE_INGOT, "Lingote de verdita");
        addBlock(RegBlocks.VERDITE_BLOCK, "Bloque de verdita");

        addItem(RegItems.AETHERSTEEL_INGOT, "Lingote de acero etéreo");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Trozo de acero etéreo");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Bloque de acero etéreo");

        addItem(RegItems.QUARTZ_CHIP, "Fragmento de cuarzo");

        addItem(RegItems.PROFESSIONAL_FIREWORK_ROCKET, "Cohete profesional");

        addItem(RegItems.MOTION_DETECTOR, "Detector de movimiento");
        addItem(RegItems.GEO_SCANNER, "Escáner geográfico");

        addSmithingTemplateTips(RegItems.BASIC_TEMPLATE, "basic",
                "Mejora básica",
                "Cualquier equipamiento",
                "Cualquier material superior",
                "Agregá una armadura, arma o herramienta",
                "Agregá un material superior");

        add("upgrade.orevolution.downgrade", "Degradación de equipamiento");
        addItem(RegItems.DOWNGRADE_TEMPLATE, "Plantilla de herrería");
        add("item.orevolution.smithing_template.downgrade.applies_to", "Cualquier equipamiento");
        add("item.orevolution.smithing_template.downgrade.ingredients", "Cualquier material inferior");
        add("item.orevolution.smithing_template.downgrade.base_slot_description", "Agregá una armadura, arma o herramienta");
        add("item.orevolution.smithing_template.downgrade.additions_slot_description", "Agregá un material inferior");

        addSmithingTemplateTips(RegItems.REINFORCED_TEMPLATE, "tungsten",
                "Mejora de reforzamiento",
                "Objetos con durabilidad",
                "Lingote de tungsteno",
                "Agregá cualquier objeto con durabilidad",
                "Agregá un lingote de tungsteno");
        addSmithingTemplateTips(RegItems.COATING_TEMPLATE, "coating",
                "Mejora de revestimiento",
                "Cualquier equipamiento",
                "Lingote de tungsteno",
                "Agrega cualquier armadura, arma o herramienta",
                "Agregá un lingote de tungsteno");

        addSmithingTemplateTips(RegItems.AETHERSTEEL_TEMPLATE, "aethersteel",
                "Mejora del cielo",
                "Equipamiento de netherita",
                "Lingote de acero etéreo",
                "Agregá una armadura, arma o herramienta de netherita",
                "Agregá un lingote de acero etéreo");

        addItem(RegItems.VINNELIO, "Vinnelio");
        addItem(RegItems.ANCIENT_FRUIT, "Fruta antigua");
        addItem(RegItems.ANCIENT_STEW, "Guiso antiguo");

        addItem(RegItems.TIN_NUGGET, "Pepita de estaño");
        addItem(RegItems.PLATINUM_NUGGET, "Pepita de platino");
        addItem(RegItems.TUNGSTEN_NUGGET, "Pepita de tungsteno");
        addItem(RegItems.VERDITE_NUGGET, "Pepita de verdita");
        addItem(RegItems.LIVINGSTONE_SHARD, "Fragmento de piedra viva");

        addItem(RegItems.VERDITE_APPLE, "Manzana de verdita");
        addItem(RegItems.VERDITE_SPIDER_EYE, "Ojo de araña de verdita");

        addItem(RegItems.PLATINUM_BERRIES, "Bayas de platino");
        addItem(RegItems.PLATINUM_APPLE, "Manzana de platino");

        addBlock(RegBlocks.BUDDING_CELESTITE, "Brotador de celestina");
        addBlock(RegBlocks.CELESTITE_CLUSTER, "Clúster de celestina");
        addBlock(RegBlocks.SMALL_CELESTITE_BUD, "Brote de celestina pequeño");
        addBlock(RegBlocks.MEDIUM_CELESTITE_BUD, "Brote de celestina mediano");
        addBlock(RegBlocks.LARGE_CELESTITE_BUD, "Brote de celestina grande");
        addBlock(RegBlocks.CELESTITE_BLOCK, "Bloque de celestina");
        addBlock(RegBlocks.PYRITE_BLOCK, "Bloque de pirita");
        addBlock(RegBlocks.QUARTZOLITE, "Silexita");
        addBlock(RegBlocks.STEEL_BARS, "Barras de acero");
        addBlock(RegBlocks.VERDITE_BRICKS, "Ladrillos de verdita");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS, "Ladrillos de piedra viva");
        addBlock(RegBlocks.TIN_ORE, "Mineral de estaño");
        addBlock(RegBlocks.DEEPSLATE_TIN_ORE, "Mineral de estaño de pizarra profunda");
        addBlock(RegBlocks.CASSITERITE_BLOCK, "Mineral de casiterita");
        addBlock(RegBlocks.DEEPSLATE_CASSITERITE_ORE, "Mineral de casiterita de pizarra profunda");
        addBlock(RegBlocks.NETHER_CASSITERITE_ORE, "Mineral de casiterita del Nether");
        addBlock(RegBlocks.PLATINUM_ORE, "Mineral de platino");
        addBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE, "Mineral de platino de pizarra profunda");
        addBlock(RegBlocks.NETHER_TUNGSTEN_ORE, "Mineral de tungsteno del Nether");
        addBlock(RegBlocks.RAW_TUNGSTEN_BLOCK, "Bloque de tungsteno crudo");
        
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Bloque de tungsteno");
        addBlock(RegBlocks.CUT_TUNGSTEN_BLOCK, "Tungsteno cortado");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, "Bloque de tungsteno cincelado");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, "Ladrillos de tungsteno cincelado");
        addBlock(RegBlocks.TUNGSTEN_BRICKS, "Ladrillos de tungsteno");
        addBlock(RegBlocks.TUNGSTEN_BARS, "Barras de tungsteno");

        addBlock(RegBlocks.DECAYING_TUNGSTEN_BLOCK, "Bloque de tungsteno en decadencia");
        addBlock(RegBlocks.CUT_DECAYING_TUNGSTEN_BLOCK, "Tungsteno cortado en decadencia");
        addBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BLOCK, "Tungsteno cincelado en decadencia");
        addBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BRICKS, "Ladrillos de tungsteno cincelado en decadencia");
        addBlock(RegBlocks.DECAYING_TUNGSTEN_BRICKS, "Ladrillos de tungsteno en decadencia");
        addBlock(RegBlocks.DECAYING_TUNGSTEN_BARS, "Barras de tungsteno en decadencia");

        addBlock(RegBlocks.CORRODED_TUNGSTEN_BLOCK, "Bloque de tungsteno carcomido");
        addBlock(RegBlocks.CUT_CORRODED_TUNGSTEN_BLOCK, "Tungsteno cortado carcomido");
        addBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BLOCK, "Bloque de tungsteno cincelado carcomido");
        addBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BRICKS, "Ladrillos de tungsteno cincelado carcomido");
        addBlock(RegBlocks.CORRODED_TUNGSTEN_BRICKS, "Ladrillos de tungsteno carcomido");
        addBlock(RegBlocks.CORRODED_TUNGSTEN_BARS, "Barras de tungsteno carcomido");

        addBlock(RegBlocks.TUNGSTEN_SPONGE, "Esponja de tungsteno");
        addBlock(RegBlocks.HOT_TUNGSTEN_SPONGE, "Esponja de tungsteno caliente");

        addBlock(RegBlocks.NETHER_XP_ORE, "Mineral de experiencia del Nether");
        addBlock(RegBlocks.END_XP_ORE, "Mineral de experiencia del End");
        addBlock(RegBlocks.RHYOLITE_EMERALD_ORE, "Mineral de esmeralda de riolita");
        addBlock(RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP, "Deposito de mineral de esmeralda de riolita");
        addBlock(RegBlocks.AETHERROCK, "Piedra etérea");
        addBlock(RegBlocks.AETHERROCK_TILES, "Losetas de piedra etérea");
        addBlock(RegBlocks.POLISHED_AETHERROCK, "Piedra etérea pulida");
        addBlock(RegBlocks.AETHERROCK_BRICKS, "Ladrillos de piedra etérea");
        addBlock(RegBlocks.CRACKED_AETHERROCK_BRICKS, "Ladrillos de piedra etérea agrietados");
        addBlock(RegBlocks.CHERT_BRICKS, "Ladrillos de sílice");
        addBlock(RegBlocks.RHYOLITE_BRICKS, "Ladrillos de riolita");
        addBlock(RegBlocks.CELESTITE_BRICKS, "Ladrillos de celestina");
        addBlock(RegBlocks.POLISHED_CELESTITE, "Celestina pulida");
        addBlock(RegBlocks.PRIMITIVE_AETHERROCK, "Piedra etérea primitiva");
        addBlock(RegBlocks.CUT_STEEL_BLOCK, "Acero cortado");
        addBlock(RegBlocks.BRONZE_TILES, "Losetas de bronce");
        addBlock(RegBlocks.STEEL_PILLAR, "Pilar de acero");
        addBlock(RegBlocks.STEEL_DOOR, "Puerta de acero");
        addBlock(RegBlocks.STEEL_TRAPDOOR, "Trampilla de acero");
        addBlock(RegBlocks.TIN_BRICKS, "Ladrillos de estaño");
        addBlock(RegBlocks.MOONSTONE, "Gema lunar");
        addBlock(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE, "Gema lunar incrustada en riolita");
        addBlock(RegBlocks.BASALT_ENCRUSTED_MOONSTONE, "Gema lunar incrustada en basalto");
        addBlock(RegBlocks.STEEL_GRATE, "Rejilla de acero");

        addBlock(RegBlocks.TIN_LANTERN, "Farol de estaño");
        addBlock(RegBlocks.PLATINUM_LANTERN, "Farol de platino");
        addBlock(RegBlocks.BRONZE_LANTERN, "Farol de bronce");
        addBlock(RegBlocks.GOLDEN_LANTERN, "Farol de oro");
        addBlock(RegBlocks.TIN_SOUL_LANTERN, "Farol de almas de estaño");
        addBlock(RegBlocks.PLATINUM_SOUL_LANTERN, "Farol de almas de platino");
        addBlock(RegBlocks.BRONZE_SOUL_LANTERN, "Farol de almas de bronce");
        addBlock(RegBlocks.GOLDEN_SOUL_LANTERN, "Farol de almas de oro");

        addBlock(RegBlocks.TIN_TILES, "Losetas de estaño");
        addBlock(RegBlocks.PLATINUM_TILES, "Losetas de platino");
        addBlock(RegBlocks.GOLD_TILES, "Losetas de oro");
        addBlock(RegBlocks.PLATINUM_PILLAR, "Pilar de platino");
        addBlock(RegBlocks.GOLD_PILLAR, "Pilar de oro");
        addBlock(RegBlocks.BRONZE_BARS, "Barras de bronce");
        addBlock(RegBlocks.GOLD_BARS, "Barras de oro");
        addBlock(RegBlocks.TIN_BARS, "Barras de estaño");
        addBlock(RegBlocks.PLATINUM_BARS, "Barras de platino");
        addBlock(RegBlocks.POLISHED_AETHERROCK_WALL, "Muro de piedra etérea pulida");
        addBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, "Escalera de piedra etérea pulida");
        addBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, "Baldosa de piedra etérea pulida");
        addBlock(RegBlocks.AETHERROCK_WALL, "Muro de piedra etérea");
        addBlock(RegBlocks.AETHERROCK_STAIR, "Escalera de piedra etérea");
        addBlock(RegBlocks.AETHERROCK_SLAB, "Baldosa de piedra etérea");
        addBlock(RegBlocks.AETHERROCK_BRICKS_WALL, "Muro de ladrillos de piedra etérea");
        addBlock(RegBlocks.AETHERROCK_BRICKS_STAIR, "Escalera de ladrillos de piedra etérea");
        addBlock(RegBlocks.AETHERROCK_BRICKS_SLAB, "Baldosa de ladrillos de piedra etérea");

        addBlock(RegBlocks.CHERT_WALL, "Muro de silicio");
        addBlock(RegBlocks.CHERT_STAIR, "Escalera de silicio");
        addBlock(RegBlocks.CHERT_SLAB, "Baldosa de silicio");
        addBlock(RegBlocks.POLISHED_CHERT_WALL, "Baldosa de silicio pulido");
        addBlock(RegBlocks.POLISHED_CHERT_STAIR, "Escalera de silicio pulido");
        addBlock(RegBlocks.POLISHED_CHERT_SLAB, "Baldosa de silicio pulido");
        addBlock(RegBlocks.CHERT_BRICKS_WALL, "Muro de ladrillos de silicio");
        addBlock(RegBlocks.CHERT_BRICKS_STAIR, "Escalera de ladrillos de silicio");
        addBlock(RegBlocks.CHERT_BRICKS_SLAB, "Baldosa de ladrillos de silicio");

        addBlock(RegBlocks.RHYOLITE_WALL, "Muro de riolita");
        addBlock(RegBlocks.RHYOLITE_STAIR, "Escalera de riolita");
        addBlock(RegBlocks.RHYOLITE_SLAB, "Baldosa de riolita");
        addBlock(RegBlocks.POLISHED_RHYOLITE_WALL, "Baldosa de riolita pulida");
        addBlock(RegBlocks.POLISHED_RHYOLITE_STAIR, "Escalera de riolita pulida");
        addBlock(RegBlocks.POLISHED_RHYOLITE_SLAB, "Baldosa de riolita pulida");
        addBlock(RegBlocks.RHYOLITE_BRICKS_WALL, "Muro de ladrillos de riolita");
        addBlock(RegBlocks.RHYOLITE_BRICKS_STAIR, "Escalera de ladrillos de riolita");
        addBlock(RegBlocks.RHYOLITE_BRICKS_SLAB, "Baldosa de ladrillos de riolita");
        
        addBlock(RegBlocks.POLISHED_LIVINGSTONE_WALL, "Muro de piedra viva pulida");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE_STAIR, "Escalera de piedra viva pulida");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE_SLAB, "Baldosa de piedra viva pulida");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_WALL, "Muro de ladrillos de piedra viva");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_STAIR, "Escalera de ladrillos de piedra viva");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_SLAB, "Baldosa de ladrillos de piedra viva");

        addBlock(RegBlocks.CUT_STEEL_SLAB, "Baldosa de acero cortado");
        addBlock(RegBlocks.CUT_STEEL_STAIR, "Escalera de acero cortado");

        addBlock(RegBlocks.AMETHYST_BRICKS_WALL, "Muro de ladrillos de amatista");
        addBlock(RegBlocks.AMETHYST_BRICKS_STAIR, "Escalera de ladrillos de amatista");
        addBlock(RegBlocks.AMETHYST_BRICKS_SLAB, "Baldosa de ladrillos de amatista");
        addBlock(RegBlocks.POLISHED_AMETHYST_WALL, "Muro de amatista pulida");
        addBlock(RegBlocks.POLISHED_AMETHYST_STAIR, "Escalera de amatista pulida");
        addBlock(RegBlocks.POLISHED_AMETHYST_SLAB, "Baldosa de amatista pulida");

        addBlock(RegBlocks.CELESTITE_BRICKS_WALL, "Muro de ladrillos de celestina");
        addBlock(RegBlocks.CELESTITE_BRICKS_STAIR, "Escalera de ladrillos de celestina");
        addBlock(RegBlocks.CELESTITE_BRICKS_SLAB, "Baldosa de ladrillos de celestina");
        addBlock(RegBlocks.POLISHED_CELESTITE_WALL, "Muro de celestina pulida");
        addBlock(RegBlocks.POLISHED_CELESTITE_STAIR, "Escalera de celestina pulida");
        addBlock(RegBlocks.POLISHED_CELESTITE_SLAB, "Baldosa de celestina pulida");

        addBlock(RegBlocks.POLISHED_AMETHYST, "Amatista pulida");
        addBlock(RegBlocks.AMETHYST_BRICKS, "Ladrillos de amatista");

        addBlock(RegBlocks.CORELIO, "Corelio");
        addBlock(RegBlocks.LARGE_CORELIO, "Corelio grande");
        addBlock(RegBlocks.UNKNOWN_ROOTS, "Raíces extrañas");
        addBlock(RegBlocks.UNKNOWN_ROOTS_BLOCK, "Bloque de raíces extrañas");
        addBlock(RegBlocks.EMERALD_CLUSTER, "Cluster de esmeralda");
        addBlock(RegBlocks.PRISMARINE_CLUSTER, "Cluster de prismarina");
        addBlock(RegBlocks.VINNELIO, "Cuerdelio");
        addBlock(RegBlocks.VINNELIO_PLANT, "Cuerdelio");
        addBlock(RegBlocks.RHYOLITE, "Riolita");
        addBlock(RegBlocks.RHYOLITE_PILLAR, "Pilar de riolita");
        addBlock(RegBlocks.POLISHED_RHYOLITE, "Riolita pulida");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE, "Piedra viva pulida");

        addBlock(RegBlocks.RDX, "RDX");

        addItem(SBRegistry.AETHERSTEEL_SPEAR, "Lanza de acero etéreo");
        addItem(SBRegistry.TIN_SPEAR, "Lanza de estaño");
        addItem(SBRegistry.VERDITE_SPEAR, "Lanza de verdita");
        addItem(SBRegistry.PLATINUM_SPEAR, "Lanza de platino");
        addItem(SBRegistry.LIVINGSTONE_SPEAR, "Lanza de piedra viva");
        addItem(SBRegistry.MOONSTONE_SPEAR, "Lanza de gema lunar");

//        addItem(RegItemsAE.TIN_ARROW, "Flecha revestida en estaño");
//        addItem(RegItemsAE.PLATINUM_ARROW, "Flecha revestida en platino");
//        addItem(RegItemsAE.AETHERSTEEL_ARROW, "Flecha revestida en acero etéreo");
//        addItem(RegItemsAE.TIN_BOW, "Arco reforzado en estaño");
//        addItem(RegItemsAE.PLATINUM_BOW, "Arco reforzado en platino");
//        addItem(RegItemsAE.AETHERSTEEL_BOW, "Arco reforzado en acero etéreo");
//
//        addEntityType(RegEntityTypesAE.TIN_ARROW, "Flecha revestida en estaño");
//        addEntityType(RegEntityTypesAE.PLATINUM_ARROW, "Flecha revestida en platino");
//        addEntityType(RegEntityTypesAE.AETHERSTEEL_ARROW, "Flecha revestida en acero etéreo");
    }
}
