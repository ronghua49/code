package com.haohua.exception;    /*
 * @author  Administrator
 * @date 2019/1/12
 */

public class LackOfGoodsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LackOfGoodsException() {
        super();
    }

    public LackOfGoodsException(String message) {
        super(message);
    }

    public LackOfGoodsException(Throwable cause) {
        super(cause);
    }
}
