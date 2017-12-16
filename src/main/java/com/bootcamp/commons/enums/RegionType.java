package com.bootcamp.commons.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by darextossa on 11/28/17.
 */
public enum RegionType {

    /**
     * The geographical location type City
     */
    @SerializedName("ville")
    VILLE,

    /**
     * The geographical location type Country
     */
    @SerializedName("pays")
    PAYS,

    /**
     * The geographical location type Borough
     */
    @SerializedName("commune")
    COMMUNE,

    /**
     * The geographical location type State
     */
    @SerializedName("departement")
    DEPARTEMENT;
}
