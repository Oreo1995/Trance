package com.trance.multi.trance.intf;

public class LambdaDemo {
    public static void  main(String[] args){
        MyListener listener = (a,b)->{
            String result = a+b;
            return result;
        };
        String m = listener.doSomething("5",6);
        System.out.print(m);
    }
}
