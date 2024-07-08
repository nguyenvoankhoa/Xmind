package sheet;

import dependency.ISheetSerialize;
import file.IOMessage;
import file.IOStatus;
public class SheetWordSerializer implements ISheetSerialize {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to Word success");
    }
}
