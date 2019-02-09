package com.example.business.friedrich.kuzan.business.model.wraps_for_eventbus;

import com.example.business.friedrich.kuzan.business.model.enumeration.Position;

public class EBPosition {

    private Position mPosition;

    public EBPosition(Position mPosition) {
        this.mPosition = mPosition;
    }

    public Position getmPosition() {
        return mPosition;
    }

    public void setmPosition(Position mPosition) {
        this.mPosition = mPosition;
    }
}
