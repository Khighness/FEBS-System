package top.parak.febs.common.exception;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public class RedisConnectException extends Exception {

    private static final long serialVersionUID = 1639374111871115063L;

    public RedisConnectException(String message) {
        super(message);
    }
}
