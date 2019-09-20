package com.flytre.Particles;

public class ParticleRectangle extends ParticleShape {

    private double len;
    private double width;

    public ParticleRectangle(String id, String particle, double len, double width) {
        super(id, particle);

        this.len = len;
        this.width = width;
    }

    @Override
    public void write() {

        double l1 = len/2;
        double w1 = width/2;


        line(-l1,0,-w1,l1,0,-w1);
        line(-l1,0,-w1,-l1,0,w1);

        line(l1,0,w1,-l1,0,w1);
        line(l1,0,w1,l1,0,-w1);



    }

    public static void main(String[] args) {
        ParticleRectangle t = new ParticleRectangle("test","flame",5,3);
        t.write();
    }
}
