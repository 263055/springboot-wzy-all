package com.example.loser.core.imp;

import com.example.loser.core.Game;
import com.example.loser.core.IVisitor;

public class FreeGame extends Game {
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
