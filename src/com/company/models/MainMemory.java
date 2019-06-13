package com.company.models;

import java.util.HashMap;
import java.util.Map;

public class MainMemory {
    final static int mainMemBits = 16;

    private Map<String, String> memoryMap = new HashMap<>();
    private String address;
    private boolean memReadSignal;
    private boolean memWriteSignal;

    public MainMemory() {
        int memSize = (int) Math.pow(2, mainMemBits);
        for (int i = 0; i < memSize; i++) {
            String key = String.format("%" + 16 + "s", Integer.toBinaryString(i)).replace(' ', '0');
            memoryMap.put(key, "00000000000000000000000000000000");
        }
    }

    public void setup(String address, boolean memReadSignal, boolean memWriteSignal) {
        this.address = address;
        this.memReadSignal = memReadSignal;
        this.memWriteSignal = memWriteSignal;
    }

    public void writeData(String data) {
        if (memWriteSignal && data.length() == 32)
            memoryMap.replace(address, data);
    }

    public String readData() {
        if (memReadSignal)
            return memoryMap.get(address);
        else return "";
    }
}
