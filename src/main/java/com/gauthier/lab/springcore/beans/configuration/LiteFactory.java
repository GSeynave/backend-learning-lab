package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LiteFactory {
    public class LiteRepository{}
    public class LiteService{
        private  final  LiteRepository repository;

        public LiteService(LiteRepository repository) {
            this.repository = repository;
        }
        public LiteRepository repository(){
            return repository;
        }
    }

    @Bean
    public LiteRepository liteRepository(){
        System.out.println("LiteRepository FACTORY");
        return new LiteRepository();
    }
    @Bean
    public LiteService liteService(){
        return new LiteService(liteRepository());
    }
}
