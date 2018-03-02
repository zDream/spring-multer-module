package com.drayy.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Cold
@Fruity
public class UpanDisc implements CompactDisc {
    @Override
    public void play() {
        System.out.println("this is Upan Disc");
    }
}
