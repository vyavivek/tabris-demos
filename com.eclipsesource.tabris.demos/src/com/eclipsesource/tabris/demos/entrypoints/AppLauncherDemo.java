/*******************************************************************************
 * Copyright (c) 2012 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package com.eclipsesource.tabris.demos.entrypoints;

import static com.eclipsesource.tabris.widgets.enhancement.Widgets.onToolItem;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.rap.rwt.widgets.DialogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.eclipsesource.tabris.interaction.AppLauncher;
import com.eclipsesource.tabris.interaction.BrowserOptions;
import com.eclipsesource.tabris.interaction.MailOptions;
import com.eclipsesource.tabris.interaction.MapsOptions;
import com.eclipsesource.tabris.interaction.PhoneOptions;
import com.eclipsesource.tabris.interaction.SMSOptions;


public class AppLauncherDemo implements EntryPoint {

  private Display display;

  public int createUI() {
    display = new Display();
    final Shell shell = createShell();
    AppLauncher appLauncher = RWT.getClient().getService( AppLauncher.class );
    if( appLauncher != null ) {
      createToolBar( shell );
      Composite container = createParentComposite( shell );
      createBrowserLauncher( container, appLauncher );
      createMailLauncher( container, appLauncher );
      createPhoneLauncher( container, appLauncher );
      createMapsLauncher( container, appLauncher );
      createTextLauncher( container, appLauncher );
      shell.open();
    } else {
      createWebClientContent( shell );
    }
    return 0;
  }

  private Shell createShell() {
    final Shell shell = new Shell( display, SWT.NONE );
    shell.setMaximized( true );
    GridLayout shellLayout = new GridLayout( 1, false );
    shellLayout.marginHeight = 0;
    shellLayout.marginWidth = 0;
    shell.setLayout( shellLayout );
    return shell;
  }

  private void createToolBar( final Composite parent ) {
    ToolBar toolBar = new ToolBar( parent, SWT.NONE );
    toolBar.setLayoutData( UiUtil.createFillHori() );
    ToolItem toolItem = new ToolItem( toolBar, SWT.NONE );
    toolItem.setText( "Heisenberg's App" );
    onToolItem( toolItem ).useAsTitle();
  }

  private Composite createParentComposite( final Shell shell ) {
    Composite comp = new Composite( shell, SWT.NONE );
    GridLayout compLayout = new GridLayout( 1, false );
    compLayout.marginWidth = 16;
    compLayout.horizontalSpacing = 16;
    comp.setLayout( compLayout );
    comp.setLayoutData( UiUtil.createFill() );
    return comp;
  }

  private void createBrowserLauncher( Composite parent, final AppLauncher appLauncher ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "Open Los Pollos Site" );
    button.setLayoutData( UiUtil.createFillHori() );
    button.setBackground( new Color( display, 70, 124, 30 ) );
    button.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        appLauncher.open( new BrowserOptions( "http://breakingbad.wikia.com/wiki/Los_Pollos_Hermanos" ) );
      }
    } );
  }
  
  private void createMailLauncher( Composite parent, final AppLauncher appLauncher ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "New Mail to Mike" );
    button.setLayoutData( UiUtil.createFillHori() );
    button.setBackground( new Color( display, 191, 37, 39 ) );
    button.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        appLauncher.open( new MailOptions( "mike@lospollos.com", "We need to talk!" ) );
      }
    } );
  }
  
  private void createPhoneLauncher( Composite parent, final AppLauncher appLauncher ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "Call Jesse" );
    button.setLayoutData( UiUtil.createFillHori() );
    button.setBackground( new Color( display, 70, 124, 30 ) );
    button.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        appLauncher.open( new PhoneOptions( "555 234 554 22" ) );
      }
    } );
  }
  
  private void createMapsLauncher( Composite parent, final AppLauncher appLauncher ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "View White Residence" );
    button.setLayoutData( UiUtil.createFillHori() );
    button.setBackground( new Color( display, 191, 37, 39 ) );
    button.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        appLauncher.open( new MapsOptions( "3828 Piermont Dr. NE ABQ, NM" ) );
      }
    } );
  }
  
  private void createTextLauncher( Composite parent, final AppLauncher appLauncher ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "Text Hank Schrader" );
    button.setLayoutData( UiUtil.createFillHori() );
    button.setBackground( new Color( display, 70, 124, 30 ) );
    button.addSelectionListener( new SelectionAdapter() {
      @Override
      public void widgetSelected( SelectionEvent e ) {
        appLauncher.open( new SMSOptions( "555 232 111 23", 
                                          "Hank, congratulations to your promotion. Cheers Walter" ) );
      }
    } );
  }

  private void createWebClientContent( final Shell shell ) {
    MessageBox messageBox = new MessageBox( shell, SWT.ICON_WARNING );
    messageBox.setMessage( "This demo is availaible on mobile devices only." );
    messageBox.setText( "Tabris Demo" );
    shell.open();
    DialogUtil.open( messageBox, null );
  }
}
