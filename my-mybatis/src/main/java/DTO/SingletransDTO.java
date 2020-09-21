package DTO;

public class SingletransDTO {
    public String EnterpriseNum;

    public String TransNo;

    public String CommandCode;

    public int State;

    public String getEnterpriseNum() {
        return EnterpriseNum;
    }

    public void setEnterpriseNum(String enterpriseNum) {
        EnterpriseNum = enterpriseNum;
    }

    public String getTransNo() {
        return TransNo;
    }

    public void setTransNo(String transNo) {
        TransNo = transNo;
    }

    public String getCommandCode() {
        return CommandCode;
    }

    public void setCommandCode(String commandCode) {
        CommandCode = commandCode;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "SingletransDTO{" +
                "EnterpriseNum='" + EnterpriseNum + '\'' +
                ", TransNo='" + TransNo + '\'' +
                ", CommandCode='" + CommandCode + '\'' +
                ", State=" + State +
                '}';
    }
}
