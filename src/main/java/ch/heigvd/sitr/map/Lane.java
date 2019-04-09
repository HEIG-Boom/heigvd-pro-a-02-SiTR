package ch.heigvd.sitr.map;

import lombok.Getter;



public class Lane {

    @Getter
    private int laneId;

    public static final int ON_LEFT = -1;
    public static final int ON_RIGHT = 1;

    Lane(){

    }
}
