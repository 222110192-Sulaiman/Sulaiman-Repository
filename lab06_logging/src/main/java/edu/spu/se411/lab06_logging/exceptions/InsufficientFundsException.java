package edu.spu.se411.lab06_logging.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsufficientFundsException extends Exception {

    static Logger logger =
            LoggerFactory.getLogger(InsufficientFundsException.class);

    public InsufficientFundsException(String message) {
        super(message);

        logger.warn("InsufficientFundsException object created");
    }
}