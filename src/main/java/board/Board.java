package board;

import builder.RootBuilder;
import content.Root;
import dependency.IBoardSerialize;
import dependency.IRelationshipManager;
import setting.ViewType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class Board {

    private String theme;
    private String background;
    private String globalFont;
    private int zoomLevel;
    private String title;
    private ViewType viewType;
    private Root root;
    private IRelationshipManager iRelationshipManager;
    private IBoardSerialize iBoardSerialize;

    public Board() {
        this.root = new RootBuilder()
                .addId("Root")
                .addContent("Central Topic")
                .addColor("Black")
                .addChildren("Main Topic 1", "Main Topic 2", "Main Topic 3", "Main Topic 4")
                .build();
        this.title = "Hello";
        this.theme = "New Theme";
        this.globalFont = "Arial";
        this.background = "White";
        this.zoomLevel = 90;
        this.viewType = ViewType.THREE_BY_FOUR;
    }
    public Board(@Value("${board.theme}") String theme,
                 @Value("${board.background}") String background,
                 @Value("${board.globalFont}") String globalFont,
                 @Value("${board.zoomLevel}") int zoomLevel,
                 @Value("${board.title}") String title,
                 @Value("${board.viewType}") ViewType viewType) {
        this.theme = theme;
        this.background = background;
        this.globalFont = globalFont;
        this.zoomLevel = zoomLevel;
        this.title = title;
        this.viewType = viewType;
        this.root = new RootBuilder()
                .addId("Root")
                .addContent("Central Topic")
                .addColor("Black")
                .addChildren("Main Topic 1", "Main Topic 2", "Main Topic 3", "Main Topic 4")
                .build();
    }
    public Board(IRelationshipManager iRelationshipManager, IBoardSerialize iBoardSerialize) {
        this();
        this.iRelationshipManager = iRelationshipManager;
        this.iBoardSerialize = iBoardSerialize;
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
                ", iBoardSerialize=" + iBoardSerialize +
                '}';
    }


    public Board createBoard(){
        return new Board();
    }

    public Board duplicateBoard(Board template) {
        Board newBoard = new Board(template.iRelationshipManager, template.iBoardSerialize);
        newBoard.setTheme(template.getTheme());
        newBoard.setBackground(template.getBackground());
        newBoard.setGlobalFont(template.getGlobalFont());
        newBoard.setZoomLevel(template.getZoomLevel());
        newBoard.setTitle(template.getTitle());
        newBoard.setViewType(template.getViewType());
        newBoard.setRoot(template.getRoot());
        return newBoard;
    }
}
