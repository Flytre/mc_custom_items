package com.flytre.Particles;

import com.flytre.FunctionWriter;

public class ParticleCircle extends ParticleShape {

    private double radius;
    private double degreeRotation;
    private String particleName;

    public ParticleCircle(String id,double radius, String particleName) {
        super(id);
        this.radius = radius;
        this.degreeRotation = (int) (360 / (Math.PI * 0.75 * 2 * radius));
        this.particleName = particleName;
    }


    public void write() {

        FunctionWriter.makeFunction("particles/circle");

        for(double i = 0, j = 0; i < 360; i += degreeRotation, j = i * Math.PI/180) {

            double x = formatDouble(radius*Math.cos(j));
            double y = formatDouble(radius*Math.sin(j));

            drawParticle("particles/circle",particleName,x,y,0);

        }
    }

}
