package xmindtest;

import setting.PropertiesLoader;
import sheet.*;
import dependency.ISheetSerialize;
import dependency.IRelationshipManager;
import content.Leaf;
import content.Root;
import file.IOMessage;
import file.IOStatus;
import relationship.Relationship;
import relationship.RelationshipManager;
import setting.Structure;
import setting.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMindApplicationTests {
    Sheet sheet;
    Root root;

    @BeforeEach
    public void setUp() throws IOException {
        ISheetSerialize sheetSerialize = new SheetPDFSerializer();
        IRelationshipManager relationshipManager = new RelationshipManager();
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        sheet = new Sheet(relationshipManager, sheetSerialize, propertiesLoader);
        root = sheet.getRoot();
    }

    @Test
    void testDefaultInit() {
        assertEquals(4, sheet.getRoot().getChildren().stream().count());
    }

    @Test
    void testCreateSheet() throws IOException {
        Sheet newSheet = sheet.createSheet();
        assertNotEquals(null, newSheet);
    }

    @Test
    void testDuplicateSheet() throws IOException {
        Sheet duplicateSheet = sheet.duplicateSheet(sheet);
        assertNotEquals(null, duplicateSheet);
    }

    @Test
    void testRenameSheet() {
        String newSheetName = "Test";
        sheet.setTitle(newSheetName);
        assertEquals(sheet.getTitle(), newSheetName);
    }

    @Test
    void testExportPDF() {
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.pdf");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportPNG() {
        ISheetSerialize sheetSerialize = new SheetPNGSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.png");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportWord() {
        ISheetSerialize sheetSerialize = new SheetWordSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.docx");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportXmind() {
        ISheetSerialize sheetSerialize = new SheetXmindSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.xmind");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testOpenXmind() {
        SheetXmindSerializer sheetSerialize = new SheetXmindSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = ((SheetXmindSerializer) sheet.getISheetSerialize()).openMindMap("test.xmind");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testAddChildren() throws IOException {
        Leaf leaf = new Leaf("abc", "New Topic", root);
        long beforeAddSize = root.getChildren().stream().count();
        root.addChild(leaf);
        long afterAddSize = root.getChildren().stream().count();
        assertEquals(beforeAddSize + 1, afterAddSize);
    }

    @Test
    void testRemoveChildren() throws IOException {
        Leaf leaf = new Leaf("abc", "Leaf 1", root);
        root.addChild(leaf);
        long beforeRemoveSize = root.getChildren().stream().count();
        root.removeChild(leaf.getId());
        long afterRemoveSize = root.getChildren().stream().count();
        assertEquals(beforeRemoveSize - 1, afterRemoveSize);
    }

    @Test
    void testCollapse() {
        root.collapse();
        assertFalse(root.isOpen());
    }

    @Test
    void testExpand() {
        root.expand();
        assertTrue(root.isOpen());
    }

    @Test
    void testLeafMove() throws IOException {
        Leaf leaf = new Leaf("abc", "Leaf 1", root);
        Leaf leaf1 = new Leaf("def", "Leaf 2", root);
        root.addChild(leaf);
        root.addChild(leaf1);
        leaf.changeParent("def", sheet.getRoot());
        assertEquals(leaf.getParent().getId(), leaf1.getId());
    }


    @Test
    void testFloatContentBecomeLeaf() throws IOException {
        Leaf leaf = new Leaf("leaf", "New Topic");
        leaf.setFloating(true);
        leaf.changeParent("root", sheet.getRoot());
        assertEquals(leaf.getParent().getId(), root.getId());
    }



    @Test
    void testEditContent() {
        root.setContent("New Content");
        assertEquals("New Content", root.getContent());
    }

    @Test
    void testRemoveAll() {
        root.removeAll();
        assertNull(root.getChildren());
    }


    @Test
    void testAdjustZoomLevel() {
        sheet.setZoomLevel(100);
        assertEquals(100, sheet.getZoomLevel());
    }

    @Test
    void testAdjustViewport() {
        sheet.setViewType(ViewType.THREE_BY_FOUR);
        assertEquals(ViewType.THREE_BY_FOUR, sheet.getViewType());
    }


    @Test
    void testRemoveRelationship() throws IOException {
        Leaf src = new Leaf("abc", "Node 1");
        Leaf target = new Leaf("def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        sheet.getIRelationshipManager().addRelationship(src, target);
        Relationship relationship = sheet.getIRelationshipManager().getRelationships().get(0);
        int relaBefore = sheet.getIRelationshipManager().getRelationships().size();
        sheet.getIRelationshipManager().removeRelationship(relationship);
        int relaAfter = sheet.getIRelationshipManager().getRelationships().size();
        assertEquals(relaBefore - 1, relaAfter);
    }

    @Test
    void testAddRelationship() throws IOException {
        Leaf src = new Leaf("abc", "Node 1");
        Leaf target = new Leaf("def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        sheet.getIRelationshipManager().addRelationship(src, target);
        assertEquals(1, sheet.getIRelationshipManager().getRelationships().stream().count());
    }

    @Test
    void testChangeTargetNodeRelationship() throws IOException {
        Leaf src = new Leaf("abc", "Node 1");
        Leaf target = new Leaf("def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        sheet.getIRelationshipManager().addRelationship(src, target);
        sheet.getIRelationshipManager().getRelationships().get(0).changeTargetRelationship(root);
        assertEquals(sheet.getIRelationshipManager().getRelationships().get(0).getTargetNode(), root);
    }

    @Test
    void testStructure() {
        root.setStructure(Structure.FISH_BONE);
        assertEquals(Structure.FISH_BONE, root.getStructure());
    }


}
