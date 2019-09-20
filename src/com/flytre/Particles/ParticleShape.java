package com.flytre.Particles;

import com.flytre.FunctionWriter;

public abstract class ParticleShape {

    protected String id;
    protected String particle;

    public ParticleShape(String id, String particle) {
        this.id = id;
        this.particle = particle;
    }

    public String getId() {
        return id;
    }

    public String getParticle() {
        return particle;
    }

    public abstract void write();


    public static double formatDouble(double d) {
        return Double.parseDouble(String.format("%.3f", d));
    }

    public void drawParticle(double x, double y, double z) {
        FunctionWriter.addStatment("particles/" + id, "particle " + particle + " ^" + (Math.round(x * 100.0) / 100.0) + " ^" + (Math.round(z * 100.0) / 100.0) + " ^" + (Math.round(y * 100.0) / 100.0) + " 0 0 0 0 1 force");

    }



    public void line(double x, double y, double z, double x2, double y2, double z2) {

        double deltaX = x2 - x, deltaY = y2 - y, deltaZ = z2 - z;
        double ratioY = deltaX == 0 ? deltaY : deltaY / deltaX, ratioZ = deltaX == 0 ? deltaZ : deltaZ / deltaX;


        double dist = Math.hypot(Math.hypot(deltaX, deltaY), deltaZ);
        int points = (int) (dist * 5);
        if(deltaX == 0) deltaX = 0.0001;

        for (double ix = 0; ix < Math.abs(deltaX); ix += Math.abs(deltaX) / (double) points) {
            double changeInX = (ix / Math.abs(deltaX)) * deltaX;
            double changeInY = (ix / Math.abs(deltaX)) * deltaY;
            double changeInZ = (ix / Math.abs(deltaX)) * deltaZ;
            double finalX = changeInX + x, finalY = changeInY + y, finalZ = changeInZ + z;

            drawParticle(finalX, finalZ, finalY);
        }

    }

}
