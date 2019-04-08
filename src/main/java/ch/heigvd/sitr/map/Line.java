package ch.heigvd.sitr.map;

import lombok.Getter;

public class Line {

    private int lineId;
    private int length;
    private float width;
    private int speedLimit;
    private int xCoordinatesBegin;
    private int yCoordinatesBegin;
    private int xCoordinatesEnd;
    private int yCoordinatesEnd;
    private Road r;

    Line(int xCoordinatesBegin, int yCoordinatesBegin, int xCoordinatesEnd, int yCoordinatesEnd, int lenght,
         float width, int nbLaneLeft, int nbLaneRight, int lineId) {

        this.xCoordinatesBegin = xCoordinatesBegin;
        this.yCoordinatesBegin = yCoordinatesBegin;
        this.xCoordinatesEnd = xCoordinatesEnd;
        this.yCoordinatesEnd = yCoordinatesEnd;
        this.length = lenght;
        this.width = width;
        r = new Road(xCoordinatesBegin, yCoordinatesBegin, xCoordinatesEnd, yCoordinatesEnd,
                lenght, width, nbLaneLeft, nbLaneRight, speedLimit, lineId);
    }

}
