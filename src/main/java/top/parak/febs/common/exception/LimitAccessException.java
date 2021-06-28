package top.parak.febs.common.exception;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public class LimitAccessException extends Exception {

    private static final long serialVersionUID = -3608667856397125671L;

    public LimitAccessException(String message) {
        super(message);
    }
}
