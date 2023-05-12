/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Cartitem;
import com.mycompany.entities.Remise;
import com.mycompany.myapp.services.ServicePanier;
import com.mycompany.myapp.services.ServiceRemise;
import java.util.ArrayList;
/**
 *
 * @author darka
 */
public class Panier extends Form {
    
   public Panier(Form previous) {
        setTitle("Forum");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        
        Button btnAddTask = new Button("enter information");
        btnAddTask.addActionListener(e-> new Card(this).show());
               addAll(btnAddTask);
        ArrayList<Cartitem> cartitem = ServicePanier.getInstance().getAllStatuts();
        for (Cartitem t : cartitem) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   public void addElement(Cartitem stat) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");

    Label code = new Label("id: " + stat.getId());
    Label nom= new Label("nom: " + stat.getProduct().getNom());
    Label total = new Label("total: " + stat.getTotal());
    Label quantity = new Label("quantity: " + stat.getTotal());
    
    


     
    
     
     
    
    
    
    
     
     
     
     
     
     container.add(BorderLayout.WEST, BoxLayout.encloseY(
          code, nom, total,quantity
    ));
   
     
     
     
    // Ajouter un séparateur rouge entre chaque élément
    Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
}
    
     
        
public void refreshList() {
    removeAll();
    ArrayList<Cartitem> remises = ServicePanier.getInstance().getAllStatuts();
    for (Cartitem s : remises) {
        addElement(s);
    }
    revalidate();
}

 

}
