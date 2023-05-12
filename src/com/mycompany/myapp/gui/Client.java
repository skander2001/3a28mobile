/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author darka
 */
public class Client extends Form {

    public Client() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("panier");
 
        
      btnAddTask.addActionListener(e-> new Panier(this).show());

        addAll(btnAddTask);
        
        
    }
    
}
