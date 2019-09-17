package com.flytre;

interface CustomItem {


    String getId();
    String getDisplayName();
    String[] getEffect();

    interface Builder {

        CustomItem build() throws InvalidItemException;
    }
}
