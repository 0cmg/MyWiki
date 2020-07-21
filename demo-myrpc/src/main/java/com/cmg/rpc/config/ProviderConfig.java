package com.cmg.rpc.config;

/**
 * @Name MyWiKi com.cmg
 * @Author cmg
 * @Date 2020/7/21 16:43
 * @Desciption TODO
 **/
public class ProviderConfig {
    //接口
    private String nozzle;
    //映射
    private String ref;
    //别名
    private String alias;

    /**
     * @Name provider.
     * @Author cmg
     * @Params
     * @Return
     * @Date 2020/7/21 16:46
     * @Version 1.0.0
     * @Description  发布
     **/
    protected void doExport(){
        System.out.format("生产者信息=> [接口:%s] [映射:%s] [别名:%s]\r\n", nozzle, ref, alias);
    }

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
