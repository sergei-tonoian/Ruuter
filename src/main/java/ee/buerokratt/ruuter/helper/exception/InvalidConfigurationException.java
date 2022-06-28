package ee.buerokratt.ruuter.helper.exception;

import static java.lang.String.*;

public class InvalidConfigurationException extends IllegalArgumentException {
    public InvalidConfigurationException(String configuration, Throwable err) {
        super(format("Encountered error, when loading configuration: %s", configuration), err);
    }
}