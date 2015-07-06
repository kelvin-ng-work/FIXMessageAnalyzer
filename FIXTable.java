package fix.parser;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.*;
import java.util.*;

// Database operations
public class FIXTable extends JPanel {
	String[][] FIXTable;
	private int totalDataFields = 0;
    
    public FIXTable() {
    	super(new GridLayout(1,0));
        // Creates the security records table
        JTable table = new JTable(new FIXTableModel());
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        // Creates the scroll pane
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Sets up column sizes
        initColumnSizes(table);
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        table.setFont(font);
        table.getTableHeader().setFont(font);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        add(scrollPane);
    }
    
    // Sets up column sizes
    private void initColumnSizes(JTable table) {
    	// Creates table model
        FIXTableModel model = (FIXTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        // Retrieves table handler
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();
        // Configures column properties
        for (int i = 0; i < 4; i++) {
            column = table.getColumnModel().getColumn(i);
            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;
            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, longValues[i],
                                 false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;
            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }
    
    class FIXTableModel extends AbstractTableModel {
        public FIXTableModel() {
     	   try{
     		   FIXParser parser = new FIXParser("1=ASTM00221=155=CBO59=054=138=100040=244=20.73076=916751=TRADER017713=DM7714=ABC1236820=N60=20150703-13:48:58.60337=20150703-211=Order2051017=001A220=0150=039=0151=100014=06=0.00031=0.00032=058=New Order ACK");
			   FIXTable = parser.parse();
			   for(int i = 0; i < FIXTable.length; i++) {
					for(int j = 0; j < 4; j++) {
						System.out.println(FIXTable[i][j]);
					}
				}
  	       }catch(Exception e){
  	          // Handle other errors
  	          e.printStackTrace();
  	       }
        }
        	
         private String[] columnNames = {"Tag",
                                         "Field",
                                         "Value",
                                         "Description"
                                         };
         public final Object[] longValues = { "TAG", "FIELD NAME",
                                             "FIELD VALUE", "COMMENTS" };

         // Gets the number of columns
         public int getColumnCount() {
             return columnNames.length;
         }

         // Gets the number of rows
         public int getRowCount() {
         	return FIXTable.length;
         }

     	// Gets the column name
         public String getColumnName(int col) {
             return columnNames[col];
         }

     	// Gets a specific security record's data value
         public Object getValueAt(int row, int col) {
     		return FIXTable[row][col];
         }

         // Gets the class of a particular column
         public Class getColumnClass(int c) {
             return getValueAt(0, c).getClass();
         }
     }

    // Creates and displays the UI
    public static void createAndShowGUI() {
        // Creates and configures the UI Frame and the content pane
        JFrame frame = new JFrame("FIX Message Fields");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FIXTable newContentPane = new FIXTable();
        int contentPaneWidth = 700;
  	    int contentPaneHeight = 700;
        newContentPane.setPreferredSize(new Dimension(contentPaneWidth, contentPaneHeight));
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    // Main function to run this program alone
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });     
    }
}