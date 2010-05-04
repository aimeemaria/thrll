/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

//package components;

/*
 * TextAreaDemo.java requires no other files.
 */

import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;


public class TextAreaDemo extends JFrame{
    
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTextArea textArea;
    private DisplayPanel graph;
    private JButton execute,simulate;
    private JTextField text;
    
    public TextAreaDemo() {
	try {
		initComponents();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
        
    private class BListener implements ActionListener
	{
		public void actionPerformed(ActionEvent a)
		{
			String message = textArea.getText();

			if(a.getSource() == execute){
				FileOutputStream fout;		
				text.setText("Executing now . . . ");
				try
				{
				    // Open an output stream
				    fout = new FileOutputStream ("code.txt");

				    // Print a line of text
				    new PrintStream(fout).print (message);

				    // Close our output stream
				    fout.close();		
				
				}
				catch (IOException e)
				{
					System.err.println ("Unable to write to file");
					System.exit(-1);
				}
				/*
				//run shell script to compile, and execute showing output on the screen
				Runtime r = Runtime.getRuntime();
						Process p = null;
						try {
							p = r.exec("echo hello");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							p.waitFor();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				*/
				 String s = null;

			        try {
				  Process p = Runtime.getRuntime().exec("./shell.sh");
		            
		            BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(p.getInputStream()));

		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(p.getErrorStream()));

		            // read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            while ((s = stdInput.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		            // read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println(s);
		            }
		            
		          //  System.exit(0);
		        }
		        catch (IOException e) {
		            System.out.println("exception happened - here's what I know: ");
		            e.printStackTrace();
		            System.exit(-1);
		        }

					
			
			}
		}
	}
    private void initComponents() throws InterruptedException {
        jLabel1 = new JLabel("Enter your Program Code  below . . . ");
        text = new JTextField (5);
        textArea = new JTextArea();
	    graph = new DisplayPanel();
	    //graph.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textArea.setColumns(50);
        textArea.setLineWrap(true);
        textArea.setRows(25);
        textArea.setWrapStyleWord(true);
        
        jScrollPane1 = new JScrollPane(textArea);
        execute = new JButton("Execute");
        simulate = new JButton("Simulate");
        execute.addActionListener(new BListener());
        simulate.addActionListener(new BListener());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
 
       layout.setAutoCreateGaps(true);
     //  layout.setAutoCreateContainerGaps(true);

  
       layout.setHorizontalGroup(
          layout.createSequentialGroup()
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addComponent(jLabel1)
             .addComponent(jScrollPane1)
             .addComponent(text))
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(graph)
                  .addComponent(execute)
                  .addComponent(simulate))
           );
       layout.setVerticalGroup(
          layout.createSequentialGroup()
          .addComponent(jLabel1)
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	  .addComponent(jScrollPane1)
                  .addComponent(graph))
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		 .addComponent(text)
            		 .addComponent(execute))
             .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		 .addComponent(simulate))
       );
        pack();
        //setVisible(true);
       // Thread.sleep(2000);
		//paintppl();
		
    }
    
    public void paintppl(){
    	text.setText("Executing now . . . ");
    	graph.paintppl(100,100);
    }


}
