package com.cmg.springboot.entity;

/**
 * @Name MyWiki com.cmg.springboot.entity
 * @Author cmg
 * @date 2020/8/9 20:25
 * @Description TODO
 **/
public class User {
    private Integer id;
    private String name;
    private String sex;
    private String addr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public User() {
        super();
    }

    public User(Integer id, String name, String sex, String addr) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
