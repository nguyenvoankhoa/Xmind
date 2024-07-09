package content;

import dependency.ISheetManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class XMind {
    ISheetManager sheetManager;

    public XMind(ISheetManager sheetManager) {
        this.sheetManager = sheetManager;
    }


}
