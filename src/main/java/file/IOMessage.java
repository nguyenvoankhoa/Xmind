package file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IOMessage {
    private IOStatus exportStatus;
    private String message;
}
