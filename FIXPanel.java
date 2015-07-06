package fix.parser;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class FIXPanel extends JFrame{
	private JPanel searchFields;
	private JPanel contentPanel;
	private JLabel msgLabel;
	private JLabel FIXMsgLabel = new JLabel("FIX Message");
	private JTextField FIXMsgField = new JTextField(25);
	private JButton runButton = new JButton("Run");
	private Font font = new Font("Tahoma", Font.PLAIN, 16);
	private int totalDataFields = 0;
	
	public FIXPanel() {
		setTitle( "FIX Message Analyzer" );
		setPreferredSize(new Dimension(700, 600));
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout());
		searchFields = new JPanel();
		searchFields.setLayout(new FlowLayout());
		searchFields.add(FIXMsgLabel);
		searchFields.add(FIXMsgField);
		searchFields.add(runButton);
		runButton.addActionListener(new searchActionListener());
		contentPanel.add(searchFields, BorderLayout.NORTH);
		msgLabel = new JLabel("Enter FIX Message string and run", SwingConstants.CENTER);
		contentPanel.add(msgLabel, BorderLayout.CENTER);
		add(contentPanel);
		setFont(font);
		pack();
		setVisible(true);
	}
    
	// ActionListener registered to the run button to process FIX message
	class searchActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(msgLabel);
			contentPanel.add(new FIXTable(), BorderLayout.CENTER);
			invalidate();
			validate();
		}
	}
		
	// Creates the display panel
	protected JPanel createDisplayPanel(String[][] FIXTable, String[] FIXTableColNames, boolean init) {
	    JPanel displayPanel = new JPanel();
	    displayPanel.setLayout(new GridLayout(0, 1));
	    int displayPanelWidth = 600;
		int displayPanelHeight = 600;
		displayPanel.setPreferredSize(new Dimension(displayPanelWidth, displayPanelHeight));
		JTable table;
		if(init == true) {
			table = new JTable(0, 4);
		} else {
			table = new JTable(FIXTable, FIXTableColNames);
		}
        table.setAutoCreateRowSorter(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // Creates the scroll pane
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Sets up column sizes
        for (int i = 0; i < FIXTableColNames.length; i++) {
        	table.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        table.setFont(font);
        table.getTableHeader().setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        // Adds the scroll pane to this panel.
        displayPanel.add(scrollPane);
        return displayPanel;
	}
	
	public static void main(String[] args) {
		FIXPanel fPanel = new FIXPanel();
	}
}