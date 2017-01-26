/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialCardTable;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialIconButton;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialEditableLayout;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Édgar
 */
public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MaterialDesign.initSystemProperties();
       
        MaterialDesign.setCustomPath("/main/images");
         StackPane root=new StackPane();
        Scene scene=MaterialDesign.getMaterialSizableScene(primaryStage, root, 800, 480);
        MaterialDesign.setTooltipsOn(true);
        MaterialDesign.setExitOnClose(true);
        MaterialEditableLayout layout=new MaterialEditableLayout("AGFD",true){
            @Override
            public void onMenuButtonPressed(Button button) {
               
            
            }
        };
        MaterialIconButton reportButton=new MaterialIconButton();
        reportButton.setIcon("/ic_bug_report.png");
        MaterialTooltip tooltip=new MaterialTooltip(reportButton);
        tooltip.setText("Report problems");
        layout.addToolbarActionButton(reportButton);
        
        HBox selectorBox=new HBox(getTypeBox(),getFirmwareBox(),getRegionBox());
        selectorBox.setPadding(new Insets(8));
        layout.addDrawerNode(selectorBox);
        layout.setMiniDrawerSize(0);
        
        MaterialDisplayText infoText=new MaterialDisplayText("This program will download "
        		+ "files associated with particular pages "
        		+ "of the A9LH guide. Select a page using "
        		+ "the table, and click Download. For"
        		+ " quickest use, run from the root of your "
        		+ "SD card, or otherwise move the files onto "
        		+ "your SD card afterward. Please note any messages "
        		+ "that come up in the download status window.");
        infoText.setPadding(new Insets(14));
        infoText.setWrapText(true);
        infoText.setColorCode(MaterialColor.material.INDIGO);
        infoText.setFontSize(15);
        layout.addDrawerNode(infoText);
        
       
        layout.setRootView(new PageList());
        layout.fullSize();
        layout.setWindowTitle("A9LH Guide File Downloader by Quantumcat");
        layout.getToolbar().getStatusBar().hideMinimizeButtons();
        root.getChildren().add(layout);
        primaryStage.show();
         //To change body of generated methods, choose Tools | Templates.
    }
    private VBox getBox(String labelString, MaterialSelector selector){
        VBox box=new VBox();
        box.setPadding(new Insets(6));
        MaterialLabel label=new MaterialLabel(labelString);
        selector.setLabel(label);
        box.getChildren().addAll(label,selector);
        return box;
    }
    private VBox getFirmwareBox(){
        return getBox("Firmware",getFirmwareSelector());
    }
    private VBox getRegionBox(){
        
        return getBox("Region",getRegionSelector());
    }
    private VBox getTypeBox(){
        
        return getBox("Type",getTypeSelector());
    }
    public enum Region
    {
    	ALL ("All"),
    	E ("Eur"),
    	J ("JPN"),
    	U ("USA"),
    	K ("Kor"),
    	T ("TWN");

    	private final String desc;
    	Region(String desc)
    	{
    		this.desc = desc;
    	}
    	@Override
    	public String toString()
    	{
    		return desc;
    	}
    }

    public enum Firmware
    {
    	ALL ("All"),
    	L_f110 ("< 11.0"),
    	f110 ("11.0"),
    	f111 ("11.1"),
    	f112 ("11.2");

    	private final String desc;
    	Firmware(String desc)
    	{
    		this.desc = desc;
    	}
    	@Override
    	public String toString()
    	{
    		return desc;
    	}
    }

    public enum Type
    {
    	ALL ("All"),
    	O ("Old"),
    	N ("New");

    	private final String desc;
    	Type(String desc)
    	{
    		this.desc = desc;
    	}
    	@Override
    	public String toString()
    	{
    		return desc;
    	}
    }
    private MaterialSelector<Type> typeSelector;
    public MaterialSelector<Type> getTypeSelector(){
        if(typeSelector==null){
            typeSelector=new MaterialSelector<Type>(){

                @Override
                public String getDataString(Type valor) {
                        return valor.toString();
                }
                
            };
            for(Type r: Type.values()){
            typeSelector.addItem(r);
                
            }
            
            typeSelector.getSelectionModel().select(0);
            typeSelector.setPrefWidth(500);
        }
        return typeSelector;
    }
    
     private MaterialSelector<Region> regionSelector;
    public MaterialSelector<Region> getRegionSelector(){
        if(regionSelector==null){
            regionSelector=new MaterialSelector<Region>(){

                @Override
                public String getDataString(Region valor) {
                        return valor.toString();
                }
                
            };
            for(Region r: Region.values()){
            regionSelector.addItem(r);
                
            }
            regionSelector.getSelectionModel().select(0);
            regionSelector.setPrefWidth(500);
        }
        return regionSelector;
    }
    
    private MaterialSelector<Firmware> firmwareSelector;
    public MaterialSelector<Firmware> getFirmwareSelector(){
        if(firmwareSelector==null){
            firmwareSelector=new MaterialSelector<Firmware>(){

                @Override
                public String getDataString(Firmware valor) {
                        return valor.toString();
                }
                
            };
            for(Firmware r: Firmware.values()){
            firmwareSelector.addItem(r);
                
            }
            firmwareSelector.getSelectionModel().select(0);
            firmwareSelector.setPrefWidth(500);
        }
        return firmwareSelector;
    }
      public static void main(String[] args) {
        
 launch(args);       
    
    
    }
    
}
