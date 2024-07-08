package sheet;

import dependency.ISheetSerialize;
import file.IOMessage;
import file.IOStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SheetPDFSerializer implements ISheetSerialize {
    private static final Log LOGGER = LogFactory.getLog(SheetPDFSerializer.class);

    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to PDF success");
    }
}
