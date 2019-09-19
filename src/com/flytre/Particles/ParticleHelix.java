package com.flytre.Particles;

import com.flytre.FunctionWriter;

public class ParticleHelix extends ParticleShape {

    private double radius;
    private double degreeRotation;
    private String particleName;
    private double rotations;
    private double vertical;

    public ParticleHelix(String id,double radius, String particleName, double rotations,double vertical) {
        super(id);
        this.radius = radius;
        this.degreeRotation = (int) ((360 * vertical) / (Math.PI * 0.75 * rotations * 2 * radius));
        this.particleName = particleName;
        this.rotations = rotations;
        this.vertical = vertical;
    }


    public void write() {

        FunctionWriter.makeFunction("particles/helix");

        for(double i = 0, j = 0; i < 360*rotations; i += degreeRotation, j = i * Math.PI/180) {

            double x = formatDouble(radius*Math.cos(j));
            double y = formatDouble(radius*Math.sin(j));
            double z = formatDouble((i*vertical)/(360*rotations));

            drawParticle("particles/helix",particleName,x,y,z);
        }
    }


}
