package com.gmail.tvmj.marcosvilchez.springbootShop.controller;

import com.gmail.tvmj.marcosvilchez.springbootShop.config.SecurityBeansInjector;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Cliente;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Pedido;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.Producto;
import com.gmail.tvmj.marcosvilchez.springbootShop.model.User;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.ClienteService;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.PedidoService;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.ProductoService;
import com.gmail.tvmj.marcosvilchez.springbootShop.service.UserService;
import com.gmail.tvmj.marcosvilchez.springbootShop.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase(ClienteService repoCli, ProductoService repoPro, PedidoService repoPed,
                                   UserService uService){
        return args ->{
/*
            Cliente cliente1= new Cliente("marcos","vilchez", "argentina", "Marcosvilchez.tvmj@gmail.com", "2004");
            Cliente cliente2= new Cliente("Alex","Johnson", "Estados Unidos", "alex.johnson@email.com", "Pass123");
            Cliente cliente3= new Cliente("Maria","Rodriguez", "México", "maria.rodriguez@email.com", "SecurePass456");
            Cliente cliente4= new Cliente("Oliver","Müller", "argentina", "oliver.mueller@email.com", "Secret@789");
            Cliente cliente5= new Cliente("Sakura","Tanaka", "Japón", "sakura.tanaka@email.com", "CherryBlossom123");
            Cliente cliente6= new Cliente("Andrei","Popescu", "Rumania", "andrei.popescu@email.com", "Rainbow2022");
            Cliente cliente7= new Cliente("Isabella","Santos", "Brasil", "isabella.santos@email.com", "Sunflower");
            repoCli.saveClient(cliente1);
            repoCli.saveClient(cliente2);
            repoCli.saveClient(cliente3);
            repoCli.saveClient(cliente4);
            repoCli.saveClient(cliente5);
            repoCli.saveClient(cliente6);
            repoCli.saveClient(cliente7);
            repoCli.findAll().forEach(cliente -> log.info("Cargando clientes... " + cliente));

            Producto producto1 = new Producto("arroz",1000,"grano de arroz");
            Producto producto2 = new Producto("pollo",5000,"carne de pollo");
            Producto producto3 = new Producto("carne vacuna",4000,"molida especial");
            Producto producto4 = new Producto("lenteja",3000,"grano de lenteja");
            Producto producto5 = new Producto("galletas",1000,"galletas carita feliz");
            Producto producto6 = new Producto("gelatina",2000,"gelatina marca royal");
            Producto producto7 = new Producto("doritos",3000,"doritos de 300g");
            Producto producto8 = new Producto("gatarina",6000,"comida de gato");
            Producto producto9 = new Producto("shampoo",3500,"shampoo marca pantene");
            repoPro.saveProduct(producto1);
            repoPro.saveProduct(producto2);
            repoPro.saveProduct(producto3);
            repoPro.saveProduct(producto4);
            repoPro.saveProduct(producto5);
            repoPro.saveProduct(producto6);
            repoPro.saveProduct(producto7);
            repoPro.saveProduct(producto8);
            repoPro.saveProduct(producto9);
            repoPro.findAll().forEach(productos -> log.info("Cargando productos... " + productos));

            Pedido pedido1 = new Pedido(cliente1);
            Pedido pedido2 = new Pedido(cliente2);
            Pedido pedido3 = new Pedido(cliente3);
            Pedido pedido4 = new Pedido(cliente4);
            Pedido pedido5 = new Pedido(cliente5);

            repoPed.saveOrder(pedido1);
            repoPed.saveOrder(pedido2);
            repoPed.saveOrder(pedido3);
            repoPed.saveOrder(pedido4);
            repoPed.saveOrder(pedido5);
            repoPed.saveOrder(pedido1);

            uService.saveUser(new User("Marcos", "Vilchez", "Marcos@Gmail.com",
                    encoder.encode("2004").toString(), Role.ADMIN));
*/
        };
    }
}
