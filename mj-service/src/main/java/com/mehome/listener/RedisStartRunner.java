package com.mehome.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renhui on 2017/3/2.
 */
@Component
@Order(value=1)
public class RedisStartRunner implements CommandLineRunner {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(String... args) throws Exception {


    }
}
