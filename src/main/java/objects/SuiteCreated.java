package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteCreated {
    boolean status;
    SuiteResult result;
}