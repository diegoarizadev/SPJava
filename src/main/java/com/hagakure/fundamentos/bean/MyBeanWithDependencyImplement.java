package com.hagakure.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        System.out.println(myOperation.sum(10,999));
        System.out.println("Hola desde un Bean con dependencia.");
    }


}
