/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chartsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author Cafer Tayyar YORUK
 */
public class AnaPencere extends javax.swing.JFrame {
    
    static class CustomBarRenderer extends BarRenderer{
 
        private Paint colors[];
        public Paint getItemPaint(int i, int j){

            return colors[j % colors.length];
        }

        public CustomBarRenderer(Paint apaint[]){

                colors = apaint;
        }
    }
 
    /**
     * Creates new form AnaPencere
     */
    
    TreeMap< String, Integer > siniflarWithSkorsMap = new TreeMap< String, Integer >();
    
    ArrayList<String > studentNameList1 = new ArrayList<String>();
    ArrayList<String > studentNameList2 = new ArrayList<String>();
    ArrayList<String > studentNameList3 = new ArrayList<String>();
    
    ArrayList<Integer > studentSkorList2 = new ArrayList<Integer>();
    ArrayList<Integer > studentSkorList3 = new ArrayList<Integer>();
    
    ArrayList<String > studentSchoolList3 = new ArrayList<String>();
    ArrayList<String > studentSinifList3 = new ArrayList<String>();
    ArrayList<String > studentSectionList3 = new ArrayList<String>();
    
    String[] studentNameListInTable1 = { "", "" };
    String[] studentNameListInTable2 = { "", "", "" };
    String[] studentNameListInTable3 = { "", "", "", "", "", "" };
    
    Connection conn;
    Statement s;
    String sqlQuery = "";
    
    String name1, name2, name3;
    String school1, school2, school3;
    String sinif1, sinif2, sinif3;
    String section1, section2, section3;
    int skor2, skor3;
    
    String[] schoolList = { "Fen Lisesi", "Anadolu Lisesi" };
    String[] sinifList = { "9", "10", "11", "12" };
    String[][] sinifWithSectionsList;
    String[][] sinifWithSkorsList;
        
    DefaultTableModel model1; // Model for JTable1 in Öğrenci Ekleme/Listeleme
    DefaultTableModel model2; // Model for JTable2 in Öğrenci Ekleme/Listeleme
    DefaultTableModel model3; // Model for JTable3 in Öğrenci Ekleme/Listeleme
    DefaultTableModel model4; // Model for JTable4 in Öğrenci Ekleme/Listeleme
    DefaultTableModel model5; // Model for JTable5 in Öğrenci Ekleme/Listeleme
    
    public AnaPencere() {
        
        initComponents();
        
        model1 = (DefaultTableModel) jTable1.getModel();
        model2 = (DefaultTableModel) jTable2.getModel();
        model3 = (DefaultTableModel) jTable3.getModel();
        model4 = (DefaultTableModel) jTable4.getModel();
        model5 = (DefaultTableModel) jTable5.getModel();

        database();
        
        fillTableWithData4();
        fillTableWithData5();
    }
    
    private static Paint[] createPaint(){

            Paint apaint[] = new Paint[6];

            apaint[0] = Color.RED;
            apaint[1] = Color.GREEN;
            apaint[2] = Color.BLUE;
            apaint[3] = Color.ORANGE;
            apaint[4] = Color.MAGENTA;
            apaint[5] = Color.CYAN;
            
            return apaint;
    }
 
