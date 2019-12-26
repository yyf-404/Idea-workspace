package com.yyf.springbootfast;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**  * 将配置文件中配置的每一个属性的值，映射到这个组件中  
        @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
        prefix = "person"：配置文件中哪个下面的所有属性进行一一映射  
       只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；  *  */
@Component
@ConfigurationProperties(prefix = "user")
@Validated
public class User {

   public String lastName ;
   public String[] cars;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getCars() {
        return cars;
    }



    public void setCars(String[] cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", cars=" + Arrays.toString(cars) +
                '}';
    }
}
