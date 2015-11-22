package org.journerist.testutils.enums;

public enum ReplaceableTag {
    RTAG_1_TO_1("one-to-one"), RTAG_1_TO_N("one-to-many"), RTAG_N_TO_1("many-to-one"), RTAG_N_TO_N("many-to-many");

    String relationTagName;

    ReplaceableTag(String s) {
        relationTagName = s;
    }

    public static ReplaceableTag getByRelationTagName(String relationTagName) {
        ReplaceableTag[] enumConstants = ReplaceableTag.values();
        for(ReplaceableTag enumValue : enumConstants) {
            if(relationTagName.equals(enumValue.relationTagName)){
                return enumValue;
            }
        }

        return null;
    }
}
