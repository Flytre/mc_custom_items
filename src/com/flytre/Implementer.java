package com.flytre;

import java.text.DecimalFormat;

class Implementer {



    private static int enderIDCounter = 2;

    static void initialize() {
        FunctionWriter.deleteOld();
        FunctionWriter.createDatapack();
        FunctionWriter.makeFunction("init_items");
        FunctionWriter.makeFunction("info");
        FunctionWriter.makeFunction("generic_base");

        FunctionWriter.makeLoadJSON();
        FunctionWriter.makeTickJSON();


    }

    static void initializeBow() {
        FunctionWriter.makeFunction("bow_base");
        FunctionWriter.addStatment("generic_base", "function flytre:bow_base");

        FunctionWriter.addStatment("bow_arrow_init", "execute as @a run scoreboard players operation @s enderID2 = @s enderID");
        FunctionWriter.addStatment("bow_arrow_init", "execute as @s[type=arrow,tag=!init] at @s run scoreboard players operation @a enderID2 -= @s enderID");


        FunctionWriter.addObj("enderID");
        FunctionWriter.addObj("enderID2");
        FunctionWriter.addObj("arrowDetection");

        FunctionWriter.addStatment("bow_base", "execute as @a store result score @s enderID run data get entity @s UUIDLeast 0.00000000001");
        FunctionWriter.addStatment("bow_base", "execute as @e[type=arrow] at @s store result score @s enderID run data get entity @s OwnerUUIDLeast 0.00000000001");


    }

    static void initializeSword() {

        FunctionWriter.makeFunction("sword_base");
        FunctionWriter.addStatment("generic_base", "function flytre:sword_base");

        FunctionWriter.addObj("damageDealt", "minecraft.custom:minecraft.damage_dealt");
        FunctionWriter.addObj("killedEntity", "totalKillCount");


    }

    static void initializeAbility() {

        FunctionWriter.makeFunction("ability_base");
        FunctionWriter.addStatment("generic_base", "function flytre:ability_base");

        FunctionWriter.addObj("rightclick", "minecraft.used:minecraft.carrot_on_a_stick");
        FunctionWriter.addObj("custom_item");

    }

    static void initializeTrident() {
        FunctionWriter.makeFunction("trident_base");
        FunctionWriter.addStatment("generic_base", "function flytre:trident_base");
    }

    static void initializeShield() {
        FunctionWriter.makeFunction("shield_base");
        FunctionWriter.addStatment("generic_base", "function flytre:shield_base");
        FunctionWriter.addObj("shieldblock", "minecraft.custom:minecraft.damage_blocked_by_shield");
    }


