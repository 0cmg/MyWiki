package DTO;

public class SingleCondition {
    /**
     * 流水号
     */
    public String TransNo;

    /**
     * 指令类型
     */
    public String CommandCode;

    public SingleCondition(String transNo, String commandCode){
        TransNo = transNo;
        CommandCode = commandCode;
    }
}
