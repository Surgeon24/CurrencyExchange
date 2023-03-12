package main.exchange;


import main.currency.ICurrency;

public class Exchange implements IExchange{
    @Override
    public double exchange(ICurrency src, ICurrency tgt, double amt){
        double src_r = src.getRate();
        double tgt_r = tgt.getRate();
        return amt * src_r / tgt_r;
    }
}
