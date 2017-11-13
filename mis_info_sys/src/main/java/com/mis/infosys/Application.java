package com.mis.infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;




@ComponentScan(basePackages={"com.mis.infosys.persist.repositories.impl.rbac",
							 "com.mis.infosys.persist.repositories.impl.manufactures",
							 "com.mis.infosys.persist.repositories"})
@EntityScan(basePackages={"com.mis.infosys.persist.entities.rbac",
						  "com.mis.infosys.persist.entities.manufactures",
						  "com.mis.infosys.persist.entities.records"})
@EnableAutoConfiguration
@SpringBootApplication
public class Application {
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
	/**
     * 注入sessionfatory
     * @return
     */
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
    
//    @Bean 
//    public AuthorityRepositoryImpl getAuthorityRepositoryImplBean()
//    {
//    	return new AuthorityRepositoryImpl();
//    }

}
