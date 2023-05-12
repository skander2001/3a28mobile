/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Skander
 */
public class ListReclamationsForm extends Form {


    public ListReclamationsForm() {
     
 Container List = new Container (BoxLayout.y());
    
       
        for (Iterator<Reclamation> it = ServiceReclamation.getInstance().listRec().iterator(); 
                it.hasNext();) {
            Reclamation p = it.next();
            MultiButton mb = new MultiButton(p.toString());
            Button update = new Button("update");
            
            update.setUIID("update");
            //update.addActionListener(e -> new Update(p,previous).show());
            Button delete = new Button("delete");
            delete.setUIID("delete");
            //delete.addActionListener(e -> new LivreurDelete(p,previous).show());

add(mb);
add(delete);
add(update);

    }

    }
}
 
