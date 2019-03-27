package com.zimu.design.composite;

/**
 * 加盟店
 */
public class MarketJoin extends Market {

    public MarketJoin(String s) {
        this.name = s;

    }

    @Override
    public void add(Market m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Market m) {
        // TODO Auto-generated method stub

    }

    @Override
    public void PayByCard() {
        // TODO Auto-generated method stub
        System.out.println(name + "消费,积分已累加入该会员卡");
    }
}
