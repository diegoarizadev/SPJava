package com.hagakure.fundamentos.bean;

import com.hagakure.fundamentos.FundamentosApplication;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{
    MyOperation myOperation;
    private Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Begin - printWithDependency");
        int numberA = 10;
        int numberB = 45;
        System.out.println(myOperation.sum(numberA,numberB));
        LOGGER.debug("Los numeros recibidos como parametros son : a="+numberA+", b="+numberB);
        System.out.println("Hola desde un Bean con dependencia.");
        LOGGER.info("End - printWithDependency");
    }


}
