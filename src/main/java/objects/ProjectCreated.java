package objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreated {
    boolean status;
    ProjectResult result;
}