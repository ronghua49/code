package com.haohua.exception;    /*
 * @author  Administrator
 * @date 2019/2/21
 */

public class BusyException extends Throwable {

    private static final long serialVersionUID = 1L;

    public BusyException() {
        super();
    }

    public BusyException(String message) {
        super(message);
    }

    public BusyException(Throwable cause) {
        super(cause);
    }
}