    public void database(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Derby driver not found.");
        }
        try {
            conn = DriverManager.getConnection("jdbc:derby:../test;create=true;user=cty;pass=1");
            s = conn.createStatement();
            
            /*
            s.execute( "CREATE TABLE Students(" +
                            "Name varchar(30)," +
                            "Skor int," +
                            "School varchar(20)," +
                            "Sinif varchar(2)," +
                            "Section varchar(1) )"
            );
            */
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void fillTableWithData1(){
        
        for( int i = 0; i < studentNameList1.size(); i++ ){
            studentNameListInTable1[ 0 ] = "" +  ( i + 1 );
            studentNameListInTable1[ 1 ] = studentNameList1.get( i );
            
            model1.addRow( studentNameListInTable1 );
            
        }
    }
    
    public void fillTableWithData2(){
        
        for( int i = 0; i < studentNameList2.size(); i++ ){
            studentNameListInTable2[ 0 ] = "" +  ( i + 1 );
            studentNameListInTable2[ 1 ] = studentNameList2.get( i );
            studentNameListInTable2[ 2 ] = studentSkorList2.get( i ).toString();
            
            model2.addRow( studentNameListInTable2 );
            
        }
    }
    
    public void fillTableWithData3(){
        
        for( int i = 0; i < studentNameList3.size(); i++ ){
            studentNameListInTable3[ 0 ] = "" +  ( i + 1 );
            studentNameListInTable3[ 1 ] = studentNameList3.get( i );
            studentNameListInTable3[ 2 ] = studentSkorList3.get( i ).toString();
            studentNameListInTable3[ 3 ] = studentSchoolList3.get( i );
            studentNameListInTable3[ 4 ] = studentSinifList3.get( i );
            studentNameListInTable3[ 5 ] = studentSectionList3.get( i );
            
            model3.addRow( studentNameListInTable3 );
            
        }
    }
    
    public void fillTableWithData4(){
        int mapSize = siniflarWithSkorsMap.size();
        
        sinifWithSkorsList = new String[ mapSize ][ 2 ];
        
        for( int m = 0; m < mapSize; m++ ){
            
            sinifWithSkorsList[ m ][ 0 ] = siniflarWithSkorsMap.lastEntry().getKey();
            sinifWithSkorsList[ m ][ 1 ] = siniflarWithSkorsMap.lastEntry().getValue().toString();

            siniflarWithSkorsMap.remove( siniflarWithSkorsMap.lastEntry().getKey(), siniflarWithSkorsMap.lastEntry().getValue() );
        }
        
        /**** Beginning of Sorting ****/
        
        int biggest; // index of smallest element
        // loop over data.length - 1 elements
        for ( int i = 0; i < sinifWithSkorsList.length - 1; i++ ){
            biggest = i; // first index of remaining array
            
            // loop to find index of smallest element
            for ( int index = i + 1; index < sinifWithSkorsList.length; index++ ){
                if ( Integer.parseInt( sinifWithSkorsList[ index ][ 1 ] ) > Integer.parseInt( sinifWithSkorsList[ biggest ][ 1 ] ) )
                    biggest = index;
            }
        
            // swap smallest element into position
            String[] temporary = sinifWithSkorsList[ i ]; // store first in temporary
            sinifWithSkorsList[ i ] = sinifWithSkorsList[ biggest ]; // replace first with second
            sinifWithSkorsList[ biggest ] = temporary; // put temporary in second
            
        } 
        /***** End of Sorting *****/
        
        // Jtable'a veriler yazılır.
        for( int i = 0; i < sinifWithSkorsList.length; i++ ){
             model4.addRow( new Object[]{ ( i + 1 ), sinifWithSkorsList[ i ][ 0 ], sinifWithSkorsList[ i ][ 1 ] } );
        }
            
        
    }
    
    public void fillTableWithData5(){
        model5.setNumRows( 0 );
        int[] toplamSinifSkor = { 0, 0, 0, 0 };
        int tümOkulToplamSkor = 0;
        
        TreeMap< String, Integer > myMap = new TreeMap< String, Integer >();
        
        for( int i = 0; i < 4; i++ ){
            sqlQuery = String.format( "SELECT * FROM Students WHERE Sinif = '%s'", sinifList[ i ] );

            try {
                s.execute( sqlQuery );
                ResultSet rs = s.getResultSet();
                while (rs.next()) {
                    toplamSinifSkor[ i ] += rs.getInt("Skor");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            myMap.put( sinifList[ i ], toplamSinifSkor[ i ] );
            
            tümOkulToplamSkor += toplamSinifSkor[ i ];
            tümOkulToplamSkorLabel.setText( "" + tümOkulToplamSkor );
            tümOkulKalanMiktarLabel.setText( "" + ( 1000000 - tümOkulToplamSkor ) );
        }
        int mapSize = myMap.size();
        sinifWithSectionsList = new String[ mapSize ][ 2 ];
        
        for( int m = 0; m < mapSize; m++ ){
            sinifWithSectionsList[ m ][ 0 ] = myMap.lastEntry().getKey() + ". Sınıflar";
            sinifWithSectionsList[ m ][ 1 ] = myMap.lastEntry().getValue().toString();

            myMap.remove( myMap.lastEntry().getKey(), myMap.lastEntry().getValue() );
        }
        
        
        /**** Beginning of Sorting ****/
        
        int biggest; // index of smallest element
        // loop over data.length - 1 elements
        for ( int i = 0; i < sinifWithSectionsList.length - 1; i++ ){
            biggest = i; // first index of remaining array
            
            // loop to find index of smallest element
            for ( int index = i + 1; index < sinifWithSectionsList.length; index++ ){
                if ( Integer.parseInt( sinifWithSectionsList[ index ][ 1 ] ) > Integer.parseInt( sinifWithSectionsList[ biggest ][ 1 ] ) )
                    biggest = index;
            }
        
            // swap smallest element into position
            String[] temporary = sinifWithSectionsList[ i ]; // store first in temporary
            sinifWithSectionsList[ i ] = sinifWithSectionsList[ biggest ]; // replace first with second
            sinifWithSectionsList[ biggest ] = temporary; // put temporary in second
            
        } 
        /***** End of Sorting *****/
        
        // Jtable'a veriler yazılır.
        for( int i = 0; i < sinifWithSectionsList.length; i++ ){
             model5.addRow( new Object[]{ ( i + 1 ), sinifWithSectionsList[ i ][ 0 ], sinifWithSectionsList[ i ][ 1 ] } );
        }
    }
    
    public void sinifWithSectionsWithSchools( String schoolName, String sinifName, String sectionName ){
        
        int toplamPuan = 0;
        
        sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Sinif = '%s' AND Section = '%s'", schoolName, sinifName, sectionName );

        try {
            s.execute( sqlQuery );
            ResultSet rs = s.getResultSet();
            while (rs.next()) {

                toplamPuan += rs.getInt( "Skor" );

            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if( schoolName.equals( "Fen Lisesi" ) ){
            siniflarWithSkorsMap.put( String.format( "%s-%s  Fen", sinifName, sectionName ), toplamPuan );
        
        } else {    // schoolName = "Anadolu Lisesi"
            siniflarWithSkorsMap.put( String.format( "%s-%s  Anadolu", sinifName, sectionName ), toplamPuan );
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        schoolComboBox1 = new javax.swing.JComboBox();
        okulLabel1 = new javax.swing.JLabel();
        sinifLabel1 = new javax.swing.JLabel();
        sinifComboBox1 = new javax.swing.JComboBox();
        subeLabel1 = new javax.swing.JLabel();
        sectionComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        showListButton1 = new javax.swing.JButton();
        addStudentButton = new javax.swing.JButton();
        deleteStudentButton = new javax.swing.JButton();
        studentNameLabel = new javax.swing.JLabel();
        studentNameTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        okulLabel2 = new javax.swing.JLabel();
        schoolComboBox2 = new javax.swing.JComboBox();
        sinifLabel2 = new javax.swing.JLabel();
        sinifComboBox2 = new javax.swing.JComboBox();
        subeLabel2 = new javax.swing.JLabel();
        sectionComboBox2 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        showListButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        eklenecekSkorLabel = new javax.swing.JLabel();
        skorTextField = new javax.swing.JTextField();
        skorEkleButton = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        changeSkorLabel = new javax.swing.JLabel();
        skorChangeTextField = new javax.swing.JTextField();
        skorChangeButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        okulLabel3 = new javax.swing.JLabel();
        schoolComboBox3 = new javax.swing.JComboBox();
        sinifLabel3 = new javax.swing.JLabel();
        sinifComboBox3 = new javax.swing.JComboBox();
        subeLabel3 = new javax.swing.JLabel();
        sectionComboBox3 = new javax.swing.JComboBox();
        showListButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        showGrafikButton1 = new javax.swing.JButton();
        grafikSpinner1 = new javax.swing.JSpinner();
        grafikLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        sinifLabel4 = new javax.swing.JLabel();
        sinifComboBox4 = new javax.swing.JComboBox();
        showListButton4 = new javax.swing.JButton();
        showGrafikButton2 = new javax.swing.JButton();
        grafikLabel2 = new javax.swing.JLabel();
        grafikSpinner2 = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        showGrafikButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tümOkulToplamSkorLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tümOkulKalanMiktarLabel = new javax.swing.JLabel();
        yenileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        schoolComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fen Lisesi", "Anadolu Lisesi" }));
        schoolComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolComboBox1ActionPerformed(evt);
            }
        });

        okulLabel1.setText("Okul:");

        sinifLabel1.setText("Sınıf:");

        sinifComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "9", "10", "11", "12" }));
        sinifComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinifComboBox1ActionPerformed(evt);
            }
        });

        subeLabel1.setText("Şube:");

        sectionComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));
        sectionComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "İSİM"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        showListButton1.setText("Listeyi Göster / Yenile");
        showListButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showListButton1ActionPerformed(evt);
            }
        });

        addStudentButton.setText("Öğrenci Ekle");
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        deleteStudentButton.setText("Öğrenci Sil");
        deleteStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentButtonActionPerformed(evt);
            }
        });

        studentNameLabel.setText("Öğrencinin İsmi:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(okulLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schoolComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sinifLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sinifComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subeLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectionComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(showListButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(studentNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(studentNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schoolComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okulLabel1)
                    .addComponent(sinifComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinifLabel1)
                    .addComponent(sectionComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subeLabel1)
                    .addComponent(showListButton1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(studentNameLabel)
                            .addComponent(studentNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addStudentButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteStudentButton)
                        .addContainerGap(322, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Öğrenci Ekleme / Listeleme", jPanel1);

        okulLabel2.setText("Okul:");

        schoolComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fen Lisesi", "Anadolu Lisesi" }));
        schoolComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolComboBox2ActionPerformed(evt);
            }
        });

        sinifLabel2.setText("Sınıf:");

        sinifComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "9", "10", "11", "12" }));
        sinifComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinifComboBox2ActionPerformed(evt);
            }
        });

        subeLabel2.setText("Şube:");

        sectionComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D" }));
        sectionComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionComboBox2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "İSİM", "SKOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(24);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable2.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        showListButton2.setText("Listeyi Göster / Yenile");
        showListButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showListButton2ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Mevcut Skorun Üstüne Skor Ekleme"));

        eklenecekSkorLabel.setText("Eklenecek Skor:");

        skorEkleButton.setText("Skoru Ekle");
        skorEkleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorEkleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(eklenecekSkorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(skorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(skorEkleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eklenecekSkorLabel)
                    .addComponent(skorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skorEkleButton)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Skor Değiştirme"));

        changeSkorLabel.setText("Skor:");

        skorChangeButton.setText("Skoru Değiştir");
        skorChangeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skorChangeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(changeSkorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(skorChangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(skorChangeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeSkorLabel)
                    .addComponent(skorChangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skorChangeButton)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(okulLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schoolComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sinifLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sinifComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subeLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectionComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showListButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schoolComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okulLabel2)
                    .addComponent(sinifComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinifLabel2)
                    .addComponent(sectionComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subeLabel2)
                    .addComponent(showListButton2))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Skor Ekleme / Listeleme", jPanel2);

        okulLabel3.setText("Okul:");

        schoolComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hepsi", "Fen Lisesi", "Anadolu Lisesi" }));
        schoolComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                schoolComboBox3ActionPerformed(evt);
            }
        });

        sinifLabel3.setText("Sınıf:");

        sinifComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hepsi", "9", "10", "11", "12" }));
        sinifComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinifComboBox3ActionPerformed(evt);
            }
        });

        subeLabel3.setText("Şube:");

        sectionComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hepsi", "A", "B", "C", "D" }));
        sectionComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectionComboBox3ActionPerformed(evt);
            }
        });

        showListButton3.setText("Listeyi Göster / Yenile");
        showListButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showListButton3ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "İSİM", "SKOR", "OKUL", "SINIF", "ŞUBE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(24);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable3.getColumnModel().getColumn(2).setMaxWidth(150);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTable3.getColumnModel().getColumn(3).setMaxWidth(100);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(4).setMaxWidth(50);
            jTable3.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTable3.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        showGrafikButton1.setText("Grafiği Göster");
        showGrafikButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGrafikButton1ActionPerformed(evt);
            }
        });

        grafikSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(1), null, Integer.valueOf(1)));

        grafikLabel1.setText("Grafikte Görünecek Kişi Sayısı:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(showGrafikButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(okulLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(schoolComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sinifLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sinifComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(subeLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sectionComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(showListButton3)
                        .addGap(73, 73, 73)
                        .addComponent(grafikLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grafikSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schoolComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(okulLabel3)
                    .addComponent(sinifComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinifLabel3)
                    .addComponent(sectionComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subeLabel3)
                    .addComponent(showListButton3)
                    .addComponent(grafikSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grafikLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(showGrafikButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Öğrenci Grafik Görünteleme", jPanel3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sınıf Listesi"));
        jPanel5.setName(""); // NOI18N

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "SINIF", "SKOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setRowHeight(24);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable4.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable4.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable4.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        sinifLabel4.setText("Sınıf:");

        sinifComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hepsi", "9", "10", "11", "12" }));
        sinifComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sinifComboBox4ActionPerformed(evt);
            }
        });

        showListButton4.setText("Listeyi Göster / Yenile");
        showListButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showListButton4ActionPerformed(evt);
            }
        });

        showGrafikButton2.setText("Grafiği Göster");
        showGrafikButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGrafikButton2ActionPerformed(evt);
            }
        });

        grafikLabel2.setText("Grafikte Görünecek Sınıf Sayısı:");

        grafikSpinner2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(5), Integer.valueOf(1), null, Integer.valueOf(1)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(grafikLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grafikSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(sinifLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(sinifComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(showListButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(showGrafikButton2))))
                .addGap(33, 33, 33))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sinifComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sinifLabel4)
                    .addComponent(showListButton4)
                    .addComponent(showGrafikButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grafikSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grafikLabel2))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tüm Okul"));
        jPanel6.setName(""); // NOI18N

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "SINIF", "SKOR"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setRowHeight(24);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable5.getColumnModel().getColumn(0).setMaxWidth(30);
            jTable5.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable5.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        showGrafikButton3.setText("Grafiği Göster");
        showGrafikButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGrafikButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("Hedef Skor: 1 Milyon");

        jLabel2.setText("Tüm Okulun Toplam Skoru: ");

        tümOkulToplamSkorLabel.setText("jLabel3");

        jLabel4.setText("Hedefe Kalan Miktar: ");

        tümOkulKalanMiktarLabel.setText("jLabel5");

        yenileButton.setText("Yenile");
        yenileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yenileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(showGrafikButton3)
                        .addGap(18, 18, 18)
                        .addComponent(yenileButton))
                    .addComponent(jLabel1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tümOkulToplamSkorLabel)
                            .addComponent(tümOkulKalanMiktarLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showGrafikButton3)
                    .addComponent(yenileButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tümOkulToplamSkorLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tümOkulKalanMiktarLabel))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sınıf Grafik Görüntüleme", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void schoolComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolComboBox1ActionPerformed

    private void sinifComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinifComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sinifComboBox1ActionPerformed

    private void sectionComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sectionComboBox1ActionPerformed

    private void schoolComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolComboBox2ActionPerformed

    private void sinifComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinifComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sinifComboBox2ActionPerformed

    private void sectionComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sectionComboBox2ActionPerformed

    private void showListButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showListButton1ActionPerformed
        
        model1.setNumRows( 0 );
        studentNameList1.clear();
        
        school1 = schoolComboBox1.getSelectedItem().toString();
        sinif1 = sinifComboBox1.getSelectedItem().toString();
        section1 = sectionComboBox1.getSelectedItem().toString();
        
        sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Sinif = '%s' AND Section = '%s' ORDER BY Name", school1, sinif1, section1 );
        
        try {
            s.execute( sqlQuery );
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                name1 = rs.getString("Name");
                
                studentNameList1.add( name1 );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fillTableWithData1();
    }//GEN-LAST:event_showListButton1ActionPerformed

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentButtonActionPerformed
                
        if( !studentNameTextField.getText().trim().equals("") && !studentNameTextField.getText().contains( "\'") ){
            try {
                
                sqlQuery = String.format( "INSERT INTO Students VALUES ( '%s', %d, '%s', '%s', '%s' )", 
                                studentNameTextField.getText(), 0, schoolComboBox1.getSelectedItem(), sinifComboBox1.getSelectedItem(), sectionComboBox1.getSelectedItem() ); 
                s.execute( sqlQuery );
                
                model1.addRow( new Object[]{ model1.getRowCount() + 1, studentNameTextField.getText() } );

                studentNameTextField.setText( "" );
                
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if( studentNameTextField.getText().trim().equals("") ){
                JOptionPane.showMessageDialog( this, "\"Öğrencinin İsmi\" boş bırakılmamalıdır!" );
            
            } else {
                JOptionPane.showMessageDialog( this, "\"Öğrencinin İsmi\" geçersiz! İsimde   '   karakteri bulunmamalıdır." );
            }
        }
    }//GEN-LAST:event_addStudentButtonActionPerformed

    private void deleteStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStudentButtonActionPerformed
        
        if( jTable1.getSelectedRow() == -1 ){
            if( jTable1.getRowCount() == 0 ){
                JOptionPane.showMessageDialog( this, "Tablo şu an boş!" );
            } else {
                JOptionPane.showMessageDialog( this, "Tablodan bir öğrenci seçiniz!" );
            }
        } else {
            
            try {
                name1 = model1.getValueAt( jTable1.getSelectedRow(), 1 ).toString();
                
                sqlQuery = String.format( "DELETE FROM Students WHERE Name = '%s' AND School = '%s' AND Sinif = '%s' AND Section = '%s'", 
                                                name1, schoolComboBox1.getSelectedItem(), sinifComboBox1.getSelectedItem(), sectionComboBox1.getSelectedItem()  );
                s.execute( sqlQuery );
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println( "Name: " + name1 + ", Row: " + jTable1.getSelectedRow()  );
            model1.removeRow( jTable1.getSelectedRow() );
        }
        
        
    }//GEN-LAST:event_deleteStudentButtonActionPerformed

    private void skorEkleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorEkleButtonActionPerformed
        if( jTable2.getSelectedRow() == -1 ){
            if( jTable2.getRowCount() == 0 ){
                JOptionPane.showMessageDialog( this, "Tablo şu an boş!" );
            } else {
                JOptionPane.showMessageDialog( this, "Tablodan bir öğrenci seçiniz!" );
            }
        } else {
            try {
                
                name2 = model2.getValueAt( jTable2.getSelectedRow(), 1 ).toString();
                skor2 = Integer.parseInt( model2.getValueAt( jTable2.getSelectedRow(), 2 ).toString() );
                
                skor2 += Integer.parseInt( skorTextField.getText() );
                
                sqlQuery = String.format( "UPDATE Students SET Skor = %d WHERE Name = '%s' AND School = '%s' AND Sinif = '%s' AND Section = '%s'", 
                                        skor2, name2, schoolComboBox2.getSelectedItem(), sinifComboBox2.getSelectedItem(), sectionComboBox2.getSelectedItem() );
                s.execute( sqlQuery );
                
                model2.setValueAt( skor2, jTable2.getSelectedRow(), 2 );
                
                fillTableWithData5();
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog( this, "Tam sayı giriniz!" );
            
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_skorEkleButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
    }//GEN-LAST:event_jTable2MouseClicked

    private void showListButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showListButton2ActionPerformed
        model2.setNumRows( 0 );
        studentNameList2.clear();
        studentSkorList2.clear();
        
        school2 = schoolComboBox2.getSelectedItem().toString();
        sinif2 = sinifComboBox2.getSelectedItem().toString();
        section2 = sectionComboBox2.getSelectedItem().toString();
        
        sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Sinif = '%s' AND Section = '%s' ORDER BY Skor DESC", school2, sinif2, section2 );
        
        try {
            s.execute( sqlQuery );
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                name2 = rs.getString("Name");
                skor2 = rs.getInt( "Skor" );
                
                studentNameList2.add( name2 );
                studentSkorList2.add( skor2 );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fillTableWithData2();
    }//GEN-LAST:event_showListButton2ActionPerformed

    private void schoolComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_schoolComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_schoolComboBox3ActionPerformed

    private void sinifComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinifComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sinifComboBox3ActionPerformed

    private void sectionComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectionComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sectionComboBox3ActionPerformed

    private void showListButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showListButton3ActionPerformed
        
        model3.setNumRows( 0 );
        studentNameList3.clear();
        studentSkorList3.clear();
        studentSchoolList3.clear();
        studentSinifList3.clear();
        studentSectionList3.clear();
        
        school3 = schoolComboBox3.getSelectedItem().toString();
        sinif3 = sinifComboBox3.getSelectedItem().toString();
        section3 = sectionComboBox3.getSelectedItem().toString();
        
        if( schoolComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
            if( sinifComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    sqlQuery = "SELECT * FROM Students ORDER BY Skor DESC";
                } else {
                    sqlQuery = String.format( "SELECT * FROM Students WHERE Section = '%s' ORDER BY Skor DESC", section3 );
                }
            } else {
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    sqlQuery = String.format( "SELECT * FROM Students WHERE Sinif = '%s' ORDER BY Skor DESC", sinif3 );
                } else {
                    sqlQuery = String.format( "SELECT * FROM Students WHERE Sinif = '%s' AND Section = '%s' ORDER BY Skor DESC", sinif3, section3 );
                }
            }
        } else {
            if( sinifComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' ORDER BY Skor DESC", school3 );
                } else {
                    sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Section = '%s' ORDER BY Skor DESC", school3, section3 );
                }
            } else {
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Sinif = '%s' ORDER BY Skor DESC", school3, sinif3 );
                } else {
                    sqlQuery = String.format( "SELECT * FROM Students WHERE School = '%s' AND Sinif = '%s' AND Section = '%s' ORDER BY Skor DESC", school3, sinif3, section3 );
                }
            }
        }
        
        try {
            s.execute( sqlQuery );
            ResultSet rs = s.getResultSet();
            while (rs.next()) {
                name3 = rs.getString("Name");
                skor3 = rs.getInt( "Skor" );
                school3 = rs.getString( "School" );
                sinif3 = rs.getString( "Sinif" );
                section3 = rs.getString( "Section" );
                               
                studentNameList3.add( name3 );
                studentSkorList3.add( skor3 );
                studentSchoolList3.add( school3 );
                studentSinifList3.add( sinif3 );
                studentSectionList3.add( section3 );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fillTableWithData3();
    }//GEN-LAST:event_showListButton3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void showGrafikButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGrafikButton1ActionPerformed
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for( int i = 0; ( i < (int) grafikSpinner1.getValue() ) && ( i < studentNameList3.size() ); i++ ){
            dataset.setValue( studentSkorList3.get( i ), "Skorlar", studentNameList3.get( i ) );
        }
        
        String grafikName = "SIRALAMA";
        if( schoolComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
            if( sinifComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    grafikName = "OKUL SIRALAMASI";
                } 
            } else {
                if( sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    grafikName = "TÜM " + sinifComboBox3.getSelectedItem() + ". SINIFLARIN SIRALAMASI";
                
                } 
            }
        } else {
            if( !sinifComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                if( !sectionComboBox3.getSelectedItem().toString().equals( "Hepsi" ) ){
                    grafikName = sinifComboBox3.getSelectedItem() + "-" + sectionComboBox3.getSelectedItem() + "  " + schoolComboBox3.getSelectedItem() + " SIRALAMASI";
                }
            }    
        }
        
        Font fontSkor = new Font( "Serif", Font.BOLD, 30 );
        Font fontName = new Font( "Serif", Font.BOLD, 18 );
        if( (int) grafikSpinner1.getValue() > 10 && (int) grafikSpinner1.getValue() <= 15 ){
            fontSkor = new Font( "Serif", Font.BOLD, 24 );
            fontName = new Font( "Serif", Font.BOLD, 17 );
        } else if( (int) grafikSpinner1.getValue() > 15 ){
            fontSkor = new Font( "Serif", Font.PLAIN, 21 );
            fontName = new Font( "Serif", Font.PLAIN, 15 );
        }
        
        JFreeChart chart = ChartFactory.createBarChart( grafikName, "", "", dataset, PlotOrientation.HORIZONTAL, false, false, false );
                
        /************/
        TextTitle texttitle = chart.getTitle();
        texttitle.setFont( new Font( "SansSerif", Font.BOLD, 30 ) );
        texttitle.setBorder(0.0D, 0.0D, 1.0D, 0.0D);
        texttitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.CYAN, 350F, 0.0F, Color.white, true));
        texttitle.setExpandToFitSpace(true);
 
        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.LIGHT_GRAY, 350F, 0.0F, Color.white, true));
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");
        categoryplot.setBackgroundPaint(null);
        categoryplot.setInsets(new RectangleInsets(10D, 5D, 5D, 5D));
        categoryplot.setOutlinePaint(Color.black);
        categoryplot.setRangeGridlinePaint(Color.gray);
        //categoryplot.setRangeGridlineStroke(new BasicStroke(1.0F));
        
        Paint apaint[] = createPaint();
        CustomBarRenderer custombarrenderer = new CustomBarRenderer(apaint); 
        custombarrenderer.setDrawBarOutline(true);
        custombarrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        custombarrenderer.setShadowVisible( false );
 
        custombarrenderer.setBaseItemLabelsVisible(true);
        custombarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        custombarrenderer.setBasePositiveItemLabelPosition( new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT ) );
        custombarrenderer.setBaseItemLabelFont( fontSkor );
        
        categoryplot.setRenderer(custombarrenderer);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       // numberaxis.setRange(0.0D, 800D);
        numberaxis.setTickMarkPaint(Color.black);
        numberaxis.setUpperMargin(0.2D);
        
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setTickLabelFont( fontName );
        categoryaxis.setCategoryMargin(0.17D);
        categoryaxis.setUpperMargin(0.03D);
        categoryaxis.setLowerMargin(0.03D);
        
        /************/
        
        ChartFrame frame = new ChartFrame( grafikName + " Grafik", chart );
        frame.setVisible( true );
        frame.setSize( 800, 600 );
        
    }//GEN-LAST:event_showGrafikButton1ActionPerformed

    private void sinifComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinifComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sinifComboBox4ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void showListButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showListButton4ActionPerformed
        model4.setNumRows( 0 );
        
        ArrayList<String> sectionList = new ArrayList<String>();
        
        if( sinifComboBox4.getSelectedItem().equals( "Hepsi" ) ){
            for( int a = 0; a < schoolList.length; a++ ){
                
                for( int i = 0; i < sinifList.length; i++ ){
                    
                    sectionList.clear();
                    
                    sqlQuery = String.format( "SELECT DISTINCT Section FROM Students WHERE School = '%s' AND Sinif = '%s'", schoolList[ a ], sinifList[ i ] );

                    try {
                        s.execute( sqlQuery );
                        ResultSet rs = s.getResultSet();
                        while (rs.next()) {

                            sectionList.add( rs.getString("Section") );

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    for( int s = 0; s < sectionList.size(); s++ ){
                        sinifWithSectionsWithSchools( schoolList[ a ], sinifList[ i ], sectionList.get( s ) );
                    }
                }
            }
            
        } else {
        
            for( int a = 0; a < schoolList.length; a++ ){
                sectionList.clear();
                
                sqlQuery = String.format( "SELECT DISTINCT Section FROM Students WHERE School = '%s' AND Sinif = '%s'", schoolList[ a ], sinifComboBox4.getSelectedItem() );

                try {
                    s.execute( sqlQuery );
                    ResultSet rs = s.getResultSet();
                    while (rs.next()) {
                        
                        sectionList.add( rs.getString("Section") );
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                for( int s = 0; s < sectionList.size(); s++ ){
                    sinifWithSectionsWithSchools( schoolList[ a ], sinifComboBox4.getSelectedItem().toString(), sectionList.get( s ) );
                }
            }
        }
       
        fillTableWithData4();
    }//GEN-LAST:event_showListButton4ActionPerformed

    private void showGrafikButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGrafikButton3ActionPerformed
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for( int i = 0; i < sinifWithSectionsList.length; i++ ){
            dataset.setValue( Integer.parseInt( sinifWithSectionsList[ i ][ 1 ] ), "Skorlar", sinifWithSectionsList[ i ][ 0 ] );
        }
        
        JFreeChart chart = ChartFactory.createBarChart( "TÜM OKUL SKOR", "", "", dataset, PlotOrientation.VERTICAL, false, true, false );
        
        chart.addSubtitle(new TextTitle("Mevcut Toplam: " + tümOkulToplamSkorLabel.getText(), new Font("Dialog", 2, 24)));
         
        /************/
        TextTitle texttitle = chart.getTitle();
        texttitle.setFont( new Font( "SansSerif", Font.BOLD, 30 ) );
        texttitle.setBorder(0.0D, 0.0D, 1.0D, 0.0D);
        texttitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.CYAN, 350F, 0.0F, Color.white, true));
        texttitle.setExpandToFitSpace(true);
 
        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.LIGHT_GRAY, 350F, 0.0F, Color.white, true));
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");
        categoryplot.setBackgroundPaint(null);
        categoryplot.setInsets(new RectangleInsets(10D, 5D, 5D, 5D));
        categoryplot.setOutlinePaint(Color.black);
        categoryplot.setRangeGridlinePaint(Color.gray);
        //categoryplot.setRangeGridlineStroke(new BasicStroke(1.0F));
        
        Paint apaint[] = createPaint();
        CustomBarRenderer custombarrenderer = new CustomBarRenderer(apaint); 
        custombarrenderer.setDrawBarOutline(true);
        custombarrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        custombarrenderer.setShadowVisible( false );
 
        custombarrenderer.setBaseItemLabelsVisible(true);
        custombarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        custombarrenderer.setBasePositiveItemLabelPosition( new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER ) );
        custombarrenderer.setBaseItemLabelFont( new Font( "Serif", Font.BOLD, 30 ) );
        
        categoryplot.setRenderer(custombarrenderer);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       // numberaxis.setRange(0.0D, 800D);
        numberaxis.setTickMarkPaint(Color.black);
        numberaxis.setUpperMargin(0.2D);
        
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setTickLabelFont( new Font( "Serif", Font.BOLD, 30 ) );
        categoryaxis.setCategoryMargin(0.17D);
        categoryaxis.setUpperMargin(0.03D);
        categoryaxis.setLowerMargin(0.03D);
        
        ChartFrame frame = new ChartFrame( "Tüm Okul Skor Grafik", chart );
        frame.setVisible( true );
        frame.setSize( 800, 600 );
    }//GEN-LAST:event_showGrafikButton3ActionPerformed

    private void yenileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yenileButtonActionPerformed
        fillTableWithData5();
    }//GEN-LAST:event_yenileButtonActionPerformed

    private void showGrafikButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGrafikButton2ActionPerformed
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for( int i = 0; ( i < (int) grafikSpinner2.getValue() ) && ( i < sinifWithSkorsList.length ); i++ ){
            dataset.setValue( Integer.parseInt( sinifWithSkorsList[ i ][ 1 ] ), "Skorlar", sinifWithSkorsList[ i ][ 0 ] );
        }
        
        String grafikName = "SIRALAMA";
        if( sinifComboBox4.getSelectedItem().toString().equals( "Hepsi" ) ){
            grafikName = "TÜM SINIFLAR";
        
        } else {
            grafikName = "TÜM " + sinifComboBox4.getSelectedItem() + ". SINIFLAR";
        }
            
        Font fontSkor = new Font( "Serif", Font.BOLD, 30 );
        Font fontName = new Font( "Serif", Font.BOLD, 24 );
        if( (int) grafikSpinner2.getValue() > 10 ){
            fontSkor = new Font( "Serif", Font.PLAIN, 24 );
            fontName = new Font( "Serif", Font.PLAIN, 20 );
        }
        
        JFreeChart chart = ChartFactory.createBarChart( grafikName, "", "", dataset, PlotOrientation.HORIZONTAL, false, true, false );
                 
        /************/
        TextTitle texttitle = chart.getTitle();
        texttitle.setFont( new Font( "SansSerif", Font.BOLD, 30 ) );
        texttitle.setBorder(0.0D, 0.0D, 1.0D, 0.0D);
        texttitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.CYAN, 350F, 0.0F, Color.white, true));
        texttitle.setExpandToFitSpace(true);
 
        chart.setBackgroundPaint(new GradientPaint(0.0F, 0.0F, Color.LIGHT_GRAY, 350F, 0.0F, Color.white, true));
        CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
        categoryplot.setNoDataMessage("NO DATA!");
        categoryplot.setBackgroundPaint(null);
        categoryplot.setInsets(new RectangleInsets(10D, 5D, 5D, 5D));
        categoryplot.setOutlinePaint(Color.black);
        categoryplot.setRangeGridlinePaint(Color.gray);
        //categoryplot.setRangeGridlineStroke(new BasicStroke(1.0F));
        
        Paint apaint[] = createPaint();
        CustomBarRenderer custombarrenderer = new CustomBarRenderer(apaint); 
        custombarrenderer.setDrawBarOutline(true);
        custombarrenderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
        custombarrenderer.setShadowVisible( false );
 
        custombarrenderer.setBaseItemLabelsVisible(true);
        custombarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator()); 
        custombarrenderer.setBasePositiveItemLabelPosition( new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT ) );
        custombarrenderer.setBaseItemLabelFont( fontSkor );
        
        categoryplot.setRenderer(custombarrenderer);
        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       // numberaxis.setRange(0.0D, 800D);
        numberaxis.setTickMarkPaint(Color.black);
        numberaxis.setUpperMargin(0.2D);
        
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setTickLabelFont( fontName );
        categoryaxis.setCategoryMargin(0.17D);
        categoryaxis.setUpperMargin(0.03D);
        categoryaxis.setLowerMargin(0.03D);
        
        ChartFrame frame = new ChartFrame( grafikName + " Grafik", chart );
        frame.setVisible( true );
        frame.setSize( 800, 600 );
    }//GEN-LAST:event_showGrafikButton2ActionPerformed

    private void skorChangeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skorChangeButtonActionPerformed
        if( jTable2.getSelectedRow() == -1 ){
            if( jTable2.getRowCount() == 0 ){
                JOptionPane.showMessageDialog( this, "Tablo şu an boş!" );
            } else {
                JOptionPane.showMessageDialog( this, "Tablodan bir öğrenci seçiniz!" );
            }
        } else {
            try {
                
                name2 = model2.getValueAt( jTable2.getSelectedRow(), 1 ).toString();
                skor2 = Integer.parseInt( model2.getValueAt( jTable2.getSelectedRow(), 2 ).toString() );
                
                skor2 = Integer.parseInt( skorChangeTextField.getText() );
                
                sqlQuery = String.format( "UPDATE Students SET Skor = %d WHERE Name = '%s' AND School = '%s' AND Sinif = '%s' AND Section = '%s'", 
                                        skor2, name2, schoolComboBox2.getSelectedItem(), sinifComboBox2.getSelectedItem(), sectionComboBox2.getSelectedItem() );
                s.execute( sqlQuery );
                
                model2.setValueAt( skor2, jTable2.getSelectedRow(), 2 );
                
                fillTableWithData5();
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog( this, "Tam sayı giriniz!" );
            
            } catch (SQLException ex) {
                Logger.getLogger(AnaPencere.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_skorChangeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AnaPencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnaPencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnaPencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnaPencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnaPencere().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudentButton;
    private javax.swing.JLabel changeSkorLabel;
    private javax.swing.JButton deleteStudentButton;
    private javax.swing.JLabel eklenecekSkorLabel;
    private javax.swing.JLabel grafikLabel1;
    private javax.swing.JLabel grafikLabel2;
    private javax.swing.JSpinner grafikSpinner1;
    private javax.swing.JSpinner grafikSpinner2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel okulLabel1;
    private javax.swing.JLabel okulLabel2;
    private javax.swing.JLabel okulLabel3;
    private javax.swing.JComboBox schoolComboBox1;
    private javax.swing.JComboBox schoolComboBox2;
    private javax.swing.JComboBox schoolComboBox3;
    private javax.swing.JComboBox sectionComboBox1;
    private javax.swing.JComboBox sectionComboBox2;
    private javax.swing.JComboBox sectionComboBox3;
    private javax.swing.JButton showGrafikButton1;
    private javax.swing.JButton showGrafikButton2;
    private javax.swing.JButton showGrafikButton3;
    private javax.swing.JButton showListButton1;
    private javax.swing.JButton showListButton2;
    private javax.swing.JButton showListButton3;
    private javax.swing.JButton showListButton4;
    private javax.swing.JComboBox sinifComboBox1;
    private javax.swing.JComboBox sinifComboBox2;
    private javax.swing.JComboBox sinifComboBox3;
    private javax.swing.JComboBox sinifComboBox4;
    private javax.swing.JLabel sinifLabel1;
    private javax.swing.JLabel sinifLabel2;
    private javax.swing.JLabel sinifLabel3;
    private javax.swing.JLabel sinifLabel4;
    private javax.swing.JButton skorChangeButton;
    private javax.swing.JTextField skorChangeTextField;
    private javax.swing.JButton skorEkleButton;
    private javax.swing.JTextField skorTextField;
    private javax.swing.JLabel studentNameLabel;
    private javax.swing.JTextField studentNameTextField;
    private javax.swing.JLabel subeLabel1;
    private javax.swing.JLabel subeLabel2;
    private javax.swing.JLabel subeLabel3;
    private javax.swing.JLabel tümOkulKalanMiktarLabel;
    private javax.swing.JLabel tümOkulToplamSkorLabel;
    private javax.swing.JButton yenileButton;
    // End of variables declaration//GEN-END:variables
}
