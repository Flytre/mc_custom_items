public class CustomTrident implements CustomItem {

    private final String id;
    private final String displayName;
    private final String[] effect;
    private final String type;
    private final boolean effectRegardless;
    private final boolean playerSelector;

    public CustomTrident(Builder builder) throws InvalidItemException {

        if(!builder.id.matches("[A-Za-z0-9_]{2,11}"))
            throw new InvalidItemException("id","must be 2-11 alphanumeric characters, with underscores allowed");

        if(!( builder.type.equals("hurt_entity") || builder.type.equals("trident")))
            throw new InvalidItemException("type","must be hurt_entity, or trident");

        this.id = builder.id;
        this.displayName = builder.displayName;
        this.effect = builder.effect;
        this.type = builder.type;
        this.effectRegardless = builder.hurtEntityIfNoEntity;
        this.playerSelector = builder.playerSelector;

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

    public String getType() {
        return type;
    }

    public boolean hasEffectAnyways() {
        return effectRegardless;
    }

    public boolean hasPlayerSelector() {
        return playerSelector;
    }

    public static class Builder implements CustomItem.Builder {

        private String id;
        private String displayName;
        private String[] effect = null;
        private String type = "hurt_entity";  //hurt_entity / trident
        private boolean hurtEntityIfNoEntity = false;
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

        public Builder runEffectTypeHurtEntityIfNoHurtEntity(boolean b) {
            hurtEntityIfNoEntity = b;
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

        public Builder type(String t) {
            type = t;
            return this;
        }

        public Builder addPlayerSelector(boolean b) {
            playerSelector = b;
            return this;
        }

        public CustomTrident build() throws InvalidItemException {
            return new CustomTrident(this);
        }


    }
}
