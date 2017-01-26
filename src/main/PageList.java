/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.olmectron.material.components.MaterialCardTable;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialFloatingButton;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.components.MaterialTransparentPane;
import com.olmectron.material.constants.MaterialColor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Édgar
 */
public class PageList extends MaterialTransparentPane {
    private MainWindow manager;
    public PageList() throws ParseException, ParserConfigurationException, SAXException, IOException{
        super();
        manager=new MainWindow();
        getContentCard().setCardPadding(new Insets(4));
        MaterialCardTable<Page> pageTable=new MaterialCardTable<Page>() {
            @Override
            public Property[] getObservedProperties(Page t) {
                return null;
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Node getLayout() {
                MaterialDisplayText nameText=new MaterialDisplayText("");
                nameText.setId("name_text");
                nameText.setFontSize(18);
                nameText.setColorCode(MaterialColor.material.BLACK_87);
                
                return new VBox(nameText);
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void fillGraphic(Node graphic, Page value) {
                ((MaterialDisplayText)graphic.lookup("#name_text")).setText(value.getTitle());
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onRowAdded(ListChangeListener.Change c, Object addedObject) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onRowRemoved(ListChangeListener.Change c, Object removedObject) {
                 //To change body of generated methods, choose Tools | Templates.
            }
        };
        pageTable.addItems(Page.getPages());
        pageTable.setTitle("Sections");
        pageTable.getTable().setPrefHeight(2000);
        pageTable.getTable().setPrefWidth(2000);
        //BorderPane pane=new BorderPane();
        //pane.setCenter(pageTable.getTable());
        MaterialFloatingButton floatButton=new MaterialFloatingButton("");
        floatButton.setIcon("/ic_download.png");
        floatButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(pageTable.getSelectedItem()!=null){
                    manager.setStatus(null);
				try {
					manager.download();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }
                else
                    new MaterialToast("Select a guide section first!").noSound().unhide();
            
            }
        });
        new MaterialTooltip("Download section files",floatButton);
        this.getContentCard().setFloatingButton(floatButton);
        this.setRootComponent(pageTable);
    }
    @Override
    public void onShown() {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
