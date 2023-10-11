package com.example.loser.biz;

import com.example.loser.core.Car;
import com.example.loser.core.Goods;

import java.util.List;

public class Camera extends Goods {

    public Camera(Car car) {
        super(car);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 500;
    }

    @Override
    public List<String> getNames() {
        List<String> names = super.getNames();
        names.add("行车记录仪");
        return names;
    }
}
