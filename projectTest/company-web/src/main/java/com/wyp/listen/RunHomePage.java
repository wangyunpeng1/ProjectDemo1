package com.wyp.listen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author: wyp
 * @date: 2020/1/14
 * @description: 启动项目打开的页面
 */
@Component
public class RunHomePage implements CommandLineRunner {

    public void run(String... args) throws Exception {
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8080/login");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
