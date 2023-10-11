package com.example.loser.biz;

import com.example.loser.core.Car;
import com.example.loser.core.Goods;

import java.util.List;

public class CarMat extends Goods {

    public CarMat(Car car) {
        super(car);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 200;
    }

    @Override
    public List<String> getNames() {
        List<String> names = super.getNames();
        names.add("全包车垫");
        return names;
    }

}
