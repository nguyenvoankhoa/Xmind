package sheet;

import dependency.ISheetSerialize;
import file.IOMessage;
import file.IOStatus;

public class SheetXmindSerializer implements ISheetSerialize {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to Xmind success");
    }

    public IOMessage openMindMap(String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Open success " + filepath);
    }
}
