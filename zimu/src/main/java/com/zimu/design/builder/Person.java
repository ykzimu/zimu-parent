package com.zimu.design.builder;

public class Person {

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 体重
     */
    private Float weight;

    /**
     * 身高
     */
    private Float height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public Person() {
    }

    public Person(PersonBuilder builder) {
        this.name = builder.getName();
        this.age = builder.getAge();
        this.sex = builder.getSex();
        this.weight = builder.getWeight();
        this.height = builder.getHeight();
    }


    public Person(String name, Integer age, Integer sex, Float weight, Float height) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    public static class PersonBuilder {

        /**
         * 名称
         */
        private String name;

        /**
         * 年龄
         */
        private Integer age;

        /**
         * 性别
         */
        private Integer sex;

        /**
         * 体重
         */
        private Float weight;

        /**
         * 身高
         */
        private Float height;

        public String getName() {
            return name;
        }

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public PersonBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Integer getSex() {
            return sex;
        }

        public PersonBuilder setSex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Float getWeight() {
            return weight;
        }

        public PersonBuilder setWeight(Float weight) {
            this.weight = weight;
            return this;
        }

        public Float getHeight() {
            return height;
        }

        public PersonBuilder setHeight(Float height) {
            this.height = height;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

}
