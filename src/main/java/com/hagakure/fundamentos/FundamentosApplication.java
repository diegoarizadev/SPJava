package com.hagakure.fundamentos;

import com.hagakure.fundamentos.bean.MyBean;
import com.hagakure.fundamentos.bean.MyBeanWhitProperties;
import com.hagakure.fundamentos.bean.MyBeanWithDependency;
import com.hagakure.fundamentos.component.ComponentDependecy;
import com.hagakure.fundamentos.entity.User;
import com.hagakure.fundamentos.pojo.UserPojo;
import com.hagakure.fundamentos.repository.UserRepository;
import com.hagakure.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {


	private Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private final MyBeanWithDependency myBeanWithDependencyImplement;
	private ComponentDependecy componentDependecy;
	private MyBean myBean;
	private MyBeanWhitProperties myBeanWhitProperties;
	private UserPojo userPojo;
	private UserRepository repository; //Injección del repositorio.

	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependecy componentDependecy, MyBean bean, MyBeanWithDependency beanWhitDependency, MyBeanWhitProperties beanProperties, UserPojo pojo, UserRepository userRepository, UserService service){
		this.componentDependecy = componentDependecy;
		this.myBean = bean;
		this.myBeanWithDependencyImplement = beanWhitDependency;
		this.myBeanWhitProperties = beanProperties;
		this.userPojo = pojo;
		this.repository = userRepository;
		this.userService = service;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//examplesOld();
		saveUsersInDataBase();
		getInformationJPQLFromUser();
		saveWhitErrorTransactional();
	}

	private void examplesOld(){
		componentDependecy.saludar();
		myBean.print();
		myBeanWithDependencyImplement.printWithDependency();
		System.out.println(myBeanWhitProperties.function());
		System.out.println(userPojo.getEmail() + " -- " + userPojo.getEdad());
		LOGGER.error("Esto es un error!");
	}

	//Metodo para persistir informacion en la BD
	private void saveUsersInDataBase(){
		User a1 = new User("Diego Ariza", "diego.ariza2@gmail.com", LocalDate.of(2022, 9, 28));
		User b2 = new User("Ryz3n Ariza", "ryz3n@gmail.com", LocalDate.of(2022, 1, 2));
		User c3 = new User("Gw3n Ariza", "Gw3n@gmail.com", LocalDate.of(2022, 2, 1));
		User user3 = new User("Daniela", "daniela@gmail.com", LocalDate.of(2022, 1, 8));
		User user4 = new User("Marisol", "marisol@gmail.com", LocalDate.of(2022, 2, 18));
		User user5 = new User("Karen", "karen@gmail.com", LocalDate.of(2022, 3, 1));
		User user6 = new User("Carlos", "carlos@gmail.com", LocalDate.of(2022, 4, 7));
		User user7 = new User("Enrique", "enrique@gmail.com", LocalDate.of(2022, 5, 12));
		User user8 = new User("Luis", "luis@gmail.com", LocalDate.of(2022, 6, 27));
		User user9 = new User("Karen", "KarenPaola@gmail.com",LocalDate.of(2022, 7, 31));

		List<User> list = Arrays.asList(a1,b2, c3,user3,user4,user5,user6,user7,user8,user9);
		//Implementación del repositorio para persistir.
		list.stream().forEach(repository::save); //Se almacenaran todos los registros.
	}


	private void getInformationJPQLFromUser(){
		LOGGER.info("---> Begin getInformationJPQLFromUser");
		LOGGER.info("---> getInformationJPQLFromUser Usuario Existente");
		LOGGER.info("Select USER --> "+repository.findByUserEmail("ryz3n@gmail.com").orElseThrow(()-> new RuntimeException("Error : Usuario no encontrado")));
		LOGGER.info("---> getInformationJPQLFromUser El Usuario NO Existente");
		LOGGER.info("Select USER --> "+repository.findByUserEmail("Gw3n@gmail.com").orElseThrow(()-> new RuntimeException(" -->> Error : Usuario no encontrado")));
		LOGGER.info("---> getInformationJPQLFromUser ORDENAMIENTO");

		LOGGER.info("Select USER --> ");

		repository.findAndSort("Gw3", Sort.by("id").descending()).stream().forEach(user -> LOGGER.info("----> USUARIO : " + user));


		//Implementación QueryMethods
		repository.findByname("Karen").stream().forEach(user -> LOGGER.info(" ---> QUERY METHODS findByname : "+ user));


		LOGGER.info("QUERY METHODS - findByEmailAndName : "+ repository.findByEmailAndName("marisol@gmail.com", "Marisol").orElseThrow(()-> new RuntimeException(" -->> Error findByEmailAndName : Usuario no encontrado")));

		repository.findByNameLike("%Ari%").stream().forEach(user -> LOGGER.info(" ----> USUARIO : findByNameLike : Usuario no encontrado" + user));


		repository.findByNameOrEmail("Carlos", null).stream().forEach(user -> LOGGER.info(" ----> USUARIO : findByNameOREmail : " + user));

		repository.findByNameOrEmail(null, "enrique@gmail.com").stream().forEach(user -> LOGGER.info(" ----> USUARIO : findByNameOREmail : " + user));


		repository.findByBirthDateBetween(LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 10) ).stream().forEach(user -> LOGGER.info(" ----> USUARIO : findByBirthDateBetWeen : " + user));


		repository.findByNameLikeOrderByIdDesc("%Ariza%").stream().forEach(user -> LOGGER.info(" ----> USUARIO : findByNAmeLikeOrderByIdDesc : " + user));


		LOGGER.info("El usuario  getAllByBirthDateAndEmail : " + repository.getAllByBirthDateAndEmail(LocalDate.of(2022, 1, 8), "daniela@gmail.com").orElseThrow(()-> new RuntimeException(" -->> Error getAllByBirthDateAndEmail : Usuario no encontrado")));



		LOGGER.info("---> END getInformationJPQLFromUser");
	}


	private	void saveWhitErrorTransactional(){
		User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
		User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
		User test3 = new User("TestTransactional3", "TestTransactional1@domain.com", LocalDate.now()); //Se agrega el mismo email del user 1, para provocar un error de registro unico.
		User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1,test2, test3, test4);
		try {
			//Registrar a los usuarios con el metodo transactional
			userService.saveTransactional(users);

		}catch (Exception e){
			LOGGER.error("\n --> ERROR saveWhitErrorTransactional - Mensaje : " + e.getMessage());
		}


		userService.getAllUsers().stream().forEach(user -> LOGGER.info("\n --> saveWhitErrorTransactional - Usuario en el metodo transacciona : " + user)); //Recuperar todos los usuarios.

	}
}
