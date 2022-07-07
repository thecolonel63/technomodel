package com.thecolonel63.technomodel;

import net.minecraft.entity.Entity;

public class Technomodel {

    public static boolean isTechno(Entity entity) {
        return entity.getCustomName() != null && entity.getCustomName().getString().equals("Technoblade");
    }
}
