package sheet;

import dependency.ISheetFile;
import file.IOMessage;
import file.IOStatus;

public class SheetXMindFile implements ISheetFile {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to XMind success");
    }

    public IOMessage openMindMap(String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Open success " + filepath);
    }
}
