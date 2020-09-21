package DAO;

import DTO.SingletransDTO;

public interface SingleTransMapper {
    SingletransDTO selectSingle(String transNo);
}
