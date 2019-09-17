public class CustomSword implements CustomItem  {

    private final String id;
    private final String displayName;
    private final String[] effect;
    private final String particle;
    private final String type;


    private CustomSword(Builder builder) throws InvalidItemException {

        if(!builder.id.matches("[A-Za-z0-9_]{2,14}"))
            throw new InvalidItemException("id","must be 2-14 alphanumeric characters, with underscores allowed");

        if(!(builder.type.equals("damage_as_victim") || builder.type.equals("damage_as_attacker") || builder.type.equals("kill_as_attacker")))
            throw new InvalidItemException("type","must be damage_as_victim, damage_as_attacker, or kill_as_attacker");

        id = builder.id;
        effect = builder.effect;
        displayName = builder.displayName;
        particle = builder.particle;
        type = builder.type;
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

    public String getParticle() {
        return particle;
    }

    public String getType() {
        return type;
    }

    public static class Builder implements CustomItem.Builder {

        private String id;
        private String displayName;
        private String[] effect = null;
        private String particle = null;
        private String type = "damage_as_victim";

        public Builder(String id) {
            this.id = id;
            this.displayName = id;
        }

        public Builder displayName(String name) {
            displayName = name;
            return this;
        }

        public Builder particle(String name) {
            particle = name;
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

        public Builder type(String t) {
            type = t;
            return this;
        }

        public CustomSword build() throws InvalidItemException {
            return new CustomSword(this);
        }

    }
}
