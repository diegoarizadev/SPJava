package com.hagakure.fundamentos;

import com.hagakure.fundamentos.bean.MyBean;
import com.hagakure.fundamentos.bean.MyBeanWithDependency;
import com.hagakure.fundamentos.bean.MyBeanWithDependencyImplement;
import com.hagakure.fundamentos.component.ComponentDependecy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private ComponentDependecy componentDependecy;
	private MyBean myBean;

	private MyBeanWithDependency myBeanWithDependencyImplement;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependecy componentDependecy, MyBean bean, MyBeanWithDependency beanWhitDependency){
		this.componentDependecy = componentDependecy;
		this.myBean = bean;
		this.myBeanWithDependencyImplement = beanWhitDependency;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		componentDependecy.saludar();
		myBean.print();
		myBeanWithDependencyImplement.printWithDependency();
	}
}
