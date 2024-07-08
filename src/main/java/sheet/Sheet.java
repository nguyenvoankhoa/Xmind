package sheet;

import builder.RootBuilder;
import content.Root;
import dependency.ISheetSerialize;
import dependency.IRelationshipManager;
import setting.PropertiesLoader;
import setting.ViewType;
import lombok.Getter;
import lombok.Setter;


import java.io.IOException;

@Getter
@Setter
public class Sheet {

    private String theme;
    private String background;
    private String globalFont;
    private int zoomLevel;
    private String title;
    private ViewType viewType;
    private Root root;
    private IRelationshipManager iRelationshipManager;
    private ISheetSerialize iSheetSerialize;
    private PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();

    public Sheet() throws IOException {
        this.root = new RootBuilder()
                .addId(propertiesLoader.getProperty("root.id"))
                .addContent(propertiesLoader.getProperty("root.content"))
                .addColor(propertiesLoader.getProperty("root.color"))
                .addChildren("Main Topic 1", "Main Topic 2", "Main Topic 3", "Main Topic 4")
                .build();
        this.title = propertiesLoader.getProperty("board.title");
        this.theme = propertiesLoader.getProperty("board.theme");
        this.globalFont = propertiesLoader.getProperty("board.globalFont");
        this.background = propertiesLoader.getProperty("board.background");
        this.zoomLevel = Integer.parseInt(propertiesLoader.getProperty("board.zoomLevel", "0"));
        this.viewType = ViewType.THREE_BY_FOUR;
    }

    public Sheet(IRelationshipManager iRelationshipManager, ISheetSerialize iSheetSerialize) throws IOException {
        this();
        this.iRelationshipManager = iRelationshipManager;
        this.iSheetSerialize = iSheetSerialize;
    }

    @Override
    public String toString() {
        return "Board{" +
                "theme='" + theme + '\'' +
                ", background='" + background + '\'' +
                ", globalFont='" + globalFont + '\'' +
                ", zoomLevel=" + zoomLevel +
                ", title='" + title + '\'' +
                ", viewType=" + viewType +
                ", root=" + root +
                ", iRelationshipManager=" + iRelationshipManager +
                ", iBoardSerialize=" + iSheetSerialize +
                '}';
    }

    public Sheet createSheet() throws IOException {
        return new Sheet();
    }

    public Sheet duplicateSheet(Sheet template) throws IOException {
        Sheet newSheet = new Sheet(template.iRelationshipManager, template.iSheetSerialize);
        newSheet.setTheme(template.getTheme());
        newSheet.setBackground(template.getBackground());
        newSheet.setGlobalFont(template.getGlobalFont());
        newSheet.setZoomLevel(template.getZoomLevel());
        newSheet.setTitle(template.getTitle());
        newSheet.setViewType(template.getViewType());
        newSheet.setRoot(template.getRoot());
        return newSheet;
    }
}
