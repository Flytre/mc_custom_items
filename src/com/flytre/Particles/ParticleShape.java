package com.flytre.Particles;

import com.flytre.FunctionWriter;

public abstract class ParticleShape {

    private String id;

    public ParticleShape(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract void write();


    public static double formatDouble(double d) {
        return Double.parseDouble(String.format("%.3f", d));
    }

    public static void drawParticle(String file,String particle, double x, double y, double z) {
        FunctionWriter.addStatment(file,"particle "+particle+" ~"+x+" ~"+z+" ~"+y+" 0 0 0 0 1 force");

    }

}
