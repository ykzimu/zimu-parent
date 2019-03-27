package com.zimu.design.prototype;

import lombok.Data;

import java.io.*;

@Data
public class Prototype implements Serializable, Cloneable {

    private String name;

    private PrototypePhone prototypePhone;

    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return prototype;
    }

    public Prototype deepClone() {

        Prototype prototype = null;

        try {
            //将对象写到流里
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //从流里读回来
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            prototype = (Prototype) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prototype;
    }
}
