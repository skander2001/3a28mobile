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
 * @author Skander
 */
public class HomeForm extends Form{
    private Form current;
    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnUsers = new Button("List Users");

        Button btnAddTask = new Button("Add Produit");
        Button btnListTasks = new Button("Liste des produits");
        Button btnAddTask1 = new Button("Add categorie");
        Button btnListTask = new Button("Liste des categories");
        Button btnAddTask2 = new Button("Add remise");
        Button btnListTask2 = new Button("List remise");
        Button btnAddTask3 = new Button("Add Statut");
        Button btnListTask3 = new Button("List Statuts");
        Button btnAddTask4 = new Button("Add Event");
        Button btnListTasks4 = new Button("List Event");
        Button btnAddTask5 = new Button("Add Workshop");
        Button btnListTask5 = new Button("List Workshop");
        
        
        
        btnAddTask4.addActionListener(e-> new AddEventForm(this).show());
        btnListTasks4.addActionListener(e-> new ListEventForm(this).show());
        
        btnAddTask2.addActionListener(e-> new AddRemieForm(this).show());
        btnListTask2.addActionListener(e-> new ListRemiseForm(this).show());
        

        
        btnAddTask3.addActionListener(e-> new AddStatutForm(this).show());
        btnListTask3.addActionListener(e-> new ListStatutsForm(this).show());
        btnAddTask.addActionListener(e-> new AddProduit(this).show());
        btnListTasks.addActionListener(e-> new ListProduit(this).show());
        btnAddTask1.addActionListener(e-> new AddCategorie(this).show());
        btnListTask.addActionListener(e-> new ListCategorie(this).show());

        btnUsers.addActionListener(e -> new ListeUsersForm(this).show());
        
        
        
        btnAddTask5.addActionListener(e-> new AddWorkshopForm(this).show());
        btnListTask5.addActionListener(e-> new ListWorkshopForm(this).show());
       // btnListTasks.addActionListener(e-> new ListStatutsForm(this).show());
       // btnUsers.addActionListener(e-> new ListStatutsForm(this).show());
       
        addAll(btnUsers,btnAddTask,btnAddTask1,btnListTasks, btnListTask,btnListTask2,btnAddTask2,btnAddTask3,btnListTask3,btnAddTask4,btnListTasks4,btnAddTask5,btnListTask5);
        
        
    }
    
    
}

