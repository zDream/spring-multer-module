package com.drayy.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Creamy
@Cold
public class SgtPeppers implements CompactDisc {


    @Override
    public void play() {

        System.out.println("this is sgtPerppers");
    }
}
