package com.oop;

import org.junit.Test;

public class InstanceofTest {


    public static void main(String[] args) {
//         int var=1;
//         System.out.println(var instanceof Integer);//编译错误
//        System.out.println(var instanceof int);//编译错误
        System.out.println();
        Object obj=new Object();
        String str=(String)obj;
        System.out.println(str instanceof String);
        Integer val=new Integer(2);
       // System.out.println(val instanceof String);//无法编译
       // String str2=(String)val;//无法编译
    }
      @Test
    public void test(){

        Object obj=new Object();
        String ss="1";
        obj=ss;
        Object obj2=new Object();
       String str=(String)obj2;
       System.out.println(str);
    }
    @Test
    public void test2(){
        System.out.println(null instanceof A);
        A a=new C();
        System.out.println(a instanceof A);//true
        System.out.println(a instanceof B);//true
        System.out.println(a instanceof C);//true
        A b=new B();
        C c=(C)b;
        System.out.println(c instanceof B);
        System.out.println(c instanceof C);
    }
}
interface A{

}
class B implements A{

}
class C extends B{

}