package com.javarush.task.task37.task3711;

import java.lang.reflect.Member;

public class Computer {
    CPU cpu = new CPU();
    Memory memory = new Memory();
    HardDrive hardDrive = new HardDrive();
    void run(){
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
