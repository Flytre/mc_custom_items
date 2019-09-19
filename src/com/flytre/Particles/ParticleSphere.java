package com.flytre.Particles;

import com.flytre.FunctionWriter;

public class ParticleSphere extends ParticleShape {

    private double radius;
    private double degreeRotation;
    private String particleName;

    public ParticleSphere(String id, double radius, String particleName) {
        super(id);
        this.radius = radius;
        this.degreeRotation = (int) (360 / (Math.PI * 0.5 * 2 * radius));
        this.particleName = particleName;
    }


    public void write() {

        FunctionWriter.makeFunction("particles/circle");

        for(double i = 0, j = 0; i < 360; i += degreeRotation, j = i * Math.PI/180) {

            for(double k = 0, l = 0; k < 360; k += degreeRotation, l = k * Math.PI/180) {

                double x = formatDouble(radius*Math.sin(l)*Math.cos(j));
                double y = formatDouble(radius*Math.sin(l)*Math.sin(j));
                double z = formatDouble(radius*Math.cos(l));

                drawParticle("particles/sphere",particleName,x,y,z);
            }
        }
    }


}
