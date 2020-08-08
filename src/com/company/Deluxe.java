package com.company;

public class Deluxe extends Savings implements Compound_Interest{

    double rate = 0.0;

    Savings s = new Savings();

    public Deluxe(int custno, String custname, double cdep, int nyears, String savtype, double r){
        s.setCustno(custno);
        s.setCustname(custname);
        s.setCdep(cdep);
        s.setNyears(nyears);
        s.setSavtype(savtype);
        this.rate = r;
    }

    @Override
    public void generateTable() {

        float r,  t,  si; // principal amount, rate, time and simple interest respectively

        r = 12; t = 2;
        si  = (float) ((nyears*r*t)/100);
        System.out.println("Simple Interest is: " +si);
    }
}
