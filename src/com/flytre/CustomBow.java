package com.flytre;

public class CustomBow implements CustomItem {

    private final String id;
    private final String[] effect;
    private final String trail;
    private final double trailRad;
    private final String displayName;
    private final boolean playerSelector;

    private CustomBow(Builder builder) throws InvalidItemException {
        if(!builder.id.matches("[A-Za-z0-9_]{2,11}"))
            throw new InvalidItemException("id","must be 2-11 alphanumeric characters, with underscores allowed");

        if(builder.trailRadius < 0)
            throw new InvalidItemException("trail radius", "must be 0 or a postive number");


        id = builder.id;
        effect = builder.effect;
        trail = builder.trail;
        trailRad = builder.trailRadius;
        displayName = builder.displayName;
        playerSelector = builder.playerSelector;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String[] getEffect() {
        return effect;
    }

    public String getTrail() {
        return trail;
    }
    public boolean hasPlayerSelector() {
        return playerSelector;
    }

    public double getTrailRad() {
        return trailRad;
    }

    public static class Builder implements CustomItem.Builder {

        private String id;
        private String displayName;
        private String[] effect = null;
        private String trail = null;
        private double trailRadius = 0.06;
        private boolean playerSelector = false;

        public Builder(String id) {
            this.id = id;
            this.displayName = id;
        }

        public Builder effect(String single) {
            effect = new String[1];
            effect[0] = single;
            return this;
        }

        public Builder displayName(String name) {
            displayName = name;
            return this;
        }

        public Builder effect(String[] multiple) {
            effect = multiple;
            return this;
        }

        public Builder trail(String particleName) {
            trail = particleName;
            return this;
        }

        public Builder trailRadius(double rad) {
            trailRadius = rad;
            return this;
        }

        public Builder addPlayerSelector(boolean b) {
            playerSelector = b;
            return this;
        }

        public CustomBow build() throws InvalidItemException {
            return new CustomBow(this);
        }

    }

}
