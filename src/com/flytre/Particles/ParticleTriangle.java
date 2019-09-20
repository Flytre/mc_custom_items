package com.flytre.Particles;

import com.flytre.FunctionWriter;

public class ParticleTriangle extends ParticleShape {
    private double rad;

    public ParticleTriangle(String id, String particle, double rad) {
        super(id, particle);
        this.rad = rad;
    }

    @Override
    public void write() {
        double x1 = rad * Math.sin(240 * Math.PI/180);
        double z1 = rad * Math.cos(240 * Math.PI/180);
        double x2 = rad * Math.sin(0 * Math.PI/180);
        double z2 = rad * Math.cos(0 * Math.PI/180);
        double x3 = rad * Math.sin(120 * Math.PI/180);
        double z3 = rad * Math.cos(120 * Math.PI/180);

        line(x1,0,z1,x2,0,z2);
        line(x1,0,z1,x3,0,z3);
        line(x2,0,z2,x3,0,z3);
    }

    public static void main(String[] args) {
        ParticleTriangle t = new ParticleTriangle("test","flame",5);
        t.write();

        FunctionWriter.makeFunction("particles/execute");
        for(int i = 0; i < 360; i+= 8)
            FunctionWriter.addStatment("particles/execute","execute rotated " + i + " 60 run function flytre:particles/test");

    }
}
