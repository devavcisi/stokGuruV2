/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guru.stokguruv2.gui;

import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

/**
 *
 * @author User
 */
public interface IMainFrame {
    
    
    JToolBar getMainToolBar ();
    JMenuBar getMainMenuBar();
    JDesktopPane  getDesktopPaneControl();
    
    
    
}
