package com.example.testowanieCRUD.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends VerticalLayout implements RouterLayout {

    public MainLayout(){
        Tabs menu = new Tabs();
        menu.add(new Tab(new RouterLink("Students", StudentsView.class)));
        menu.add(new Tab(new RouterLink("Grades", GradesView.class)));
        menu.add(new Tab(new RouterLink("Courses", CoursesView.class)));
        add(new H1("Student Database System"), menu);
    }
}