    /*
    hasPlayerSelector false: @s references the arrow
    hasPlayerSelector true: @s references the arrow, @a[tag=bow_<id>] references the shooter
    */
    static void addBow(CustomBow bow) {

        FunctionWriter.addStatment("bow_base", "execute as @a[nbt={SelectedItem:{tag:{ability:\"" + bow.getId() + "\"}}}] at @s run execute as @e[type=arrow,distance=..10,tag=!init,limit=1] at @s run function flytre:bow_arrow_init");
        FunctionWriter.addStatment("bow_arrow_init", "execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:\"" + bow.getId() + "\"}}},distance=..10] run tag @s add custom_arrow");
        FunctionWriter.addStatment("bow_arrow_init", "execute as @s[type=arrow,tag=!init] at @s if entity @a[scores={enderID2=0},nbt={SelectedItem:{tag:{ability:\"" + bow.getId() + "\"}}},distance=..10] run tag @s add " + bow.getId());

        if (bow.getTrail() != null)
            FunctionWriter.addStatment("bow_base", "execute as @e[type=arrow,tag=" + bow.getId() + ",nbt={inGround:0b}] at @s positioned ~ ~.5 ~ run particle " + bow.getTrail() + " ~ ~-" + bow.getTrailRad() + " ~0 0 " + (bow.getTrailRad() * 2) + " 0 0 30 force");


        FunctionWriter.addStatment("bow_base", "execute as @e[type=arrow,tag=" + bow.getId() + ",nbt={inGround:0b}] at @s run kill @e[type=armor_stand,distance=..8,tag=" + bow.getId() + ",limit=1]");
        FunctionWriter.addStatment("bow_base", "execute as @e[type=arrow,tag=" + bow.getId() + ",nbt={inGround:0b}] at @s run summon armor_stand ~ ~1 ~ {Tags:[" + bow.getId() + ",arrow_stand],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}");
        FunctionWriter.addStatment("bow_base", "execute as @e[type=arrow,tag=" + bow.getId() + "] at @s store result score @e[type=armor_stand,tag=" + bow.getId() + ",limit=1,distance=..8] enderID run data get entity @s OwnerUUIDLeast 0.00000000001");

        if (!bow.hasPlayerSelector()) {
            if (bow.getEffect() != null)
                for (String s : bow.getEffect())
                    FunctionWriter.addStatment("bow_base", "execute as @e[type=armor_stand,tag=" + bow.getId() + ",scores={arrowDetection=3}] at @s run " + s);


        } else {

            FunctionWriter.addObj("enderID" + (++enderIDCounter));

            FunctionWriter.makeFunction("bows/" + bow.getId());

            FunctionWriter.addStatment("bow_base", "execute as @e[type=armor_stand,tag=" + bow.getId() + ",scores={arrowDetection=3}] at @s run function flytre:bows/" + bow.getId());


            FunctionWriter.addStatment("bows/" + bow.getId(), "execute as @s run execute as @a run scoreboard players operation @s enderID" + enderIDCounter + " = @s enderID");
            FunctionWriter.addStatment("bows/" + bow.getId(), "execute as @s run scoreboard players operation @a enderID" + enderIDCounter + " -= @s enderID");
            FunctionWriter.addStatment("bows/" + bow.getId(), "tag @a[scores={enderID" + enderIDCounter + "=0}] add bow_" + bow.getId());
            if (bow.getEffect() != null)
                for (String s : bow.getEffect()) {
                    FunctionWriter.addStatment("bows/" + bow.getId(), "execute as @s run " + s);
                }
            FunctionWriter.addStatment("bows/" + bow.getId(), "tag @a remove bow_" + bow.getId());
            FunctionWriter.addStatment("bows/" + bow.getId(), "scoreboard players reset * enderID" + enderIDCounter);
            FunctionWriter.addStatment("bows/" + bow.getId(), "tag @s add effectDone");
            FunctionWriter.addStatment("bows/" + bow.getId(), "execute as @e[type=armor_stand,tag=" + bow.getId() + ",tag=!effectDone,scores={arrowDetection=3},sort=random,limit=1] at @s run function flytre:bows/" + bow.getId());

        }


        FunctionWriter.addStatment("info", "tellraw @s [\"\",{\"text\":\"/give @s bow{ability:\\\"" + bow.getId() + "\\\"}\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/give @s bow{ability:\\\"" + bow.getId() + "\\\"}\"}},{\"text\":\": give yourself a(n) " + bow.getDisplayName() + " bow.\",\"color\":\"green\"}]");

    }


    /*
    Type: damage_as_victim: @s references the hurt entity
    Type: damage_as_attacker: @s references the attacker
    Type: kill_as_attacker: @s references the killer
     */
    static void addSword(CustomSword sword) {

        if (sword.getEffect() != null)
            for (String s : sword.getEffect()) {

                if (sword.getType().equals("damage_as_victim"))
                    FunctionWriter.addStatment("sword_base", "execute as @a[scores={damageDealt=1..},nbt={SelectedItem:{tag:{ability:\"" + sword.getId() + "\"}}}] at @s run execute as @e[nbt={HurtTime:10s},distance=..5,limit=1] at @s run " + s);

                else if (sword.getType().equals("damage_as_attacker"))
                    FunctionWriter.addStatment("sword_base", "execute as @a[scores={damageDealt=1..},nbt={SelectedItem:{tag:{ability:\"" + sword.getId() + "\"}}}] at @s run " + s);
                else if (sword.getType().equals("kill_as_attacker"))
                    FunctionWriter.addStatment("sword_base", "execute as @a[scores={killedEntity=1..},nbt={SelectedItem:{tag:{ability:\"" + sword.getId() + "\"}}}] at @s run " + s);

            }
        if (sword.getType().equals("damage_as_victim") || sword.getType().equals("damage_as_attacker"))
            FunctionWriter.addStatment("sword_base", "execute as @a[scores={damageDealt=1..},nbt={SelectedItem:{tag:{ability:\"" + sword.getId() + "\"}}}] at @s run execute as @e[nbt={HurtTime:10s},distance=..5,limit=1] at @s run particle " + sword.getParticle() + " ~-0.1 ~0.65 ~-.1 0.2 0.75 0.2 0 35 force");
        if (sword.getType().equals("kill_as_attacker"))
            FunctionWriter.addStatment("sword_base", "execute as @a[scores={killedEntity=1..},nbt={SelectedItem:{tag:{ability:\"" + sword.getId() + "\"}}}] at @s run execute as @e[nbt={HurtTime:10s},distance=..5,limit=1] at @s run particle " + sword.getParticle() + " ~-0.1 ~0.65 ~-.1 0.2 0.75 0.2 0 20 force");


        FunctionWriter.addStatment("info", "tellraw @s [\"\",{\"text\":\"/give @s diamond_sword{ability:\\\"" + sword.getId() + "\\\"}\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/give @s diamond_sword{ability:\\\"" + sword.getId() + "\\\"}\"}},{\"text\":\": give yourself a(n) " + sword.getDisplayName() + " sword.\",\"color\":\"green\"}]");


    }

