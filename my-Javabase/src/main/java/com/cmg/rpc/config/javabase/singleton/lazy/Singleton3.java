package com.cmg.rpc.config.javabase.singleton.lazy;
import java.io.IOException;
import	java.util.Properties;

/**
 * @Name MyWiKi com.cmg.javabase
 * @Author cmg
 * @date 2020/6/30 21:14
 * @Description TODO
 **/

/**
 * 适合于复杂实例化
 */
public class Singleton3 {
    public static final Singleton3 INSTANCE;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    static {
        try {
            Properties props = new Properties();
            props.load(Singleton3.class.getResourceAsStream("/single.properties"));
            INSTANCE = new Singleton3(props.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Singleton3(String info){
        this.info = info;
    }

    @Override
    public String toString() {
        return "Singleton3{" +
                "info='" + info + '\'' +
                '}';
    }
}
