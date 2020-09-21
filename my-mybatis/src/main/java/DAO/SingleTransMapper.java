package DAO;

import DTO.SingleCondition;
import DTO.SingletransDTO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface SingleTransMapper {
    SingletransDTO selectSingle(String transNo);
    SingletransDTO selectSingleByClass(SingleCondition singleCondition);
    SingletransDTO selectSingleByMultCondition(HashMap<String,Object> param);
    SingletransDTO selectSingleByParam(@Param("param1")String transNo, @Param("param2") String commandCode);
}
