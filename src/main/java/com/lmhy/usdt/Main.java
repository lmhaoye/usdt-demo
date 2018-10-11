package com.lmhy.usdt;

public class Main {
    public static void main(String[] args) throws Throwable {
        String result = CoinUtils.getInstance().getInfo();
        System.out.println(result);
//        System.out.println(CoinUtils.getInstance().getNewaddress());
        System.out.println(CoinUtils.getInstance().validateaddress("mxZ3wavDsQxdX8q84Ci9cvNAHDePiFw138"));
    }
}