    static void addAbility(CustomAbility ability) {

        DecimalFormat df = new DecimalFormat("0.00000");


        FunctionWriter.makeFunction("abilities/" + ability.getId());
        FunctionWriter.addObj(ability.getId() + "_cd");

        FunctionWriter.addStatment("ability_base", "scoreboard players add @a[nbt={SelectedItem:{tag:{ability:\"" + ability.getId() + "\"}}}] " + ability.getId() + "_cd 0");
        FunctionWriter.addStatment("ability_base", "scoreboard players add @a[scores={" + ability.getId() + "_cd=..-1}] " + ability.getId() + "_cd 1");

        FunctionWriter.addStatment("ability_base", "scoreboard players remove @a[scores={" + ability.getId() + "_cd=..-1}] " + ability.getId() + "_cd " + (ability.getCooldown() * 20 / 24));
        FunctionWriter.addStatment("ability_base", "execute as @a store result entity @s Inventory[{tag:{ability:\"" + ability.getId() + "\"}}].tag.Damage int " + df.format(-24.0 / (ability.getCooldown() * 20.0)) + " run scoreboard players get @s " + ability.getId() + "_cd");
        FunctionWriter.addStatment("ability_base", "scoreboard players add @a[scores={" + ability.getId() + "_cd=..-1}] " + ability.getId() + "_cd " + (ability.getCooldown() * 20 / 24));
        FunctionWriter.addStatment("ability_base", "execute as @a[scores={rightclick=1..," + ability.getId() + "_cd=0},nbt={SelectedItem:{tag:{ability:\"" + ability.getId() + "\"}}}] at @s run function flytre:abilities/" + ability.getId());


        FunctionWriter.addStatment("abilities/" + ability.getId(), "");


        if (ability.getSound() != null)
            FunctionWriter.addStatment("abilities/" + ability.getId(), "playsound " + ability.getSound() + " player @s");

        if (ability.getMessage() != null)
            FunctionWriter.addStatment("abilities/" + ability.getId(), "tellraw @s [\"\",{\"text\":\"[\",\"color\":\"green\"},{\"text\":\"Ability\",\"color\":\"none\"},{\"text\":\"]\",\"color\":\"green\"},{\"text\":\":\",\"color\":\"none\"},{\"text\":\" " + ability.getMessage() + "\",\"color\":\"none\"}]");


        if (ability.getEffect() != null)
            for (String s : ability.getEffect()) {
                FunctionWriter.addStatment("abilities/" + ability.getId(), s);
            }

        FunctionWriter.addStatment("abilities/" + ability.getId(), "scoreboard players set @s " + ability.getId() + "_cd " + (ability.getCooldown() * -20));

        if (ability.getCircleParticleName() != null && ability.getCircleParticleRadius() > 0) {

            int degreeRotation = (int) (360 / (Math.PI * 0.75 * 2 * ability.getCircleParticleRadius()));
            FunctionWriter.makeFunction("abilities/" + ability.getId() + "_circle_2");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle_2", "particle " + ability.getCircleParticleName() + " ^ ^ ^" + ability.getCircleParticleRadius() + " 0 0 0 0 1 force");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle_2", "tp @s ~ ~ ~ ~" + degreeRotation + " ~");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle_2", "execute unless entity @s[y_rotation=0.." + degreeRotation + "] at @s run function flytre:abilities/" + ability.getId() + "_circle_2");

            FunctionWriter.makeFunction("abilities/" + ability.getId() + "_circle");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle", "summon armor_stand ~ ~ ~ {Tags:[" + ability.getId() + "_c],NoGravity:1b,Small:1,Marker:1b,Invisible:1,Invulnerable:1,NoBasePlate:1,PersistenceRequired:1,DisabledSlots:2039583}");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle", "execute as @e[tag=" + ability.getId() + "_c,type=armor_stand] at @s run tp @s ~ ~ ~ " + (degreeRotation + 1) + " 0");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle", "execute as @e[tag=" + ability.getId() + "_c,type=armor_stand] at @s run function flytre:abilities/" + ability.getId() + "_circle_2");
            FunctionWriter.addStatment("abilities/" + ability.getId() + "_circle", "kill @e[type=armor_stand,tag=" + ability.getId() + "_c]");


        }

        if (ability.getOverTimeEffect() != null && ability.getOverTimeDuration() > 0)
            for (String s : ability.getOverTimeEffect()) {
                FunctionWriter.makeFunction("abilities/" + ability.getId() + "_time");
                FunctionWriter.addStatment("ability_base", "execute as @a[scores={" + ability.getId() + "_cd=" + (ability.getCooldown() * -20) + ".." + ((ability.getCooldown() - ability.getOverTimeDuration()) * -20) + "}] at @s run function flytre:abilities/" + ability.getId() + "_time");

                FunctionWriter.addStatment("abilities/" + ability.getId() + "_time", s);

            }


        FunctionWriter.addStatment("info", "tellraw @s [\"\",{\"text\":\"/give @s carrot_on_a_stick{ability:\\\"" + ability.getId() + "\\\"}\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/give @s carrot_on_a_stick{ability:\\\"" + ability.getId() + "\\\"}\"}},{\"text\":\": give yourself a(n) " + ability.getDisplayName() + " ability.\",\"color\":\"green\"}]");

    }


    /*
    Type: hurt_entity: @s references the entity
    Type: trident: @s references the trident

    hasPlayerSelector false: @s references the trident/hurt_entity
    hasPlayerSelector true: @s references the trident, @a[tag=tri_<id>] references the thrower
     */
    static void addTrident(CustomTrident trident) {


        if (trident.hasPlayerSelector() && trident.getEffect() != null) {

            FunctionWriter.addObj("enderID" + (++enderIDCounter));

            FunctionWriter.makeFunction("tridents/" + trident.getId());

            if (trident.getType().equals("trident"))
                FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s run function flytre:tridents/" + trident.getId());
            else if (trident.getType().equals("hurt_entity")) {
                FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s run execute as @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] at @s run function flytre:tridents/" + trident.getId());
                if (trident.hasEffectAnyways())
                    FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s unless entity @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] run function flytre:tridents/" + trident.getId());

            }
            FunctionWriter.addStatment("tridents/" + trident.getId(), "execute as @s run execute as @a run scoreboard players operation @s enderID" + enderIDCounter + " = @s enderID");
            FunctionWriter.addStatment("tridents/" + trident.getId(), "execute as @s run scoreboard players operation @a enderID" + enderIDCounter + " -= @s enderID");
            FunctionWriter.addStatment("tridents/" + trident.getId(), "tag @a[scores={enderID" + enderIDCounter + "=0}] add tri_" + trident.getId());

            if (trident.getEffect() != null)
                for (String s : trident.getEffect())
                    FunctionWriter.addStatment("tridents/" + trident.getId(), s);

            FunctionWriter.addStatment("tridents/" + trident.getId(), "tag @a remove tri_" + trident.getId());
            FunctionWriter.addStatment("tridents/" + trident.getId(), "scoreboard players reset * enderID" + enderIDCounter);
            FunctionWriter.addStatment("tridents/" + trident.getId(), "tag @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1,sort=nearest] add effectApplied");

            if (trident.getType().equals("trident"))
                FunctionWriter.addStatment("tridents/" + trident.getId(), "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s run function flytre:tridents/" + trident.getId());
            else if (trident.getType().equals("hurt_entity")) {
                FunctionWriter.addStatment("tridents/" + trident.getId(), "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s run execute as @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] at @s run function flytre:tridents/" + trident.getId());
                if (trident.hasEffectAnyways())
                    FunctionWriter.addStatment("tridents/" + trident.getId(), "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied,limit=1] at @s unless entity @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] run function flytre:tridents/" + trident.getId());

            }


        } else {


            if (trident.getEffect() != null) {
                if (trident.getType().equals("hurt_entity"))
                    for (String s : trident.getEffect()) {
                        FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied] at @s run execute as @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] at @s run " + s);
                        if (trident.hasEffectAnyways())
                            FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied] at @s unless entity @e[limit=1,sort=nearest,nbt={HurtTime:10s},distance=..5] run " + s);
                    }
                else if (trident.getType().equals("trident"))
                    for (String s : trident.getEffect())
                        FunctionWriter.addStatment("trident_base", "execute as @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied] at @s run " + s);
            }

        }
        FunctionWriter.addStatment("trident_base", "tag @e[type=trident,nbt={DealtDamage:1b,Trident:{tag:{ability:\"" + trident.getId() + "\"}}},tag=!effectApplied] add effectApplied");
        FunctionWriter.addStatment("info", "tellraw @s [\"\",{\"text\":\"/give @s trident{ability:\\\"" + trident.getId() + "\\\"}\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/give @s trident{ability:\\\"" + trident.getId() + "\\\"}\"}},{\"text\":\": give yourself a(n) " + trident.getDisplayName() + " trident.\",\"color\":\"green\"}]");

    }

    static void addShield(CustomShield shield) {

        if (shield.getEffect() != null)
            for (String s : shield.getEffect())
                FunctionWriter.addStatment("shield_base", "execute as @a[scores={shieldblock=1..},nbt={SelectedItem:{tag:{ability:\""+shield.getId()+"\"}}}] at @s run " + s);


        if (shield.getPassiveEffect() != null)
            for (String s : shield.getPassiveEffect()) {
                FunctionWriter.addStatment("shield_base", "execute as @a[nbt={SelectedItem:{tag:{ability:\"" + shield.getId() + "\"}}}] at @s run " + s);
                FunctionWriter.addStatment("shield_base", "execute as @a[nbt={Inventory:[{Slot: -106b, tag:{ability:\""+shield.getId()+"\"}}]}] at @s run " + s);
            }

        FunctionWriter.addStatment("info", "tellraw @s [\"\",{\"text\":\"/give @s shield{ability:\\\"" + shield.getId() + "\\\"}\",\"color\":\"gold\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/give @s shield{ability:\\\"" + shield.getId() + "\\\"}\"}},{\"text\":\": give yourself a(n) " + shield.getDisplayName() + " shield.\",\"color\":\"green\"}]");


    }

    static void postInitializeBow() {

        FunctionWriter.addStatment("bow_arrow_init", "execute as @e[type=arrow,tag=!custom_arrow,tag=!init,limit=1] at @s run function flytre:bow_arrow_init");


        FunctionWriter.addStatment("bow_base", "tag @e[type=arrow] add init");
        FunctionWriter.addStatment("bow_base", "scoreboard players add @e[type=armor_stand,tag=arrow_stand] arrowDetection 1");
        FunctionWriter.addStatment("bow_base", "kill @e[type=arrow,tag=custom_arrow,nbt={inGround:1b}]");
        FunctionWriter.addStatment("bow_base", "kill @e[type=armor_stand,tag=arrow_stand,scores={arrowDetection=4..}]");


    }

    static void postInitializeSword() {
        FunctionWriter.addStatment("sword_base", "scoreboard players set @a[scores={damageDealt=1..}] damageDealt 0");
        FunctionWriter.addStatment("sword_base", "scoreboard players set @a[scores={killedEntity=1..}] killedEntity 0");


    }

    static void postInitializeAbility() {

        FunctionWriter.addStatment("ability_base", "scoreboard players set @a[scores={rightclick=1..}] rightclick 0");
        FunctionWriter.addStatment("ability_base", "scoreboard players set @a custom_item 0");
    }

    static void postInitializeTrident() {
        FunctionWriter.addStatment("shield_base", "scoreboard players set @a[scores={shieldblock=1..}] shieldblock 0");
    }

    static void postInitializeShield() {
    }
}
