package com.company;

public class Savings {

    static int custno;
    static String custname;
    static double cdep;
    static int nyears;
    static String savtype;

    public int getCustno() {
        return custno;
    }

    public void setCustno(int custno) {
        Savings.custno = custno;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        Savings.custname = custname;
    }

    public double getCdep() {
        return cdep;
    }

    public void setCdep(double cdep) {
        Savings.cdep = cdep;
    }

    public int getNyears() {
        return nyears;
    }

    public void setNyears(int nyears) {
        Savings.nyears = nyears;
    }

    public String getSavtype() {
        return savtype;
    }

    public void setSavtype(String savtype) {
        Savings.savtype = savtype;
    }

    public Savings(){

    }
}
