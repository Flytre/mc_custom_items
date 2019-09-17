package com.flytre;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class FunctionWriter {

    private static final String dataLoc = "flytre_custom_items/data";

     static void deleteOld() {
        FileHandler.deleteDirectory("flytre_custom_items");
    }


     static void createDatapack() {
        FileHandler.createDatapack("flytre_custom_items");
    }

     static void makeTickJSON() {
        FileHandler.setOutput(dataLoc + "/minecraft/tags/functions/tick.json");

        FileHandler.print("{\n" +
                "  \"replace\": false,\n" +
                "  \"values\": [\n" +
                "    \"flytre:generic_base\"\n" +
                "  ]\n" +
                "}");
    }

     static void makeLoadJSON() {
        FileHandler.setOutput(dataLoc + "/minecraft/tags/functions/load.json");

        FileHandler.print("{\n" +
                "  \"replace\": false,\n" +
                "  \"values\": [\n" +
                "    \"flytre:init_items\"\n" +
                "  ]\n" +
                "}");
    }

     static void makeFunction(String name) {
        FileHandler.setOutput(dataLoc + "/flytre/functions/" + name + ".mcfunction");

        FileHandler.print("####################################################################################################");

        FileHandler.print("#Automatically Generated File");

        FileHandler.print("#Created Using: Flytre's Custom Item Generator");

         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
         FileHandler.print("#Created: " + dateFormat.format(new Date()));

        FileHandler.print("####################################################################################################");

        FileHandler.print("");

    }


     static void addStatment(String func, String statment) {
        FileHandler.setOutput(dataLoc + "/flytre/functions/"+func+".mcfunction");

        FileHandler.print(statment);
    }


     static void addObj(String name) {
        FileHandler.setOutput(dataLoc + "/flytre/functions/init_items.mcfunction");

        FileHandler.print("scoreboard objectives add " + name + " dummy");
    }

     static void addObj(String name, String criteria) {
        FileHandler.setOutput(dataLoc + "/flytre/functions/init_items.mcfunction");

        FileHandler.print("scoreboard objectives add " + name + " " + criteria);

    }


}
