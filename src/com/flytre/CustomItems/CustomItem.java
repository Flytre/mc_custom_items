package com.flytre.CustomItems;

import com.flytre.InvalidItemException;

public interface CustomItem {


    String getId();
    String getDisplayName();
    String[] getEffect();

    interface Builder {

        CustomItem build() throws InvalidItemException;
    }
}
