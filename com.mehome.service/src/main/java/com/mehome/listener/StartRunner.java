package com.mehome.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Created by renhui on 2017/3/2.
 */
@Component
@Order(value=1)
public class StartRunner implements CommandLineRunner {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {
        log.info("11111111111111");

    }
}
