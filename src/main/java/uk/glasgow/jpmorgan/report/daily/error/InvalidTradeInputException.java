package uk.glasgow.jpmorgan.report.daily.error;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */

public class InvalidTradeInputException extends Exception {

    private static final long serialVersionUID = 5883305392441308289L;

    public InvalidTradeInputException() {
        super();
    }

    public InvalidTradeInputException(String errorMsg) {
        super(errorMsg);
    }

    public InvalidTradeInputException(String errorMsg, Exception ex) {
        super(errorMsg, ex);
    }

}
