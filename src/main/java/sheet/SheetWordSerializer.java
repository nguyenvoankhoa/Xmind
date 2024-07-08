package sheet;

import dependency.ISheetSerialize;
import file.IOMessage;
import file.IOStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SheetWordSerializer implements ISheetSerialize {
    private static final Log LOGGER = LogFactory.getLog(SheetPNGSerializer.class);

    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to Word success");
    }
}
