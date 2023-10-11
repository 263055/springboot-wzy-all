package com.example.loser.core.imp;

import com.example.loser.core.Game;
import com.example.loser.core.IVisitor;

public class VipGame extends Game {

    public int getmVipPrice() {
        return mVipPrice;
    }

    public void setmVipPrice(int mVipPrice) {
        this.mVipPrice = mVipPrice;
    }

    private int mVipPrice;

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

}
