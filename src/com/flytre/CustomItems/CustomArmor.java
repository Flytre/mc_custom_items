package com.flytre.CustomItems;

import com.flytre.InvalidItemException;

public class CustomArmor implements CustomItem {

    private final String id;
    private final String displayName;
    private final String parts;
    private final String[] effect;


    public CustomArmor(Builder builder) throws InvalidItemException {
        if (!builder.id.matches("[A-Za-z0-9_]{2,14}"))
            throw new InvalidItemException("id", "must be 2-14 alphanumeric characters, with underscores allowed");


        if (!builder.parts.matches("[hclb]{1,4}"))
            throw new InvalidItemException("parts", "");


        this.id = builder.id;
        this.displayName = builder.displayName;
        this.parts = builder.parts;
        this.effect = builder.effect;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String[] getEffect() {
        return new String[0];
    }

    public static class Builder {
        private String id;
        private String displayName;
        private String parts = "hclb";
        private String[] effect = null;

        public Builder(String id) {
            this.id = id;
        }

        public Builder displayName(String name) {
            displayName = name;
            return this;
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

        public Builder parts(String parts) {
            this.parts = parts;
            return this;
        }

        public CustomArmor build() throws InvalidItemException {

            return new CustomArmor(this);

        }

    }
}
