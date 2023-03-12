package main.currency;

public interface ICurrency {
    public void setName(String name);
    public void setCode(String code);
    public void setRate(double rate);
    public String getName();
    public String getCode();
    public double getRate();

    @Override
    public String toString();
}
