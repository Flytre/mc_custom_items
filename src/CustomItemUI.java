import java.lang.reflect.Field;
import java.util.ArrayList;

public class CustomItemUI {

    private static ArrayList<CustomItem> items = new ArrayList<>();


    public static void addObjective(String objName) {
        FunctionWriter.addObj(objName);
    }

    public static void addObjective(String objName, String criteria) {
        FunctionWriter.addObj(objName, criteria);
    }

    public static void addItem(CustomItem.Builder item) {

        try {
            items.add(item.build());
        } catch (InvalidItemException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generateDatapack() {

        Implementer.initialize();

        boolean bow, sword, ability, trident, shield = false;
        bow = sword = ability = trident = shield;

        for (CustomItem i : items) {
            if (i instanceof CustomBow) {
                if (!bow) Implementer.initializeBow();
                bow = true;
                Implementer.addBow((CustomBow) i);

            } else if (i instanceof CustomSword) {
                if(!sword) Implementer.initializeSword();
                sword = true;
                Implementer.addSword((CustomSword) i);

            } else if (i instanceof CustomAbility) {
                if(!ability) Implementer.initializeAbility();
                ability = true;
                Implementer.addAbility((CustomAbility) i);

            } else if (i instanceof CustomTrident) {
                if(!trident) Implementer.initializeTrident();
                trident = true;
                Implementer.addTrident((CustomTrident) i);

            } else if (i instanceof CustomShield) {
                if(!shield) Implementer.initializeShield();
                shield = true;
                Implementer.addShield((CustomShield) i);


            }

            if(bow) Implementer.postInitializeBow();
            if(sword) Implementer.postInitializeSword();
            if(ability) Implementer.postInitializeAbility();
            if(trident) Implementer.postInitializeTrident();
            if(shield) Implementer.postInitializeShield();

        }

    }

    public static void main(String[] args) {

        CustomBow.Builder explosive = new CustomBow.Builder("explosive").effect("summon fireball ~ ~ ~ {direction:[0.0,-100.0,0.0],ExplosionPower:2.5,Motion:[0.0,-1.0,0.0]}").trail("flame");
        CustomBow.Builder warp = new CustomBow.Builder("warp").effect("tp @a[tag=bow_warp] @s").trail("portal").addPlayerSelector(true);
        CustomSword.Builder barbed = new CustomSword.Builder("barbed").effect("effect give @s minecraft:slowness 1 1").particle("cloud");
        CustomAbility.Builder test = new CustomAbility.Builder("test").cooldown(45).circleParticleName("cloud").circleParticleRadius(3.0).effect("summon creeper").overTimeEffect("function flytre:abilities/test_circle").overTimeDuration(2).message("You are testing this build");
        CustomAbility.Builder circler = new CustomAbility.Builder("circler").cooldown(20).circleParticleName("flame").circleParticleRadius(20.0).overTimeEffect("function flytre:abilities/circler_circle").overTimeDuration(19).message("Activating the circler!");
        CustomTrident.Builder trident = new CustomTrident.Builder("squeaker").type("hurt_entity").effect("summon lightning_bolt").runEffectTypeHurtEntityIfNoHurtEntity(true).addPlayerSelector(true);
        CustomShield.Builder shield = new CustomShield.Builder("lit").passiveEffect("effect give @e[type=!player,distance=..4] wither 1 1 true").effect("effect give @s regeneration 1 1 true");

        addItem(explosive);
        addItem(warp);
        addItem(barbed);
        addItem(test);
        addItem(circler);
        addItem(trident);
        addItem(shield);

        generateDatapack();


    }
}
