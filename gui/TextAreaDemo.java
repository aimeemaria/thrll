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
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;
import javax.swing.GroupLayout.*;


public class TextAreaDemo extends JFrame{

	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private JScrollPane textscroll;
	private JTextArea textArea;
	private DisplayPanel graph;
	private JButton execute,simulate,clear,generate;
	private JTextArea text;

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
		public void helloProfAho(String thrillCode, String command){
			
			FileOutputStream fout = null;
			
			simulate.setEnabled(true);
			try
			{
				// Open an output stream
				fout = new FileOutputStream ("./code.txt");//("/thrill/runthrill/code.txt");

				// Print a line of text
				new PrintStream(fout).print(thrillCode);

				// Close our output stream
				fout.close();		

			}
			catch (IOException e)
			{
				System.err.println ("Unable to write to file");
				System.exit(-1);
			}

			//run shell script to compile, and execute showing output on the screen

			String s = "", output="";

			try {
				String script = null;
				// we either execute or execute and generate position file.
				if(command.equalsIgnoreCase("Execute")) {
					script = "./thrill_execute.sh";
				}
				else{
					script = "./thrill_generate.sh";
				}
				
				Process p = Runtime.getRuntime().exec(script);
				
				BufferedReader stdInput = new BufferedReader(new 
						InputStreamReader(p.getInputStream()));

				BufferedReader stdError = new BufferedReader(new 
						InputStreamReader(p.getErrorStream()));

				// read the output from the command
				while ((s = stdInput.readLine()) != null) {
					//System.out.println(s);
					output = output + s + "\n";
				}

				// read any errors from the attempted command
				//System.out.println("Here is the standard error of the command (if any):\n");
				while ((s = stdError.readLine()) != null) {
					//System.out.println(s);
					output = output + s + "\n";
				}

				text.append(output);
			}
			catch (IOException e) {
				text.setText(e.getMessage());
				e.printStackTrace();
				System.exit(-1);
			}
		}
			
		public void actionPerformed(ActionEvent a)
		{
			if(a.getSource() == execute){
				text.setText("Executing now . . . ");
				helloProfAho(textArea.getText(), "Execute");
			}
			if(a.getSource() == simulate){
				text.setText("Simlulating . . . ");
				paintppl();
				text.append("Wicked COOL!");
			}

			if(a.getSource() == generate) {
				text.setText("Generating now... <Hope Prof likes this>\n");
				helloProfAho(textArea.getText(), "Generate");
			}

			if(a.getSource() == clear) {
				textArea.setText("");
				text.setText("");
			}
		}
	}
	private void initComponents() throws InterruptedException {
		jLabel1 = new JLabel("Enter your Program Code  below . . . ");
		text = new JTextArea();
		textArea = new JTextArea();
		graph = new DisplayPanel();
		//graph.setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		textArea.setColumns(50);
		textArea.setLineWrap(true);
		textArea.setRows(25);
		textArea.setWrapStyleWord(true);

		text.setColumns(10);
		text.setLineWrap(true);
		text.setRows(25);
		text.setWrapStyleWord(true);

		jScrollPane1 = new JScrollPane(textArea);
		textscroll = new JScrollPane(text);
		execute = new JButton("Execute");
		clear = new JButton("Clear");
		simulate = new JButton("Simulate");
		generate = new JButton("Generate");

		// set dimension
		Dimension bDimension = new Dimension(100, 25);
		simulate.setMinimumSize(bDimension);
		execute.setMinimumSize(bDimension);
		clear.setMinimumSize(bDimension);		
		generate.setMinimumSize(bDimension);
		
		// add event listener
		execute.addActionListener(new BListener());
		simulate.addActionListener(new BListener());
		generate.addActionListener(new BListener());
		clear.addActionListener(new BListener());

		// disable simulate
		simulate.setEnabled(false);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setAutoCreateGaps(true);
		//  layout.setAutoCreateContainerGaps(true);


		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1)
						.addComponent(jScrollPane1)
						.addComponent(textscroll))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(graph)
								.addComponent(execute)
								.addComponent(simulate)
								.addComponent(clear)
								.addComponent(generate))
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(jLabel1)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(jScrollPane1)
						.addComponent(graph))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(textscroll)
								.addGroup(layout.createSequentialGroup()
										.addComponent(execute)
										.addComponent(simulate)
										.addComponent(clear)
										.addComponent(generate)))
		);

		pack();
	}

	public void paintppl(){
		text.setText("Executing now . . . ");
		graph.paintppl(100,100);


		repaint();
	}


}
