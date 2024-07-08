package sheet;

import dependency.ISheetSerialize;
import file.IOMessage;
import file.IOStatus;

public class SheetPNGSerializer implements ISheetSerialize {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to PNG success");
    }
}
