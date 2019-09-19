package com.flytre.CustomItems;

import com.flytre.InvalidItemException;

public class CustomShield implements CustomItem {

    private final String id;
    private final String displayName;
    private final String[] effect;
    private final String[] passiveEffect;

    private CustomShield(Builder builder) throws InvalidItemException {

        if(!builder.id.matches("[A-Za-z0-9_]{2,14}"))
            throw new InvalidItemException("id","must be 2-14 alphanumeric characters, with underscores allowed");


        this.id = builder.id;
        this.displayName = builder.displayName;
        this.effect = builder.effect;
        this.passiveEffect = builder.passiveEffect;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String[] getEffect() {
        return effect;
    }

    public String[] getPassiveEffect() {
        return passiveEffect;
    }

    public static class Builder implements CustomItem.Builder {
        String id;
        String displayName;
        String[] effect = null;
        String[] passiveEffect = null;

        public Builder(String id) {
            this.id = id;
            this.displayName = id;
        }

        public Builder effect(String single) {
            effect = new String[1];
            effect[0] = single;
            return this;
        }

        public Builder effect(String[] multiple) {
            effect = multiple;
            return this;
        }

        public Builder passiveEffect(String single) {
            passiveEffect = new String[1];
            passiveEffect[0] = single;
            return this;
        }

        public Builder passiveEffect(String[] multiple) {
            passiveEffect = multiple;
            return this;
        }

        public CustomShield build() throws InvalidItemException {
            return new CustomShield(this);
        }
    }
}
