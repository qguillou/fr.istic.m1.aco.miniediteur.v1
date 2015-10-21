package invoker;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import observer.Observer;
import receiver.EditorEngine;

@SuppressWarnings("serial")
public class TextArea extends JTextArea implements Observer {
	
	protected EditorEngine subject;
		
	public void create(){
		setEditable(false);
		getCaret().setVisible(true);
		setFont(new Font("Monospaced",Font.PLAIN,12));
		setMargin(new Insets(10,10,10,10));
		setLineWrap(true);
	}
	
	public JScrollPane getScrollArea(){
		JScrollPane scroll = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		return scroll;
	}
	
	public void update(String text, int start, int end){
		setText(text);
		setSelectionStart(start);
		setSelectionEnd(end);
		getCaret().setVisible(true);
		requestFocusInWindow();
	}

	@Override
	public void notifyObserver() {
		update(subject.getText(), subject.getSelectionStart(), subject.getSelectionLength() + subject.getSelectionStart());
	}

	@Override
	public void registerSubject(EditorEngine engineImpl) {
		subject = engineImpl;
	}

	public void help() {
		String help = "Help\n\n";
		help += "To exit help, type something in the textarea\n\n";
		help += "Basics commands are available by \"Edit\" menu, toolbar bouton or shortcut.\n";
		setText(help);
	}
}
