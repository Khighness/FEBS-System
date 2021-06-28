package top.parak.febs.common.exception;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public class FebsException extends RuntimeException  {

    private static final long serialVersionUID = -994962710559017255L;

    public FebsException(String message) {
        super(message);
    }
}
