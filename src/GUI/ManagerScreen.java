package GUI;

import Database.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Osusara
 */
public class ManagerScreen extends javax.swing.JFrame {

    public ManagerScreen() {
        initComponents();
        userLoad();
        dateTimeLoad();
        loadID();
        userListLoad();
        analyticsDataLoad();
        stockDataLoad();
        supplierDataLoad();

        confirmationPasswordField.setEchoChar((char) 0);
        confirmationPasswordField.setForeground(Color.gray);
        searchInUMTextField.setForeground(Color.gray);
        itemSearchTextField.setForeground(Color.gray);
        supplierSearchTextField.setForeground(Color.gray);
        itemSearchInKitchenTextField.setForeground(Color.gray);
        
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        grnDateTextField.setText(date);
        kitchenDateTextField.setText(date);
    }

    public void loadID() {
        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('order_id') AS C FROM `restaurentsystem`.`order`");
            ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('customer_id') AS D FROM `restaurentsystem`.`customer`");
            ResultSet rs3 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('item_id') AS E FROM `restaurentsystem`.`item`");
            ResultSet rs4 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('supplier_id') AS F FROM `restaurentsystem`.`supplier`");
            ResultSet rs5 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('grn_id') AS G FROM `restaurentsystem`.`grn`");
            ResultSet rs6 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('record_id') AS H FROM `restaurentsystem`.`kitchen_records`");
            if (rs1.next()) {
                int i = rs1.getInt("C");
                oidLabel.setText("" + (++i));
            }
            if (rs2.next()) {
                int i = rs2.getInt("D");
                cidLabel.setText("" + (++i));
            }
            if (rs3.next()) {
                int i = rs3.getInt("E");
                itemIDTextField.setText("" + (++i));
            }
            if (rs4.next()) {
                int i = rs4.getInt("F");
                newSupplierIDTextField.setText("" + (++i));
            }
            if (rs5.next()) {
                int i = rs5.getInt("G");
                grnidLabel.setText("" + (++i));
            }
            
            if(rs6.next()){
                int i = rs6.getInt("H");
                kridLabel.setText("" + (++i));
            }

            rs1.close();
            rs2.close();
            rs3.close();
            rs4.close();
            rs5.close();
            rs6.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int useridLoad() {
        String username = new UserLogin().usernameSender();
        int uid = 0;
        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE username = '" + username + "'");
            if (rs1.first()) {
                uid = Integer.parseInt(rs1.getString("user_id"));
            }
        } catch (Exception ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return uid;
    }

    public void userLoad() {
        String username = new UserLogin().usernameSender();
        int uid = 0;
        String name = "";

        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE username = '" + username + "'");
            if (rs1.first()) {
                uid = Integer.parseInt(rs1.getString("user_id"));
            }

            ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM user WHERE user_id = '" + uid + "'");
            if (rs2.first()) {
                name = rs2.getString("name");
            }

            String nameA[] = name.split(" ");

            hiLabel.setText("Hi, " + nameA[0]);
        } catch (Exception ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void userListLoad() {
        DefaultListModel dlm = new DefaultListModel();
        String type = null;
        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM user;");
            while (rs.next()) {
                String uid = rs.getString("user_id");
                String userName = rs.getString("name");
                String listItem = uid + " | " + userName;
                dlm.addElement(listItem);
            }
            userList.setModel(dlm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void dateTimeLoad() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        Date date = new Date();
        dateLabel.setText("Today is " + formatter.format(date));
    }

    public void reset() {
        cheeseBurger.setSelected(false);
        chickenBurger.setSelected(false);
        beefBurger.setSelected(false);
        vegSubmarine.setSelected(false);
        nonvegSubmarine.setSelected(false);
        frenchFries.setSelected(false);
        chickenNuggets.setSelected(false);

        cocacolaS.setSelected(false);
        cocacolaL.setSelected(false);
        spriteS.setSelected(false);
        spriteL.setSelected(false);
        milkShake.setSelected(false);
        hotChocolate.setSelected(false);
        iceCoffee.setSelected(false);

        vanilaCone.setSelected(false);
        chocolateCone.setSelected(false);
        strawberryCone.setSelected(false);
        vanilaSundaes.setSelected(false);
        chocolateSundaes.setSelected(false);
        lavaCake.setSelected(false);

        cheeseBurgerCount.setText(null);
        cheeseBurgerCount.setEnabled(false);
        chickenBurgerCount.setText(null);
        chickenBurgerCount.setEnabled(false);
        beefBurgerCount.setText(null);
        beefBurgerCount.setEnabled(false);
        vegSubmarineCount.setText(null);
        vegSubmarineCount.setEnabled(false);
        nonvegSubmarineCount.setText(null);
        nonvegSubmarineCount.setEnabled(false);
        frenchFriesCount.setText(null);
        frenchFriesCount.setEnabled(false);
        chickenNuggetsCount.setText(null);
        chickenNuggetsCount.setEnabled(false);

        cocacolaSCount.setText(null);
        cocacolaSCount.setEnabled(false);
        cocacolaLCount.setText(null);
        cocacolaLCount.setEnabled(false);
        spriteSCount.setText(null);
        spriteSCount.setEnabled(false);
        spriteLCount.setText(null);
        spriteLCount.setEnabled(false);
        milkShakeCount.setText(null);
        milkShakeCount.setEnabled(false);
        hotChocolateCount.setText(null);
        hotChocolateCount.setEnabled(false);
        iceCoffeeCount.setText(null);
        iceCoffeeCount.setEnabled(false);

        vanilaConeCount.setText(null);
        vanilaConeCount.setEnabled(false);
        chocolateConeCount.setText(null);
        chocolateConeCount.setEnabled(false);
        strawberryConeCount.setText(null);
        strawberryConeCount.setEnabled(false);
        vanilaSundaesCount.setText(null);
        vanilaSundaesCount.setEnabled(false);
        chocolateSundaesCount.setText(null);
        chocolateSundaesCount.setEnabled(false);
        lavaCakeCount.setText(null);
        lavaCakeCount.setEnabled(false);

        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        dtm.setRowCount(0);
        serviceChargesTextField.setText(null);
        totalTextField.setText(null);

        totalTextField.setText(null);
        recievedTextField.setText(null);
        serviceChargesTextField.setText(null);
        balanceTextField.setText(null);

        nameTextField.setText(null);
        phoneTextField.setText(null);
        emailTextField.setText(null);
        
        grnItemTextField.setText(null);
        grnSupplierTextField.setText(null);
        supplierIDTextField.setText(null);
        grnUnitPriceTextField.setText(null);
        grnTotalTextField.setText(null);
        grnQtyTextField.setText(null);
        newSupplierTextField.setText(null);
        grnSupplierPhoneTextField.setText(null);
        grnSupplierEmailTextField.setText(null);
    }

    public static int getResultSetRowCount(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
            return 0;
        }
        return size;
    }

    public void analyticsDataLoad() {
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` ORDER BY order_id DESC");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                dtm.addRow(v);
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stockDataLoad() {
        DefaultTableModel dtm1 = (DefaultTableModel) stockTable.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) stockInKitchenTable.getModel();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM item");
            dtm1.setRowCount(0);
            dtm2.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("item_id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("qty"));
                v.add(rs.getString("unit_price"));
                dtm1.addRow(v);
                dtm2.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void supplierDataLoad() {
        DefaultTableModel dtm = (DefaultTableModel) supplierTable.getModel();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM supplier");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("supplier_id"));
                v.add(rs.getString("name"));
                dtm.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void reportGen() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(dtm);
            String reportSource = "D:\\Osusara\\Documents\\Github Projects\\FastFood_Ordering_System\\src\\Resources\\FoodOrderingReport.jrxml";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("oid", oidLabel.getText());

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            params.put("date", formatter.format(date));
            params.put("name", nameTextField.getText());
            params.put("charges", serviceChargesTextField.getText());
            params.put("total", totalTextField.getText());
            params.put("payment", recievedTextField.getText());
            params.put("balance", balanceTextField.getText());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, dataSource);
            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        hiLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        navPanel = new javax.swing.JTabbedPane();
        makeOrdersPanel = new javax.swing.JPanel();
        mealsPanel = new javax.swing.JPanel();
        casualMealsPanel = new javax.swing.JPanel();
        beefBurger = new javax.swing.JCheckBox();
        cheeseBurger = new javax.swing.JCheckBox();
        chickenBurger = new javax.swing.JCheckBox();
        vegSubmarine = new javax.swing.JCheckBox();
        nonvegSubmarine = new javax.swing.JCheckBox();
        cheeseBurgerCount = new javax.swing.JTextField();
        chickenBurgerCount = new javax.swing.JTextField();
        beefBurgerCount = new javax.swing.JTextField();
        vegSubmarineCount = new javax.swing.JTextField();
        nonvegSubmarineCount = new javax.swing.JTextField();
        frenchFries = new javax.swing.JCheckBox();
        chickenNuggets = new javax.swing.JCheckBox();
        frenchFriesCount = new javax.swing.JTextField();
        chickenNuggetsCount = new javax.swing.JTextField();
        casualMealsLabel = new javax.swing.JLabel();
        drinksPanel = new javax.swing.JPanel();
        cocacolaS = new javax.swing.JCheckBox();
        cocacolaL = new javax.swing.JCheckBox();
        spriteS = new javax.swing.JCheckBox();
        spriteL = new javax.swing.JCheckBox();
        milkShake = new javax.swing.JCheckBox();
        hotChocolate = new javax.swing.JCheckBox();
        iceCoffee = new javax.swing.JCheckBox();
        cocacolaSCount = new javax.swing.JTextField();
        cocacolaLCount = new javax.swing.JTextField();
        spriteSCount = new javax.swing.JTextField();
        spriteLCount = new javax.swing.JTextField();
        milkShakeCount = new javax.swing.JTextField();
        hotChocolateCount = new javax.swing.JTextField();
        iceCoffeeCount = new javax.swing.JTextField();
        drinksLabel = new javax.swing.JLabel();
        dessertPanel = new javax.swing.JPanel();
        vanilaCone = new javax.swing.JCheckBox();
        chocolateCone = new javax.swing.JCheckBox();
        strawberryCone = new javax.swing.JCheckBox();
        vanilaSundaes = new javax.swing.JCheckBox();
        chocolateSundaes = new javax.swing.JCheckBox();
        lavaCake = new javax.swing.JCheckBox();
        vanilaSundaesCount = new javax.swing.JTextField();
        chocolateSundaesCount = new javax.swing.JTextField();
        lavaCakeCount = new javax.swing.JTextField();
        vanilaConeCount = new javax.swing.JTextField();
        chocolateConeCount = new javax.swing.JTextField();
        strawberryConeCount = new javax.swing.JTextField();
        dessertLabel = new javax.swing.JLabel();
        mealsTabelPanel = new javax.swing.JScrollPane();
        mealsTable = new javax.swing.JTable();
        serviceChargesTextField = new javax.swing.JTextField();
        totalTextField = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        serviceChargesLabel = new javax.swing.JLabel();
        proceedButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        orderNoLabel = new javax.swing.JLabel();
        oidLabel = new javax.swing.JLabel();
        recievedTextField = new javax.swing.JTextField();
        balanceTextField = new javax.swing.JTextField();
        recievedLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        getCustomerDetailsPanel = new javax.swing.JPanel();
        emailTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        customerNameLabel = new javax.swing.JLabel();
        customerIDLabel = new javax.swing.JLabel();
        cidLabel = new javax.swing.JLabel();
        orderNoLabel1 = new javax.swing.JLabel();
        analyticsPanel = new javax.swing.JPanel();
        ordersTableLabel = new javax.swing.JLabel();
        orderDetailsLabel = new javax.swing.JLabel();
        ordersTablePanel = new javax.swing.JPanel();
        ordersTabelPanel1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        refereshOrderDataButton = new javax.swing.JButton();
        filterOrdersPanel = new javax.swing.JPanel();
        filterByCustomerLabel = new javax.swing.JLabel();
        filterByCashierLabel = new javax.swing.JLabel();
        filterByOrderLabel = new javax.swing.JLabel();
        filterByDateLabel = new javax.swing.JLabel();
        orderByOIDTextField = new javax.swing.JTextField();
        orderByUIDTextField = new javax.swing.JTextField();
        orderByCIDTextField = new javax.swing.JTextField();
        filterByPHTLabel = new javax.swing.JLabel();
        filterByPLTLabel = new javax.swing.JLabel();
        ordreByPHTTextField = new javax.swing.JTextField();
        ordreByPLTTextField = new javax.swing.JTextField();
        orderByDateTextField = new javax.swing.JTextField();
        ordersTableLabel1 = new javax.swing.JLabel();
        totalProfitTextField = new javax.swing.JTextField();
        totalProfitLabel = new javax.swing.JLabel();
        orderDetailsPanel = new javax.swing.JScrollPane();
        orderDetailsList = new javax.swing.JList<>();
        grnPanel = new javax.swing.JPanel();
        grnMainPanel = new javax.swing.JPanel();
        addItemButton1 = new javax.swing.JButton();
        grnItemReceiveLabel3 = new javax.swing.JLabel();
        stockDetailsPanel1 = new javax.swing.JPanel();
        stockTabelPanel1 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        supplierSearchTextField = new javax.swing.JTextField();
        supplierRefreshButton = new javax.swing.JButton();
        grnItemReceiveLabel2 = new javax.swing.JLabel();
        supplierDetailsPanel = new javax.swing.JPanel();
        grnSupplierIDLabel = new javax.swing.JLabel();
        newSupplierIDTextField = new javax.swing.JTextField();
        grnSupplierLabel = new javax.swing.JLabel();
        newSupplierTextField = new javax.swing.JTextField();
        grnSupplierPhoneLabel = new javax.swing.JLabel();
        grnSupplierPhoneTextField = new javax.swing.JTextField();
        grnSupplierEmailLabel = new javax.swing.JLabel();
        grnSupplierEmailTextField = new javax.swing.JTextField();
        addSupplierButton = new javax.swing.JButton();
        updateSupplierButton = new javax.swing.JButton();
        stockTableLabel1 = new javax.swing.JLabel();
        receiveItemsPanel = new javax.swing.JPanel();
        addNewItemButton = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        grnNoLabel = new javax.swing.JLabel();
        grnidLabel = new javax.swing.JLabel();
        grnTotalTextField = new javax.swing.JTextField();
        grnTotalLabel = new javax.swing.JLabel();
        grnQtyTextField = new javax.swing.JTextField();
        grnQtyLabel = new javax.swing.JLabel();
        grnUnitPriceLabel = new javax.swing.JLabel();
        grnUnitPriceTextField = new javax.swing.JTextField();
        grnItemTextField = new javax.swing.JTextField();
        grnItemLabel = new javax.swing.JLabel();
        itemIDTextField = new javax.swing.JTextField();
        grnItemIDLabel = new javax.swing.JLabel();
        grnDateTextField = new javax.swing.JTextField();
        grnDateLabel = new javax.swing.JLabel();
        grnSupplierIDLabel1 = new javax.swing.JLabel();
        supplierIDTextField = new javax.swing.JTextField();
        grnSupplierTextField = new javax.swing.JTextField();
        grnSupplierLabel1 = new javax.swing.JLabel();
        grnExpLabel = new javax.swing.JLabel();
        grnExpTextField = new javax.swing.JTextField();
        stockDetailsPanel = new javax.swing.JPanel();
        stockTabelPanel = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        itemSearchTextField = new javax.swing.JTextField();
        stockRefreshButton = new javax.swing.JButton();
        stockTableLabel = new javax.swing.JLabel();
        kitchenManagementPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        kitchenItemIDLabel = new javax.swing.JLabel();
        kitchenItemIDTextField = new javax.swing.JTextField();
        kitchenItemLabel = new javax.swing.JLabel();
        kitchenItemTextField = new javax.swing.JTextField();
        recordIDLabel = new javax.swing.JLabel();
        kridLabel = new javax.swing.JLabel();
        kitchenDateLabel = new javax.swing.JLabel();
        kitchenDateTextField = new javax.swing.JTextField();
        kitchenQtyLabel = new javax.swing.JLabel();
        kitchenQtyTextField = new javax.swing.JTextField();
        getItemButton = new javax.swing.JButton();
        stockDetailsPanel2 = new javax.swing.JPanel();
        stockTabelPanel2 = new javax.swing.JScrollPane();
        stockInKitchenTable = new javax.swing.JTable();
        itemSearchInKitchenTextField = new javax.swing.JTextField();
        stockRefreshInKitchenButton = new javax.swing.JButton();
        stockTableLabel2 = new javax.swing.JLabel();
        stockTableLabel3 = new javax.swing.JLabel();
        userManagementPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        userDetailsLabel = new javax.swing.JLabel();
        userRefreshButton = new javax.swing.JButton();
        searchInUMTextField = new javax.swing.JTextField();
        controlPanel = new javax.swing.JPanel();
        confirmationPasswordField = new javax.swing.JPasswordField();
        saveUserButton = new javax.swing.JButton();
        addUserButton = new javax.swing.JButton();
        userListLabel1 = new javax.swing.JLabel();
        userListLabel = new javax.swing.JLabel();
        userDetailsPanel = new javax.swing.JPanel();
        dobInUMLabel = new javax.swing.JLabel();
        addressInUMLabel = new javax.swing.JLabel();
        emailInUMLabel = new javax.swing.JLabel();
        phoneInUMLabel = new javax.swing.JLabel();
        genderInUMLabel = new javax.swing.JLabel();
        nameInUMLabel = new javax.swing.JLabel();
        addressInUMTextField = new javax.swing.JTextField();
        emailInUMTextField = new javax.swing.JTextField();
        phoneInUMTextField = new javax.swing.JTextField();
        dobInUMTextField = new javax.swing.JTextField();
        genderInUMTextField = new javax.swing.JTextField();
        nameInUMTextField = new javax.swing.JTextField();
        usernameInUMTextField = new javax.swing.JTextField();
        orderNoLabel2 = new javax.swing.JLabel();
        uidInUMLabel = new javax.swing.JLabel();
        usernameInUMLabel = new javax.swing.JLabel();
        inUMPasswordField = new javax.swing.JPasswordField();
        passwordInUMLabel = new javax.swing.JLabel();
        inUMConfirmPasswordField = new javax.swing.JPasswordField();
        passwordInUMLabel1 = new javax.swing.JLabel();
        managementInUMCheckBox = new javax.swing.JCheckBox();
        editInUMToggleButton = new javax.swing.JToggleButton();
        activeInUMCheckBox = new javax.swing.JCheckBox();
        userListScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurent Orders Management System | Home");
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(1366, 768));

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        sidePanel.setBackground(new java.awt.Color(102, 153, 255));

        hiLabel.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        hiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hiLabel.setText("User's Name");

        logoutButton.setBackground(new java.awt.Color(255, 50, 30));
        logoutButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/shutdown.png"))); // NOI18N
        logoutButton.setText("Log Out");
        logoutButton.setBorderPainted(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/users.png"))); // NOI18N

        dateLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("Date");

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hiLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(logoutButton)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(hiLabel)
                .addGap(2, 2, 2)
                .addComponent(dateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(47, 47, 47))
        );

        navPanel.setBackground(new java.awt.Color(255, 255, 255));
        navPanel.setForeground(new java.awt.Color(51, 51, 51));
        navPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        makeOrdersPanel.setBackground(new java.awt.Color(255, 255, 255));
        makeOrdersPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        makeOrdersPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        mealsPanel.setBackground(new java.awt.Color(255, 255, 255));

        casualMealsPanel.setBackground(new java.awt.Color(255, 255, 255));
        casualMealsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        casualMealsPanel.setPreferredSize(new java.awt.Dimension(190, 260));
        casualMealsPanel.setRequestFocusEnabled(false);

        beefBurger.setBackground(new java.awt.Color(255, 255, 255));
        beefBurger.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        beefBurger.setText("Beef Burger");
        beefBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beefBurgerActionPerformed(evt);
            }
        });

        cheeseBurger.setBackground(new java.awt.Color(255, 255, 255));
        cheeseBurger.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cheeseBurger.setText("Cheese Burger");
        cheeseBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheeseBurgerActionPerformed(evt);
            }
        });

        chickenBurger.setBackground(new java.awt.Color(255, 255, 255));
        chickenBurger.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chickenBurger.setText("Chicken Burger");
        chickenBurger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chickenBurgerActionPerformed(evt);
            }
        });

        vegSubmarine.setBackground(new java.awt.Color(255, 255, 255));
        vegSubmarine.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vegSubmarine.setText("Veg Submarine");
        vegSubmarine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vegSubmarineActionPerformed(evt);
            }
        });

        nonvegSubmarine.setBackground(new java.awt.Color(255, 255, 255));
        nonvegSubmarine.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nonvegSubmarine.setText("Non-veg Submarine");
        nonvegSubmarine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonvegSubmarineActionPerformed(evt);
            }
        });

        cheeseBurgerCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cheeseBurgerCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cheeseBurgerCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        cheeseBurgerCount.setEnabled(false);
        cheeseBurgerCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        cheeseBurgerCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cheeseBurgerCountFocusLost(evt);
            }
        });
        cheeseBurgerCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheeseBurgerCountActionPerformed(evt);
            }
        });
        cheeseBurgerCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cheeseBurgerCountKeyTyped(evt);
            }
        });

        chickenBurgerCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chickenBurgerCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chickenBurgerCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chickenBurgerCount.setEnabled(false);
        chickenBurgerCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        chickenBurgerCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chickenBurgerCountFocusLost(evt);
            }
        });
        chickenBurgerCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chickenBurgerCountActionPerformed(evt);
            }
        });
        chickenBurgerCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chickenBurgerCountKeyTyped(evt);
            }
        });

        beefBurgerCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        beefBurgerCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        beefBurgerCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        beefBurgerCount.setEnabled(false);
        beefBurgerCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        beefBurgerCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                beefBurgerCountFocusLost(evt);
            }
        });
        beefBurgerCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beefBurgerCountActionPerformed(evt);
            }
        });
        beefBurgerCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                beefBurgerCountKeyTyped(evt);
            }
        });

        vegSubmarineCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vegSubmarineCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vegSubmarineCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        vegSubmarineCount.setEnabled(false);
        vegSubmarineCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        vegSubmarineCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vegSubmarineCountFocusLost(evt);
            }
        });
        vegSubmarineCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vegSubmarineCountActionPerformed(evt);
            }
        });
        vegSubmarineCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vegSubmarineCountKeyTyped(evt);
            }
        });

        nonvegSubmarineCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nonvegSubmarineCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nonvegSubmarineCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nonvegSubmarineCount.setEnabled(false);
        nonvegSubmarineCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        nonvegSubmarineCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nonvegSubmarineCountFocusLost(evt);
            }
        });
        nonvegSubmarineCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nonvegSubmarineCountActionPerformed(evt);
            }
        });
        nonvegSubmarineCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nonvegSubmarineCountKeyTyped(evt);
            }
        });

        frenchFries.setBackground(new java.awt.Color(255, 255, 255));
        frenchFries.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        frenchFries.setText("French Fries");
        frenchFries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frenchFriesActionPerformed(evt);
            }
        });

        chickenNuggets.setBackground(new java.awt.Color(255, 255, 255));
        chickenNuggets.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chickenNuggets.setText("Chicken Nuggets");
        chickenNuggets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chickenNuggetsActionPerformed(evt);
            }
        });

        frenchFriesCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        frenchFriesCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        frenchFriesCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        frenchFriesCount.setEnabled(false);
        frenchFriesCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        frenchFriesCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                frenchFriesCountFocusLost(evt);
            }
        });
        frenchFriesCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frenchFriesCountActionPerformed(evt);
            }
        });
        frenchFriesCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                frenchFriesCountKeyTyped(evt);
            }
        });

        chickenNuggetsCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chickenNuggetsCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chickenNuggetsCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chickenNuggetsCount.setEnabled(false);
        chickenNuggetsCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        chickenNuggetsCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chickenNuggetsCountFocusLost(evt);
            }
        });
        chickenNuggetsCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chickenNuggetsCountActionPerformed(evt);
            }
        });
        chickenNuggetsCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chickenNuggetsCountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout casualMealsPanelLayout = new javax.swing.GroupLayout(casualMealsPanel);
        casualMealsPanel.setLayout(casualMealsPanelLayout);
        casualMealsPanelLayout.setHorizontalGroup(
            casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casualMealsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(casualMealsPanelLayout.createSequentialGroup()
                        .addComponent(chickenNuggets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chickenNuggetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(casualMealsPanelLayout.createSequentialGroup()
                        .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nonvegSubmarine)
                            .addComponent(vegSubmarine)
                            .addComponent(chickenBurger)
                            .addComponent(cheeseBurger)
                            .addComponent(beefBurger))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cheeseBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chickenBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(beefBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vegSubmarineCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nonvegSubmarineCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(casualMealsPanelLayout.createSequentialGroup()
                        .addComponent(frenchFries)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(frenchFriesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        casualMealsPanelLayout.setVerticalGroup(
            casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(casualMealsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cheeseBurger)
                    .addComponent(cheeseBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chickenBurger)
                    .addComponent(chickenBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(beefBurger)
                    .addComponent(beefBurgerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vegSubmarine)
                    .addComponent(vegSubmarineCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nonvegSubmarine)
                    .addComponent(nonvegSubmarineCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frenchFries)
                    .addComponent(frenchFriesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(casualMealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chickenNuggets)
                    .addComponent(chickenNuggetsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        casualMealsLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        casualMealsLabel.setText("Casual Meals");

        drinksPanel.setBackground(new java.awt.Color(255, 255, 255));
        drinksPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        cocacolaS.setBackground(new java.awt.Color(255, 255, 255));
        cocacolaS.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cocacolaS.setText("Coca-cola (S)");
        cocacolaS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cocacolaSActionPerformed(evt);
            }
        });

        cocacolaL.setBackground(new java.awt.Color(255, 255, 255));
        cocacolaL.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cocacolaL.setText("Coca-cola (L)");
        cocacolaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cocacolaLActionPerformed(evt);
            }
        });

        spriteS.setBackground(new java.awt.Color(255, 255, 255));
        spriteS.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spriteS.setText("Sprite (S)");
        spriteS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteSActionPerformed(evt);
            }
        });

        spriteL.setBackground(new java.awt.Color(255, 255, 255));
        spriteL.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spriteL.setText("Sprite (L)");
        spriteL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteLActionPerformed(evt);
            }
        });

        milkShake.setBackground(new java.awt.Color(255, 255, 255));
        milkShake.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        milkShake.setText("Milk Shake");
        milkShake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkShakeActionPerformed(evt);
            }
        });

        hotChocolate.setBackground(new java.awt.Color(255, 255, 255));
        hotChocolate.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        hotChocolate.setText("Hot Chocolate");
        hotChocolate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotChocolateActionPerformed(evt);
            }
        });

        iceCoffee.setBackground(new java.awt.Color(255, 255, 255));
        iceCoffee.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        iceCoffee.setText("Ice Coffee");
        iceCoffee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iceCoffeeActionPerformed(evt);
            }
        });

        cocacolaSCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cocacolaSCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cocacolaSCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        cocacolaSCount.setEnabled(false);
        cocacolaSCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        cocacolaSCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cocacolaSCountFocusLost(evt);
            }
        });
        cocacolaSCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cocacolaSCountActionPerformed(evt);
            }
        });
        cocacolaSCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cocacolaSCountKeyTyped(evt);
            }
        });

        cocacolaLCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cocacolaLCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cocacolaLCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        cocacolaLCount.setEnabled(false);
        cocacolaLCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        cocacolaLCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cocacolaLCountFocusLost(evt);
            }
        });
        cocacolaLCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cocacolaLCountActionPerformed(evt);
            }
        });
        cocacolaLCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cocacolaLCountKeyTyped(evt);
            }
        });

        spriteSCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spriteSCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spriteSCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        spriteSCount.setEnabled(false);
        spriteSCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        spriteSCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spriteSCountFocusLost(evt);
            }
        });
        spriteSCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteSCountActionPerformed(evt);
            }
        });
        spriteSCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spriteSCountKeyTyped(evt);
            }
        });

        spriteLCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        spriteLCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spriteLCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        spriteLCount.setEnabled(false);
        spriteLCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        spriteLCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spriteLCountFocusLost(evt);
            }
        });
        spriteLCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spriteLCountActionPerformed(evt);
            }
        });
        spriteLCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spriteLCountKeyTyped(evt);
            }
        });

        milkShakeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        milkShakeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        milkShakeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        milkShakeCount.setEnabled(false);
        milkShakeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        milkShakeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                milkShakeCountFocusLost(evt);
            }
        });
        milkShakeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkShakeCountActionPerformed(evt);
            }
        });
        milkShakeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                milkShakeCountKeyTyped(evt);
            }
        });

        hotChocolateCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        hotChocolateCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hotChocolateCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        hotChocolateCount.setEnabled(false);
        hotChocolateCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        hotChocolateCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotChocolateCountFocusLost(evt);
            }
        });
        hotChocolateCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotChocolateCountActionPerformed(evt);
            }
        });
        hotChocolateCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hotChocolateCountKeyTyped(evt);
            }
        });

        iceCoffeeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        iceCoffeeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        iceCoffeeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        iceCoffeeCount.setEnabled(false);
        iceCoffeeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        iceCoffeeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                iceCoffeeCountFocusLost(evt);
            }
        });
        iceCoffeeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iceCoffeeCountActionPerformed(evt);
            }
        });
        iceCoffeeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                iceCoffeeCountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout drinksPanelLayout = new javax.swing.GroupLayout(drinksPanel);
        drinksPanel.setLayout(drinksPanelLayout);
        drinksPanelLayout.setHorizontalGroup(
            drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinksPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cocacolaS)
                    .addComponent(iceCoffee)
                    .addComponent(hotChocolate)
                    .addComponent(milkShake)
                    .addComponent(spriteL)
                    .addComponent(spriteS)
                    .addComponent(cocacolaL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cocacolaSCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cocacolaLCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteSCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteLCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(milkShakeCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotChocolateCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iceCoffeeCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        drinksPanelLayout.setVerticalGroup(
            drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drinksPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cocacolaS)
                    .addComponent(cocacolaSCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cocacolaL)
                    .addComponent(cocacolaLCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spriteS)
                    .addComponent(spriteSCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spriteL)
                    .addComponent(spriteLCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(milkShake)
                    .addComponent(milkShakeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotChocolate)
                    .addComponent(hotChocolateCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(drinksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iceCoffee)
                    .addComponent(iceCoffeeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        drinksLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        drinksLabel.setText("Drinks");

        dessertPanel.setBackground(new java.awt.Color(255, 255, 255));
        dessertPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        vanilaCone.setBackground(new java.awt.Color(255, 255, 255));
        vanilaCone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vanilaCone.setText("Vanila Cone");
        vanilaCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vanilaConeActionPerformed(evt);
            }
        });

        chocolateCone.setBackground(new java.awt.Color(255, 255, 255));
        chocolateCone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chocolateCone.setText("Chocolate Cone");
        chocolateCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chocolateConeActionPerformed(evt);
            }
        });

        strawberryCone.setBackground(new java.awt.Color(255, 255, 255));
        strawberryCone.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        strawberryCone.setText("Strawberry Cone");
        strawberryCone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strawberryConeActionPerformed(evt);
            }
        });

        vanilaSundaes.setBackground(new java.awt.Color(255, 255, 255));
        vanilaSundaes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vanilaSundaes.setText("Vanila Sundae");
        vanilaSundaes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vanilaSundaesActionPerformed(evt);
            }
        });

        chocolateSundaes.setBackground(new java.awt.Color(255, 255, 255));
        chocolateSundaes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chocolateSundaes.setText("Chocolate Sundae");
        chocolateSundaes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chocolateSundaesActionPerformed(evt);
            }
        });

        lavaCake.setBackground(new java.awt.Color(255, 255, 255));
        lavaCake.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lavaCake.setText("Lava Cake");
        lavaCake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lavaCakeActionPerformed(evt);
            }
        });

        vanilaSundaesCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vanilaSundaesCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vanilaSundaesCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        vanilaSundaesCount.setEnabled(false);
        vanilaSundaesCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        vanilaSundaesCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vanilaSundaesCountFocusLost(evt);
            }
        });
        vanilaSundaesCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vanilaSundaesCountActionPerformed(evt);
            }
        });
        vanilaSundaesCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vanilaSundaesCountKeyTyped(evt);
            }
        });

        chocolateSundaesCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chocolateSundaesCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chocolateSundaesCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chocolateSundaesCount.setEnabled(false);
        chocolateSundaesCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        chocolateSundaesCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chocolateSundaesCountFocusLost(evt);
            }
        });
        chocolateSundaesCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chocolateSundaesCountActionPerformed(evt);
            }
        });
        chocolateSundaesCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chocolateSundaesCountKeyTyped(evt);
            }
        });

        lavaCakeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lavaCakeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lavaCakeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        lavaCakeCount.setEnabled(false);
        lavaCakeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        lavaCakeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lavaCakeCountFocusLost(evt);
            }
        });
        lavaCakeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lavaCakeCountActionPerformed(evt);
            }
        });
        lavaCakeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lavaCakeCountKeyTyped(evt);
            }
        });

        vanilaConeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        vanilaConeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        vanilaConeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        vanilaConeCount.setEnabled(false);
        vanilaConeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        vanilaConeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                vanilaConeCountFocusLost(evt);
            }
        });
        vanilaConeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vanilaConeCountActionPerformed(evt);
            }
        });
        vanilaConeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                vanilaConeCountKeyTyped(evt);
            }
        });

        chocolateConeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        chocolateConeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        chocolateConeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        chocolateConeCount.setEnabled(false);
        chocolateConeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        chocolateConeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chocolateConeCountFocusLost(evt);
            }
        });
        chocolateConeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chocolateConeCountActionPerformed(evt);
            }
        });
        chocolateConeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                chocolateConeCountKeyTyped(evt);
            }
        });

        strawberryConeCount.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        strawberryConeCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        strawberryConeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        strawberryConeCount.setEnabled(false);
        strawberryConeCount.setSelectionColor(new java.awt.Color(102, 153, 255));
        strawberryConeCount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                strawberryConeCountFocusLost(evt);
            }
        });
        strawberryConeCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strawberryConeCountActionPerformed(evt);
            }
        });
        strawberryConeCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                strawberryConeCountKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout dessertPanelLayout = new javax.swing.GroupLayout(dessertPanel);
        dessertPanel.setLayout(dessertPanelLayout);
        dessertPanelLayout.setHorizontalGroup(
            dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dessertPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vanilaCone)
                    .addComponent(chocolateSundaes)
                    .addComponent(vanilaSundaes)
                    .addComponent(lavaCake)
                    .addComponent(chocolateCone)
                    .addComponent(strawberryCone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vanilaConeCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(vanilaSundaesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lavaCakeCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chocolateSundaesCount, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chocolateConeCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(strawberryConeCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        dessertPanelLayout.setVerticalGroup(
            dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dessertPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vanilaCone)
                    .addComponent(vanilaConeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chocolateCone)
                    .addComponent(chocolateConeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(strawberryCone)
                    .addComponent(strawberryConeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vanilaSundaes)
                    .addComponent(vanilaSundaesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chocolateSundaes)
                    .addComponent(chocolateSundaesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dessertPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lavaCake)
                    .addComponent(lavaCakeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dessertLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dessertLabel.setText("Dessert");

        mealsTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        mealsTable.setForeground(new java.awt.Color(51, 51, 51));
        mealsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meal", "Quantity", "Unit Price", "Sub Total Price"
            }
        ));
        mealsTable.setGridColor(new java.awt.Color(153, 153, 153));
        mealsTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        mealsTable.setShowHorizontalLines(false);
        mealsTable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                mealsTableAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        mealsTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mealsTableFocusGained(evt);
            }
        });
        mealsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mealsTableMouseClicked(evt);
            }
        });
        mealsTable.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                mealsTableInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        mealsTabelPanel.setViewportView(mealsTable);

        serviceChargesTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        serviceChargesTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serviceChargesTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        serviceChargesTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        totalTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        totalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        totalTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        totalLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        totalLabel.setText("Total");

        serviceChargesLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        serviceChargesLabel.setText("Service Charges");

        proceedButton.setBackground(new java.awt.Color(255, 255, 255));
        proceedButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        proceedButton.setForeground(new java.awt.Color(51, 51, 51));
        proceedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ok.png"))); // NOI18N
        proceedButton.setText("Proceed");
        proceedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(255, 255, 255));
        cancelButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cancel.png"))); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        orderNoLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderNoLabel.setText("Order No");

        oidLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        oidLabel.setText("0");

        recievedTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        recievedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recievedTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        recievedTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        recievedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                recievedTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                recievedTextFieldFocusLost(evt);
            }
        });
        recievedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recievedTextFieldKeyTyped(evt);
            }
        });

        balanceTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        balanceTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        balanceTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        balanceTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        recievedLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        recievedLabel.setText("Payment");

        balanceLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        balanceLabel.setText("Balance");

        getCustomerDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        getCustomerDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        emailTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        emailTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        emailTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        emailLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        emailLabel.setText("Email");

        phoneLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phoneLabel.setText("Phone");

        phoneTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phoneTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        phoneTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        phoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyTyped(evt);
            }
        });

        nameTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        nameTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        customerNameLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        customerNameLabel.setText("Name");

        customerIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        customerIDLabel.setText("Customer ID");

        cidLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cidLabel.setText("0");

        javax.swing.GroupLayout getCustomerDetailsPanelLayout = new javax.swing.GroupLayout(getCustomerDetailsPanel);
        getCustomerDetailsPanel.setLayout(getCustomerDetailsPanelLayout);
        getCustomerDetailsPanelLayout.setHorizontalGroup(
            getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getCustomerDetailsPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(getCustomerDetailsPanelLayout.createSequentialGroup()
                        .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerNameLabel)
                            .addComponent(emailLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phoneTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(getCustomerDetailsPanelLayout.createSequentialGroup()
                        .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(getCustomerDetailsPanelLayout.createSequentialGroup()
                                .addComponent(customerIDLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(phoneLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        getCustomerDetailsPanelLayout.setVerticalGroup(
            getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getCustomerDetailsPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIDLabel)
                    .addComponent(cidLabel))
                .addGap(11, 11, 11)
                .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(getCustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        orderNoLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderNoLabel1.setText("Customer Details");

        javax.swing.GroupLayout mealsPanelLayout = new javax.swing.GroupLayout(mealsPanel);
        mealsPanel.setLayout(mealsPanelLayout);
        mealsPanelLayout.setHorizontalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mealsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(casualMealsLabel)
                            .addComponent(casualMealsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drinksLabel)
                            .addComponent(drinksPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mealsPanelLayout.createSequentialGroup()
                                .addComponent(totalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mealsPanelLayout.createSequentialGroup()
                                .addComponent(orderNoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serviceChargesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serviceChargesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mealsPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(balanceLabel))
                            .addGroup(mealsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(recievedLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(balanceTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recievedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(mealsTabelPanel))
                .addGap(48, 48, 48)
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addComponent(proceedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dessertPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dessertLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderNoLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getCustomerDetailsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        mealsPanelLayout.setVerticalGroup(
            mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mealsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(casualMealsLabel)
                    .addComponent(drinksLabel)
                    .addComponent(dessertLabel))
                .addGap(6, 6, 6)
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dessertPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(casualMealsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(drinksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mealsTabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addComponent(orderNoLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getCustomerDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mealsPanelLayout.createSequentialGroup()
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(orderNoLabel)
                                .addComponent(oidLabel))
                            .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(recievedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(recievedLabel)
                                .addComponent(serviceChargesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(serviceChargesLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(balanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(balanceLabel)
                            .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLabel))
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mealsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(mealsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(proceedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );

        javax.swing.GroupLayout makeOrdersPanelLayout = new javax.swing.GroupLayout(makeOrdersPanel);
        makeOrdersPanel.setLayout(makeOrdersPanelLayout);
        makeOrdersPanelLayout.setHorizontalGroup(
            makeOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makeOrdersPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        makeOrdersPanelLayout.setVerticalGroup(
            makeOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makeOrdersPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        navPanel.addTab("Make Orders", new javax.swing.ImageIcon(getClass().getResource("/Resources/food.png")), makeOrdersPanel, ""); // NOI18N

        analyticsPanel.setBackground(new java.awt.Color(255, 255, 255));
        analyticsPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        ordersTableLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ordersTableLabel.setText("Orders Table");

        orderDetailsLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderDetailsLabel.setText("Order Details");

        ordersTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        ordersTablePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        ordersTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ordersTable.setForeground(new java.awt.Color(51, 51, 51));
        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Order ID", "Cashier ID", "Customer ID", "Amount", "Profit"
            }
        ));
        ordersTable.setGridColor(new java.awt.Color(153, 153, 153));
        ordersTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        ordersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersTableMouseClicked(evt);
            }
        });
        ordersTabelPanel1.setViewportView(ordersTable);

        refereshOrderDataButton.setBackground(new java.awt.Color(255, 255, 255));
        refereshOrderDataButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        refereshOrderDataButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        refereshOrderDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refereshOrderDataButtonActionPerformed(evt);
            }
        });

        filterOrdersPanel.setBackground(new java.awt.Color(255, 255, 255));
        filterOrdersPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        filterOrdersPanel.setForeground(new java.awt.Color(51, 51, 51));

        filterByCustomerLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByCustomerLabel.setText("By Customer ID");

        filterByCashierLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByCashierLabel.setText("By Cashier ID");

        filterByOrderLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByOrderLabel.setText("By Order ID");

        filterByDateLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByDateLabel.setText("By Date");

        orderByOIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderByOIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderByOIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        orderByOIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        orderByOIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        orderByOIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orderByOIDTextFieldKeyReleased(evt);
            }
        });

        orderByUIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderByUIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderByUIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        orderByUIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        orderByUIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        orderByUIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orderByUIDTextFieldKeyReleased(evt);
            }
        });

        orderByCIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderByCIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderByCIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        orderByCIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        orderByCIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        orderByCIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orderByCIDTextFieldKeyReleased(evt);
            }
        });

        filterByPHTLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByPHTLabel.setText("By Profit Higher Than");

        filterByPLTLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        filterByPLTLabel.setText("By Profit Lower Than");

        ordreByPHTTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ordreByPHTTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ordreByPHTTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        ordreByPHTTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ordreByPHTTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        ordreByPHTTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ordreByPHTTextFieldKeyReleased(evt);
            }
        });

        ordreByPLTTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ordreByPLTTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ordreByPLTTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        ordreByPLTTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ordreByPLTTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        ordreByPLTTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ordreByPLTTextFieldKeyReleased(evt);
            }
        });

        orderByDateTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderByDateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderByDateTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        orderByDateTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        orderByDateTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        orderByDateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                orderByDateTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                orderByDateTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout filterOrdersPanelLayout = new javax.swing.GroupLayout(filterOrdersPanel);
        filterOrdersPanel.setLayout(filterOrdersPanelLayout);
        filterOrdersPanelLayout.setHorizontalGroup(
            filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filterOrdersPanelLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filterOrdersPanelLayout.createSequentialGroup()
                        .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterByPLTLabel)
                            .addComponent(filterByPHTLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ordreByPLTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ordreByPHTTextField)))
                    .addGroup(filterOrdersPanelLayout.createSequentialGroup()
                        .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterByCustomerLabel)
                            .addComponent(filterByCashierLabel)
                            .addComponent(filterByOrderLabel)
                            .addComponent(filterByDateLabel))
                        .addGap(46, 46, 46)
                        .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orderByCIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(orderByUIDTextField)
                            .addComponent(orderByOIDTextField)
                            .addComponent(orderByDateTextField, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(13, 13, 13))
        );
        filterOrdersPanelLayout.setVerticalGroup(
            filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filterOrdersPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filterByDateLabel)
                    .addComponent(orderByDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByOrderLabel)
                    .addComponent(orderByOIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByCashierLabel)
                    .addComponent(orderByUIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByCustomerLabel)
                    .addComponent(orderByCIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByPHTLabel)
                    .addComponent(ordreByPHTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filterOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterByPLTLabel)
                    .addComponent(ordreByPLTTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ordersTableLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        ordersTableLabel1.setText("Filter Orders");

        totalProfitTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        totalProfitTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalProfitTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        totalProfitTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalProfitTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        totalProfitLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        totalProfitLabel.setText("Total Profit");

        javax.swing.GroupLayout ordersTablePanelLayout = new javax.swing.GroupLayout(ordersTablePanel);
        ordersTablePanel.setLayout(ordersTablePanelLayout);
        ordersTablePanelLayout.setHorizontalGroup(
            ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersTablePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(refereshOrderDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(totalProfitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ordersTableLabel1)
                    .addComponent(filterOrdersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ordersTablePanelLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(ordersTabelPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(356, Short.MAX_VALUE)))
        );
        ordersTablePanelLayout.setVerticalGroup(
            ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersTablePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ordersTableLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ordersTablePanelLayout.createSequentialGroup()
                        .addGap(0, 214, Short.MAX_VALUE)
                        .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalProfitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalProfitLabel))
                            .addComponent(refereshOrderDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(filterOrdersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
            .addGroup(ordersTablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ordersTablePanelLayout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(ordersTabelPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE)))
        );

        orderDetailsList.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        orderDetailsPanel.setViewportView(orderDetailsList);

        javax.swing.GroupLayout analyticsPanelLayout = new javax.swing.GroupLayout(analyticsPanel);
        analyticsPanel.setLayout(analyticsPanelLayout);
        analyticsPanelLayout.setHorizontalGroup(
            analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analyticsPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderDetailsLabel)
                    .addComponent(ordersTableLabel)
                    .addComponent(ordersTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        analyticsPanelLayout.setVerticalGroup(
            analyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analyticsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(ordersTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ordersTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderDetailsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        navPanel.addTab("Order Analytics", new javax.swing.ImageIcon(getClass().getResource("/Resources/analytics.png")), analyticsPanel); // NOI18N

        grnPanel.setBackground(new java.awt.Color(255, 255, 255));

        grnMainPanel.setBackground(new java.awt.Color(255, 255, 255));

        addItemButton1.setBackground(new java.awt.Color(255, 255, 255));
        addItemButton1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        addItemButton1.setText("<html>View<br>  GRN<br>Table</html>");
        addItemButton1.setActionCommand("<html>GRN<br>\nList</html>");
        addItemButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addItemButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButton1ActionPerformed(evt);
            }
        });

        grnItemReceiveLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnItemReceiveLabel3.setText("Suppliers Details");

        stockDetailsPanel1.setBackground(new java.awt.Color(255, 255, 255));
        stockDetailsPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        supplierTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Supplier Name"
            }
        ));
        supplierTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        supplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });
        stockTabelPanel1.setViewportView(supplierTable);

        supplierSearchTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        supplierSearchTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        supplierSearchTextField.setText("Search Items");
        supplierSearchTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        supplierSearchTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        supplierSearchTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        supplierSearchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                supplierSearchTextFieldFocusLost(evt);
            }
        });
        supplierSearchTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierSearchTextFieldMouseClicked(evt);
            }
        });
        supplierSearchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                supplierSearchTextFieldKeyTyped(evt);
            }
        });

        supplierRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierRefreshButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        supplierRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        supplierRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockDetailsPanel1Layout = new javax.swing.GroupLayout(stockDetailsPanel1);
        stockDetailsPanel1.setLayout(stockDetailsPanel1Layout);
        stockDetailsPanel1Layout.setHorizontalGroup(
            stockDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockDetailsPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(stockDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(stockDetailsPanel1Layout.createSequentialGroup()
                        .addComponent(supplierSearchTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stockTabelPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        stockDetailsPanel1Layout.setVerticalGroup(
            stockDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockDetailsPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(stockDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(supplierRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockTabelPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grnItemReceiveLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnItemReceiveLabel2.setText("Add Suppliers");

        supplierDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        supplierDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        grnSupplierIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierIDLabel.setText("Supplier ID");

        newSupplierIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        newSupplierIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        newSupplierIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        newSupplierIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        newSupplierIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        newSupplierIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newSupplierIDTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newSupplierIDTextFieldKeyTyped(evt);
            }
        });

        grnSupplierLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierLabel.setText("Supplier");

        newSupplierTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        newSupplierTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        newSupplierTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        newSupplierTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        newSupplierTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        newSupplierTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newSupplierTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newSupplierTextFieldKeyTyped(evt);
            }
        });

        grnSupplierPhoneLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierPhoneLabel.setText("Phone");

        grnSupplierPhoneTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierPhoneTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnSupplierPhoneTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnSupplierPhoneTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnSupplierPhoneTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnSupplierPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnSupplierPhoneTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnSupplierPhoneTextFieldKeyTyped(evt);
            }
        });

        grnSupplierEmailLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierEmailLabel.setText("Email");

        grnSupplierEmailTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierEmailTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnSupplierEmailTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnSupplierEmailTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnSupplierEmailTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnSupplierEmailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnSupplierEmailTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnSupplierEmailTextFieldKeyTyped(evt);
            }
        });

        addSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        addSupplierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/supplier.png"))); // NOI18N
        addSupplierButton.setText("Add");
        addSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierButtonActionPerformed(evt);
            }
        });

        updateSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        updateSupplierButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        updateSupplierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/edit.png"))); // NOI18N
        updateSupplierButton.setText("Update");
        updateSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSupplierButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout supplierDetailsPanelLayout = new javax.swing.GroupLayout(supplierDetailsPanel);
        supplierDetailsPanel.setLayout(supplierDetailsPanelLayout);
        supplierDetailsPanelLayout.setHorizontalGroup(
            supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierDetailsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(supplierDetailsPanelLayout.createSequentialGroup()
                        .addComponent(addSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateSupplierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(supplierDetailsPanelLayout.createSequentialGroup()
                        .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grnSupplierPhoneLabel)
                            .addComponent(grnSupplierEmailLabel))
                        .addGap(38, 38, 38)
                        .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grnSupplierPhoneTextField)
                            .addComponent(grnSupplierEmailTextField)))
                    .addGroup(supplierDetailsPanelLayout.createSequentialGroup()
                        .addComponent(grnSupplierIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newSupplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(grnSupplierLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newSupplierTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supplierDetailsPanelLayout.setVerticalGroup(
            supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierDetailsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSupplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnSupplierIDLabel)
                    .addComponent(grnSupplierLabel)
                    .addComponent(newSupplierTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnSupplierPhoneLabel)
                    .addComponent(grnSupplierPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnSupplierEmailLabel)
                    .addComponent(grnSupplierEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(supplierDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateSupplierButton)
                    .addComponent(addSupplierButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        stockTableLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockTableLabel1.setText("Add Items");

        receiveItemsPanel.setBackground(new java.awt.Color(255, 255, 255));
        receiveItemsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        addNewItemButton.setBackground(new java.awt.Color(255, 255, 255));
        addNewItemButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        addNewItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/grn.png"))); // NOI18N
        addNewItemButton.setText("New");
        addNewItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewItemButtonActionPerformed(evt);
            }
        });

        addItemButton.setBackground(new java.awt.Color(255, 255, 255));
        addItemButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        addItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/addIem.png"))); // NOI18N
        addItemButton.setText("Add");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        grnNoLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnNoLabel.setText("GRN ID");

        grnidLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        grnidLabel.setText("0");

        grnTotalTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnTotalTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnTotalTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnTotalTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnTotalTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnTotalTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnTotalTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnTotalTextFieldKeyTyped(evt);
            }
        });

        grnTotalLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnTotalLabel.setText("Total");

        grnQtyTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnQtyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnQtyTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnQtyTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnQtyTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnQtyTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnQtyTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnQtyTextFieldKeyTyped(evt);
            }
        });

        grnQtyLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnQtyLabel.setText("Quantity");

        grnUnitPriceLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnUnitPriceLabel.setText("Unit Price");

        grnUnitPriceTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnUnitPriceTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnUnitPriceTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnUnitPriceTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnUnitPriceTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnUnitPriceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnUnitPriceTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnUnitPriceTextFieldKeyTyped(evt);
            }
        });

        grnItemTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnItemTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnItemTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnItemTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnItemTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnItemTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnItemTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnItemTextFieldKeyTyped(evt);
            }
        });

        grnItemLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnItemLabel.setText("Item");

        itemIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        itemIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        itemIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        itemIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        itemIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                itemIDTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemIDTextFieldKeyTyped(evt);
            }
        });

        grnItemIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnItemIDLabel.setText("Item ID");

        grnDateTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnDateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnDateTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnDateTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnDateTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnDateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnDateTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnDateTextFieldKeyTyped(evt);
            }
        });

        grnDateLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnDateLabel.setText("Date");

        grnSupplierIDLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierIDLabel1.setText("Supplier ID");

        supplierIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        supplierIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        supplierIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        supplierIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        supplierIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        supplierIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                supplierIDTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                supplierIDTextFieldKeyTyped(evt);
            }
        });

        grnSupplierTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnSupplierTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnSupplierTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnSupplierTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnSupplierTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnSupplierTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnSupplierTextFieldKeyTyped(evt);
            }
        });

        grnSupplierLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnSupplierLabel1.setText("Supplier");

        grnExpLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnExpLabel.setText("Expire");

        grnExpTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        grnExpTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        grnExpTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        grnExpTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        grnExpTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        grnExpTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grnExpTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                grnExpTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout receiveItemsPanelLayout = new javax.swing.GroupLayout(receiveItemsPanel);
        receiveItemsPanel.setLayout(receiveItemsPanelLayout);
        receiveItemsPanelLayout.setHorizontalGroup(
            receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                        .addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addNewItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                        .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grnItemIDLabel)
                            .addComponent(grnSupplierIDLabel1)
                            .addComponent(grnDateLabel)
                            .addComponent(grnUnitPriceLabel)
                            .addComponent(grnNoLabel)
                            .addComponent(grnQtyLabel)
                            .addComponent(grnTotalLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(grnTotalTextField)
                            .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(grnDateTextField)
                                    .addComponent(grnidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(grnUnitPriceTextField)
                                    .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                                        .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                                                .addComponent(supplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(grnSupplierLabel1))
                                            .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                                                .addComponent(itemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(grnItemLabel)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(grnItemTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                            .addComponent(grnSupplierTextField))))
                                .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                                    .addComponent(grnQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(grnExpLabel)
                                    .addGap(24, 24, 24)
                                    .addComponent(grnExpTextField))))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        receiveItemsPanelLayout.setVerticalGroup(
            receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiveItemsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnNoLabel)
                    .addComponent(grnidLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnItemLabel)
                    .addComponent(grnItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnItemIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnSupplierLabel1)
                    .addComponent(grnSupplierTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnSupplierIDLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnUnitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnUnitPriceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnQtyLabel)
                    .addComponent(grnExpLabel)
                    .addComponent(grnExpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grnTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grnTotalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiveItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNewItemButton)
                    .addComponent(addItemButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        stockDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        stockDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        stockTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Quantity", "Unit Price"
            }
        ));
        stockTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        stockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockTableMouseClicked(evt);
            }
        });
        stockTabelPanel.setViewportView(stockTable);

        itemSearchTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        itemSearchTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemSearchTextField.setText("Search Items");
        itemSearchTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        itemSearchTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        itemSearchTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        itemSearchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemSearchTextFieldFocusLost(evt);
            }
        });
        itemSearchTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemSearchTextFieldMouseClicked(evt);
            }
        });
        itemSearchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemSearchTextFieldKeyTyped(evt);
            }
        });

        stockRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        stockRefreshButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        stockRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        stockRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockDetailsPanelLayout = new javax.swing.GroupLayout(stockDetailsPanel);
        stockDetailsPanel.setLayout(stockDetailsPanelLayout);
        stockDetailsPanelLayout.setHorizontalGroup(
            stockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockDetailsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(stockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockTabelPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addGroup(stockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(itemSearchTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        stockDetailsPanelLayout.setVerticalGroup(
            stockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockDetailsPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(stockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockTabelPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        stockTableLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockTableLabel.setText("Stock Details");

        javax.swing.GroupLayout grnMainPanelLayout = new javax.swing.GroupLayout(grnMainPanel);
        grnMainPanel.setLayout(grnMainPanelLayout);
        grnMainPanelLayout.setHorizontalGroup(
            grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grnMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(grnMainPanelLayout.createSequentialGroup()
                        .addComponent(addItemButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stockDetailsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grnItemReceiveLabel3)))
                    .addComponent(stockDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockTableLabel1)
                    .addComponent(receiveItemsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grnItemReceiveLabel2)
                    .addComponent(supplierDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75))
            .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grnMainPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockTableLabel)
                    .addGap(914, 914, 914)))
        );
        grnMainPanelLayout.setVerticalGroup(
            grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, grnMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stockTableLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(grnMainPanelLayout.createSequentialGroup()
                        .addComponent(receiveItemsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grnItemReceiveLabel2)
                            .addComponent(grnItemReceiveLabel3))
                        .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stockDetailsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supplierDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(grnMainPanelLayout.createSequentialGroup()
                        .addComponent(stockDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(addItemButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(grnMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(grnMainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(stockTableLabel)
                    .addContainerGap(596, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout grnPanelLayout = new javax.swing.GroupLayout(grnPanel);
        grnPanel.setLayout(grnPanelLayout);
        grnPanelLayout.setHorizontalGroup(
            grnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grnPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(grnMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        grnPanelLayout.setVerticalGroup(
            grnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(grnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(grnMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        navPanel.addTab("Stock Management", new javax.swing.ImageIcon(getClass().getResource("/Resources/stock.png")), grnPanel); // NOI18N

        kitchenManagementPanel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        kitchenItemIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenItemIDLabel.setText("Item ID");

        kitchenItemIDTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenItemIDTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kitchenItemIDTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        kitchenItemIDTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kitchenItemIDTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        kitchenItemIDTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kitchenItemIDTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kitchenItemIDTextFieldKeyTyped(evt);
            }
        });

        kitchenItemLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenItemLabel.setText("Item");

        kitchenItemTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenItemTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kitchenItemTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        kitchenItemTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kitchenItemTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        kitchenItemTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kitchenItemTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kitchenItemTextFieldKeyTyped(evt);
            }
        });

        recordIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        recordIDLabel.setText("Record ID");

        kridLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        kridLabel.setText("0");

        kitchenDateLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenDateLabel.setText("Date");

        kitchenDateTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenDateTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kitchenDateTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        kitchenDateTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kitchenDateTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        kitchenDateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kitchenDateTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kitchenDateTextFieldKeyTyped(evt);
            }
        });

        kitchenQtyLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenQtyLabel.setText("Quantity");

        kitchenQtyTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        kitchenQtyTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        kitchenQtyTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        kitchenQtyTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kitchenQtyTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        kitchenQtyTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kitchenQtyTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kitchenQtyTextFieldKeyTyped(evt);
            }
        });

        getItemButton.setBackground(new java.awt.Color(255, 255, 255));
        getItemButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        getItemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ingredients.png"))); // NOI18N
        getItemButton.setText("Get Items");
        getItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kitchenDateLabel)
                            .addComponent(recordIDLabel))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kridLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(kitchenDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kitchenQtyLabel)
                            .addComponent(kitchenItemIDLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(kitchenItemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(kitchenItemLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kitchenItemTextField))
                            .addComponent(kitchenQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(getItemButton)
                .addGap(92, 92, 92))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recordIDLabel)
                    .addComponent(kridLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kitchenDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchenDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kitchenItemIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchenItemLabel)
                    .addComponent(kitchenItemTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchenItemIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kitchenQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kitchenQtyLabel))
                .addGap(28, 28, 28)
                .addComponent(getItemButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        stockDetailsPanel2.setBackground(new java.awt.Color(255, 255, 255));
        stockDetailsPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        stockInKitchenTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockInKitchenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Item Name", "Quantity", "Unit Price"
            }
        ));
        stockInKitchenTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        stockInKitchenTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockInKitchenTableMouseClicked(evt);
            }
        });
        stockTabelPanel2.setViewportView(stockInKitchenTable);

        itemSearchInKitchenTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        itemSearchInKitchenTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemSearchInKitchenTextField.setText("Search Items");
        itemSearchInKitchenTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        itemSearchInKitchenTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        itemSearchInKitchenTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        itemSearchInKitchenTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemSearchInKitchenTextFieldFocusLost(evt);
            }
        });
        itemSearchInKitchenTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemSearchInKitchenTextFieldMouseClicked(evt);
            }
        });
        itemSearchInKitchenTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                itemSearchInKitchenTextFieldKeyTyped(evt);
            }
        });

        stockRefreshInKitchenButton.setBackground(new java.awt.Color(255, 255, 255));
        stockRefreshInKitchenButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        stockRefreshInKitchenButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        stockRefreshInKitchenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockRefreshInKitchenButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockDetailsPanel2Layout = new javax.swing.GroupLayout(stockDetailsPanel2);
        stockDetailsPanel2.setLayout(stockDetailsPanel2Layout);
        stockDetailsPanel2Layout.setHorizontalGroup(
            stockDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockDetailsPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(stockDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockTabelPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(stockDetailsPanel2Layout.createSequentialGroup()
                        .addComponent(itemSearchInKitchenTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stockRefreshInKitchenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );
        stockDetailsPanel2Layout.setVerticalGroup(
            stockDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockDetailsPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(stockDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stockRefreshInKitchenButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemSearchInKitchenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockTabelPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        stockTableLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockTableLabel2.setText("Stock Details");

        stockTableLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        stockTableLabel3.setText("Get Items");

        javax.swing.GroupLayout kitchenManagementPanelLayout = new javax.swing.GroupLayout(kitchenManagementPanel);
        kitchenManagementPanel.setLayout(kitchenManagementPanelLayout);
        kitchenManagementPanelLayout.setHorizontalGroup(
            kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kitchenManagementPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockDetailsPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTableLabel2))
                .addGap(39, 39, 39)
                .addGroup(kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockTableLabel3)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        kitchenManagementPanelLayout.setVerticalGroup(
            kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kitchenManagementPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockTableLabel2)
                    .addComponent(stockTableLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kitchenManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockDetailsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(346, Short.MAX_VALUE))
        );

        navPanel.addTab("Kitchen Management", new javax.swing.ImageIcon(getClass().getResource("/Resources/kitchen.png")), kitchenManagementPanel); // NOI18N

        userManagementPanel.setBackground(new java.awt.Color(255, 255, 255));
        userManagementPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userManagementPanel.setInheritsPopupMenu(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        userDetailsLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userDetailsLabel.setText("User Details");

        userRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        userRefreshButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        userRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        userRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userRefreshButtonActionPerformed(evt);
            }
        });

        searchInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        searchInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchInUMTextField.setText("Search Names");
        searchInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        searchInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        searchInUMTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchInUMTextFieldFocusLost(evt);
            }
        });
        searchInUMTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchInUMTextFieldMouseClicked(evt);
            }
        });
        searchInUMTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchInUMTextFieldKeyTyped(evt);
            }
        });

        controlPanel.setBackground(new java.awt.Color(255, 255, 255));

        confirmationPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        confirmationPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        confirmationPasswordField.setText("Confirmation Password");
        confirmationPasswordField.setToolTipText("<html>To Confirm the Changes, Enter Your Password <br>\nEg: fdsf34g");
        confirmationPasswordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        confirmationPasswordField.setSelectionColor(new java.awt.Color(102, 153, 255));
        confirmationPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmationPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmationPasswordFieldFocusLost(evt);
            }
        });

        saveUserButton.setBackground(new java.awt.Color(255, 255, 255));
        saveUserButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        saveUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/saveUser.png"))); // NOI18N
        saveUserButton.setText("Save");
        saveUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveUserButtonActionPerformed(evt);
            }
        });

        addUserButton.setBackground(new java.awt.Color(255, 255, 255));
        addUserButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        addUserButton.setForeground(new java.awt.Color(51, 51, 51));
        addUserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/addUser.png"))); // NOI18N
        addUserButton.setText("Add");
        addUserButton.setBorderPainted(false);
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        userListLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userListLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userListLabel1.setText("Enter your password to take an action");

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmationPasswordField)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(userListLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userListLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmationPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserButton)
                    .addComponent(saveUserButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userListLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userListLabel.setText("Users List");

        userDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        userDetailsPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        dobInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dobInUMLabel.setText("DoB");

        addressInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        addressInUMLabel.setText("Address");

        emailInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        emailInUMLabel.setText("Email");

        phoneInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phoneInUMLabel.setText("Phone");

        genderInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        genderInUMLabel.setText("Gender");

        nameInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameInUMLabel.setText("Name");

        addressInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        addressInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        addressInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        addressInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        addressInUMTextField.setEnabled(false);
        addressInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        emailInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        emailInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        emailInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        emailInUMTextField.setEnabled(false);
        emailInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        phoneInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phoneInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        phoneInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        phoneInUMTextField.setEnabled(false);
        phoneInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        phoneInUMTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneInUMTextFieldKeyTyped(evt);
            }
        });

        dobInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dobInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dobInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        dobInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        dobInUMTextField.setEnabled(false);
        dobInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        dobInUMTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dobInUMTextFieldKeyTyped(evt);
            }
        });

        genderInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        genderInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        genderInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        genderInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        genderInUMTextField.setEnabled(false);
        genderInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));
        genderInUMTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                genderInUMTextFieldFocusLost(evt);
            }
        });

        nameInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        nameInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nameInUMTextField.setEnabled(false);
        nameInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        usernameInUMTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        usernameInUMTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameInUMTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        usernameInUMTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        usernameInUMTextField.setEnabled(false);
        usernameInUMTextField.setSelectionColor(new java.awt.Color(102, 153, 255));

        orderNoLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        orderNoLabel2.setText("User ID");

        uidInUMLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        uidInUMLabel.setText("0");

        usernameInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        usernameInUMLabel.setText("Username");

        inUMPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inUMPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inUMPasswordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        inUMPasswordField.setEnabled(false);
        inUMPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inUMPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inUMPasswordFieldFocusLost(evt);
            }
        });

        passwordInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        passwordInUMLabel.setText("Password");

        inUMConfirmPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inUMConfirmPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inUMConfirmPasswordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        inUMConfirmPasswordField.setEnabled(false);
        inUMConfirmPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inUMConfirmPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inUMConfirmPasswordFieldFocusLost(evt);
            }
        });

        passwordInUMLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        passwordInUMLabel1.setText("Confirm");

        managementInUMCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        managementInUMCheckBox.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        managementInUMCheckBox.setText("Management Access");
        managementInUMCheckBox.setToolTipText("Check If the User is a Person in Managerial Level");
        managementInUMCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        managementInUMCheckBox.setEnabled(false);
        managementInUMCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        managementInUMCheckBox.setIconTextGap(5);

        editInUMToggleButton.setBackground(new java.awt.Color(255, 255, 255));
        editInUMToggleButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        editInUMToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/editUser.png"))); // NOI18N
        editInUMToggleButton.setText("Edit");
        editInUMToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editInUMToggleButtonActionPerformed(evt);
            }
        });

        activeInUMCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        activeInUMCheckBox.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        activeInUMCheckBox.setText("Active");
        activeInUMCheckBox.setToolTipText("Check If the User is a Person in Managerial Level");
        activeInUMCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        activeInUMCheckBox.setEnabled(false);
        activeInUMCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        activeInUMCheckBox.setIconTextGap(5);

        javax.swing.GroupLayout userDetailsPanelLayout = new javax.swing.GroupLayout(userDetailsPanel);
        userDetailsPanel.setLayout(userDetailsPanelLayout);
        userDetailsPanelLayout.setHorizontalGroup(
            userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailsPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(userDetailsPanelLayout.createSequentialGroup()
                        .addComponent(orderNoLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(uidInUMLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userDetailsPanelLayout.createSequentialGroup()
                        .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameInUMLabel)
                            .addComponent(genderInUMLabel)
                            .addComponent(phoneInUMLabel)
                            .addComponent(emailInUMLabel)
                            .addComponent(addressInUMLabel))
                        .addGap(8, 8, 8)
                        .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneInUMTextField)
                            .addComponent(emailInUMTextField)
                            .addGroup(userDetailsPanelLayout.createSequentialGroup()
                                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(userDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(genderInUMTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(dobInUMLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dobInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(nameInUMTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(addressInUMTextField)))
                    .addGroup(userDetailsPanelLayout.createSequentialGroup()
                        .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(usernameInUMLabel)
                                .addComponent(passwordInUMLabel, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(passwordInUMLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inUMPasswordField)
                            .addComponent(usernameInUMTextField)
                            .addComponent(inUMConfirmPasswordField)))
                    .addGroup(userDetailsPanelLayout.createSequentialGroup()
                        .addComponent(managementInUMCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(activeInUMCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(editInUMToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        userDetailsPanelLayout.setVerticalGroup(
            userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userDetailsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderNoLabel2)
                    .addComponent(uidInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderInUMLabel)
                    .addComponent(dobInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressInUMLabel))
                .addGap(41, 41, 41)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inUMPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordInUMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inUMConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordInUMLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(userDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managementInUMCheckBox)
                    .addComponent(activeInUMCheckBox))
                .addGap(28, 28, 28)
                .addComponent(editInUMToggleButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        userList.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        userList.setSelectionBackground(new java.awt.Color(102, 153, 255));
        userList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userListFocusGained(evt);
            }
        });
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListMouseClicked(evt);
            }
        });
        userListScrollPane.setViewportView(userList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userListLabel)
                            .addComponent(userListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(searchInUMTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userDetailsLabel))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(userListLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchInUMTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(userDetailsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout userManagementPanelLayout = new javax.swing.GroupLayout(userManagementPanel);
        userManagementPanel.setLayout(userManagementPanelLayout);
        userManagementPanelLayout.setHorizontalGroup(
            userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userManagementPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userManagementPanelLayout.setVerticalGroup(
            userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userManagementPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        navPanel.addTab("User Management", new javax.swing.ImageIcon(getClass().getResource("/Resources/userManagement.png")), userManagementPanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
            .addComponent(sidePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1295, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE user_id = " + useridLoad() + " AND password = '" + confirmationPasswordField.getText() + "'");
            if (getResultSetRowCount(rs) == 1) {
                new UserRegistration().setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Do you really want to log out?", "Order Screen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            new UserLogin().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void recievedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recievedTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_recievedTextFieldKeyTyped

    private void recievedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_recievedTextFieldFocusLost
        String payment = recievedTextField.getText();
        if (!payment.isEmpty() && !payment.endsWith("LKR")) {
            recievedTextField.setText(payment + " LKR");
        }

        String[] t = totalTextField.getText().split(" ");
        String total = t[0];
        String balance = (Double.parseDouble(payment) - Double.parseDouble(total)) + "";
        balanceTextField.setText(balance + " LKR");
    }//GEN-LAST:event_recievedTextFieldFocusLost

    private void recievedTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_recievedTextFieldFocusGained
        String payment = recievedTextField.getText();
        String[] s = payment.split(" ");
        recievedTextField.setText(s[0]);
    }//GEN-LAST:event_recievedTextFieldFocusGained

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Do you really want cancel the order?", "Order cancel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            reset();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void proceedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedButtonActionPerformed

        //customer table
        int cid = Integer.parseInt(cidLabel.getText());
        String cname = nameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();

        try {
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO customer(customer_id, name, phone, email) VALUES (" + cid + ", '" + cname + "', '" + phone + "', '" + email + "')");
        } catch (Exception e) {
            System.out.println(e);
        }

        //order table
        int oid = Integer.parseInt(oidLabel.getText());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String date = formatter.format(d);
        String[] t = totalTextField.getText().split(" ");
        String total = t[0];
        String[] s = recievedTextField.getText().split(" ");
        String payment = s[0];
        String balance = (Double.parseDouble(payment) - Double.parseDouble(total)) + "";
        int uid = useridLoad();

        try {
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO restaurentsystem.order(order_id, date, total, recieved, balance, customer_id, user_id) VALUES (" + oid + ", '" + date + "', '" + total + "', '" + payment + "', '" + balance + "', " + cid + ", " + uid + ")");
        } catch (Exception e) {
            System.out.println(e);
        }

        //order_has table
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String[] meals = new String[100];
        String[] drinks = new String[100];
        String[] desserts = new String[100];

        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM meal");
            int i = 0;
            while (rs.next()) {
                meals[i] = rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            System.out.println("A - " + e);
        }

        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM drink");
            int i = 0;
            while (rs.next()) {
                drinks[i] = rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            System.out.println("A - " + e);
        }

        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM dessert");
            int i = 0;
            while (rs.next()) {
                desserts[i] = rs.getString("name");
                i++;
            }
        } catch (Exception e) {
            System.out.println("A - " + e);
        }

        for (int i = 0; i < mealsTable.getRowCount(); i++) {
            for (String meal : meals) {
                if (dtm.getValueAt(i, 0).toString().equals(meal)) {
                    int mealid;
                    int qty = Integer.parseInt(dtm.getValueAt(i, 1).toString());
                    try {
                        ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM meal WHERE name='" + meal + "'");
                        if (rs.first()) {
                            mealid = Integer.parseInt(rs.getString("meal_id"));
                            DatabaseConnection.getConnection().executeUpdate("INSERT INTO order_has_meal(order_id, meal_id, qty) VALUES (" + oid + ", " + mealid + ", " + qty + ")");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(ManagerScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            for (String drink : drinks) {
                if (dtm.getValueAt(i, 0).equals(drink)) {
                    int drinkid;
                    int qty = Integer.parseInt(dtm.getValueAt(i, 1).toString());
                    try {
                        ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM drink WHERE name='" + drink + "'");
                        if (rs.first()) {
                            drinkid = Integer.parseInt(rs.getString("drink_id"));
                            DatabaseConnection.getConnection().executeUpdate("INSERT INTO order_has_drink(order_id, drink_id, qty) VALUES (" + oid + ", " + drinkid + ", " + qty + ")");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(ManagerScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            for (String dessert : desserts) {
                if (dtm.getValueAt(i, 0).equals(dessert)) {
                    int dessertid;
                    int qty = Integer.parseInt(dtm.getValueAt(i, 1).toString());
                    try {
                        ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM dessert WHERE name='" + dessert + "'");
                        if (rs.first()) {
                            dessertid = Integer.parseInt(rs.getString("dessert_id"));
                            DatabaseConnection.getConnection().executeUpdate("INSERT INTO order_has_dessert(order_id, dessert_id, qty) VALUES (" + oid + ", " + dessertid + ", " + qty + ")");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(ManagerScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        reportGen();
        //Other Functions
        reset();
        loadID();
    }//GEN-LAST:event_proceedButtonActionPerformed

    private void mealsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mealsTableMouseClicked
        int selectedRow = mealsTable.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        dtm.removeRow(selectedRow);
    }//GEN-LAST:event_mealsTableMouseClicked

    private void mealsTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mealsTableFocusGained

    }//GEN-LAST:event_mealsTableFocusGained

    private void mealsTableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_mealsTableAncestorAdded

    }//GEN-LAST:event_mealsTableAncestorAdded

    private void strawberryConeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_strawberryConeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_strawberryConeCountKeyTyped

    private void strawberryConeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strawberryConeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = strawberryCone.getText();
        int qty = Integer.parseInt(strawberryConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_strawberryConeCountActionPerformed

    private void strawberryConeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_strawberryConeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = strawberryCone.getText();
        int qty = Integer.parseInt(strawberryConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_strawberryConeCountFocusLost

    private void chocolateConeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chocolateConeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_chocolateConeCountKeyTyped

    private void chocolateConeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chocolateConeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = chocolateCone.getText();
        int qty = Integer.parseInt(chocolateConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chocolateConeCountActionPerformed

    private void chocolateConeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chocolateConeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = chocolateCone.getText();
        int qty = Integer.parseInt(chocolateConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chocolateConeCountFocusLost

    private void vanilaConeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vanilaConeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_vanilaConeCountKeyTyped

    private void vanilaConeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vanilaConeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = vanilaCone.getText();
        int qty = Integer.parseInt(vanilaConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vanilaConeCountActionPerformed

    private void vanilaConeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vanilaConeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = vanilaCone.getText();
        int qty = Integer.parseInt(vanilaConeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vanilaConeCountFocusLost

    private void lavaCakeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lavaCakeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_lavaCakeCountKeyTyped

    private void lavaCakeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lavaCakeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = lavaCake.getText();
        int qty = Integer.parseInt(lavaCakeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_lavaCakeCountActionPerformed

    private void lavaCakeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lavaCakeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = lavaCake.getText();
        int qty = Integer.parseInt(lavaCakeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_lavaCakeCountFocusLost

    private void chocolateSundaesCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chocolateSundaesCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_chocolateSundaesCountKeyTyped

    private void chocolateSundaesCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chocolateSundaesCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = chocolateSundaes.getText();
        int qty = Integer.parseInt(chocolateSundaesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chocolateSundaesCountActionPerformed

    private void chocolateSundaesCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chocolateSundaesCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = chocolateSundaes.getText();
        int qty = Integer.parseInt(chocolateSundaesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chocolateSundaesCountFocusLost

    private void vanilaSundaesCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vanilaSundaesCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_vanilaSundaesCountKeyTyped

    private void vanilaSundaesCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vanilaSundaesCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = vanilaSundaes.getText();
        int qty = Integer.parseInt(vanilaSundaesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vanilaSundaesCountActionPerformed

    private void vanilaSundaesCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vanilaSundaesCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String dessert = vanilaSundaes.getText();
        int qty = Integer.parseInt(vanilaSundaesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM dessert WHERE name = '" + dessert + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(dessert);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vanilaSundaesCountFocusLost

    private void lavaCakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lavaCakeActionPerformed
        if (lavaCake.isSelected()) {
            lavaCakeCount.setEnabled(true);
            lavaCakeCount.requestFocus();
        } else {
            lavaCakeCount.setEnabled(false);
        }
    }//GEN-LAST:event_lavaCakeActionPerformed

    private void chocolateSundaesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chocolateSundaesActionPerformed
        if (chocolateSundaes.isSelected()) {
            chocolateSundaesCount.setEnabled(true);
            chocolateSundaesCount.requestFocus();
        } else {
            chocolateSundaesCount.setEnabled(false);
        }
    }//GEN-LAST:event_chocolateSundaesActionPerformed

    private void vanilaSundaesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vanilaSundaesActionPerformed
        if (vanilaSundaes.isSelected()) {
            vanilaSundaesCount.setEnabled(true);
            vanilaSundaesCount.requestFocus();
        } else {
            vanilaSundaesCount.setEnabled(false);
        }
    }//GEN-LAST:event_vanilaSundaesActionPerformed

    private void strawberryConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strawberryConeActionPerformed
        if (strawberryCone.isSelected()) {
            strawberryConeCount.setEnabled(true);
            strawberryConeCount.requestFocus();
        } else {
            strawberryConeCount.setEnabled(false);
        }
    }//GEN-LAST:event_strawberryConeActionPerformed

    private void chocolateConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chocolateConeActionPerformed
        if (chocolateCone.isSelected()) {
            chocolateConeCount.setEnabled(true);
            chocolateConeCount.requestFocus();
        } else {
            chocolateConeCount.setEnabled(false);
        }
    }//GEN-LAST:event_chocolateConeActionPerformed

    private void vanilaConeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vanilaConeActionPerformed
        if (vanilaCone.isSelected()) {
            vanilaConeCount.setEnabled(true);
            vanilaConeCount.requestFocus();
        } else {
            vanilaConeCount.setEnabled(false);
        }
    }//GEN-LAST:event_vanilaConeActionPerformed

    private void iceCoffeeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iceCoffeeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_iceCoffeeCountKeyTyped

    private void iceCoffeeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iceCoffeeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = iceCoffee.getText();
        int qty = Integer.parseInt(iceCoffeeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_iceCoffeeCountActionPerformed

    private void iceCoffeeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iceCoffeeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = iceCoffee.getText();
        int qty = Integer.parseInt(iceCoffeeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_iceCoffeeCountFocusLost

    private void hotChocolateCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hotChocolateCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_hotChocolateCountKeyTyped

    private void hotChocolateCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotChocolateCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = hotChocolate.getText();
        int qty = Integer.parseInt(hotChocolateCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_hotChocolateCountActionPerformed

    private void hotChocolateCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hotChocolateCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = hotChocolate.getText();
        int qty = Integer.parseInt(hotChocolateCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_hotChocolateCountFocusLost

    private void milkShakeCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_milkShakeCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_milkShakeCountKeyTyped

    private void milkShakeCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkShakeCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = milkShake.getText();
        int qty = Integer.parseInt(milkShakeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_milkShakeCountActionPerformed

    private void milkShakeCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_milkShakeCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = milkShake.getText();
        int qty = Integer.parseInt(milkShakeCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_milkShakeCountFocusLost

    private void spriteLCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spriteLCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_spriteLCountKeyTyped

    private void spriteLCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spriteLCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = spriteL.getText();
        int qty = Integer.parseInt(spriteLCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_spriteLCountActionPerformed

    private void spriteLCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spriteLCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = spriteL.getText();
        int qty = Integer.parseInt(spriteLCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_spriteLCountFocusLost

    private void spriteSCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spriteSCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_spriteSCountKeyTyped

    private void spriteSCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spriteSCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = spriteS.getText();
        int qty = Integer.parseInt(spriteSCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_spriteSCountActionPerformed

    private void spriteSCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spriteSCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = spriteS.getText();
        int qty = Integer.parseInt(spriteSCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_spriteSCountFocusLost

    private void cocacolaLCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cocacolaLCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_cocacolaLCountKeyTyped

    private void cocacolaLCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cocacolaLCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = cocacolaL.getText();
        int qty = Integer.parseInt(cocacolaLCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cocacolaLCountActionPerformed

    private void cocacolaLCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cocacolaLCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = cocacolaL.getText();
        int qty = Integer.parseInt(cocacolaLCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cocacolaLCountFocusLost

    private void cocacolaSCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cocacolaSCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_cocacolaSCountKeyTyped

    private void cocacolaSCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cocacolaSCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = cocacolaS.getText();
        int qty = Integer.parseInt(cocacolaSCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cocacolaSCountActionPerformed

    private void cocacolaSCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cocacolaSCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String drink = cocacolaS.getText();
        int qty = Integer.parseInt(cocacolaSCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM drink WHERE name = '" + drink + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(drink);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cocacolaSCountFocusLost

    private void iceCoffeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iceCoffeeActionPerformed
        if (iceCoffee.isSelected()) {
            iceCoffeeCount.setEnabled(true);
            iceCoffeeCount.requestFocus();
        } else {
            iceCoffeeCount.setEnabled(false);
        }
    }//GEN-LAST:event_iceCoffeeActionPerformed

    private void hotChocolateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotChocolateActionPerformed
        if (hotChocolate.isSelected()) {
            hotChocolateCount.setEnabled(true);
            hotChocolateCount.requestFocus();
        } else {
            hotChocolateCount.setEnabled(false);
        }
    }//GEN-LAST:event_hotChocolateActionPerformed

    private void milkShakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkShakeActionPerformed
        if (milkShake.isSelected()) {
            milkShakeCount.setEnabled(true);
            milkShakeCount.requestFocus();
        } else {
            milkShakeCount.setEnabled(false);
        }
    }//GEN-LAST:event_milkShakeActionPerformed

    private void spriteLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spriteLActionPerformed
        if (spriteL.isSelected()) {
            spriteLCount.setEnabled(true);
            spriteLCount.requestFocus();
        } else {
            spriteLCount.setEnabled(false);
        }
    }//GEN-LAST:event_spriteLActionPerformed

    private void spriteSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spriteSActionPerformed
        if (spriteS.isSelected()) {
            spriteSCount.setEnabled(true);
            spriteSCount.requestFocus();
        } else {
            spriteSCount.setEnabled(false);
        }
    }//GEN-LAST:event_spriteSActionPerformed

    private void cocacolaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cocacolaLActionPerformed
        if (cocacolaL.isSelected()) {
            cocacolaLCount.setEnabled(true);
            cocacolaLCount.requestFocus();
        } else {
            cocacolaLCount.setEnabled(false);
        }
    }//GEN-LAST:event_cocacolaLActionPerformed

    private void cocacolaSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cocacolaSActionPerformed
        if (cocacolaS.isSelected()) {
            cocacolaSCount.setEnabled(true);
            cocacolaSCount.requestFocus();
        } else {
            cocacolaSCount.setEnabled(false);
        }
    }//GEN-LAST:event_cocacolaSActionPerformed

    private void chickenNuggetsCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chickenNuggetsCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_chickenNuggetsCountKeyTyped

    private void chickenNuggetsCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chickenNuggetsCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = chickenNuggets.getText();
        int qty = Integer.parseInt(chickenNuggetsCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chickenNuggetsCountActionPerformed

    private void chickenNuggetsCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chickenNuggetsCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = chickenNuggets.getText();
        int qty = Integer.parseInt(chickenNuggetsCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chickenNuggetsCountFocusLost

    private void frenchFriesCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_frenchFriesCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_frenchFriesCountKeyTyped

    private void frenchFriesCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frenchFriesCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = frenchFries.getText();
        int qty = Integer.parseInt(frenchFriesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_frenchFriesCountActionPerformed

    private void frenchFriesCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_frenchFriesCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = frenchFries.getText();
        int qty = Integer.parseInt(frenchFriesCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_frenchFriesCountFocusLost

    private void chickenNuggetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chickenNuggetsActionPerformed
        if (chickenNuggets.isSelected()) {
            chickenNuggetsCount.setEnabled(true);
            chickenNuggetsCount.requestFocus();
        } else {
            chickenNuggetsCount.setEnabled(false);
        }
    }//GEN-LAST:event_chickenNuggetsActionPerformed

    private void frenchFriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frenchFriesActionPerformed
        if (frenchFries.isSelected()) {
            frenchFriesCount.setEnabled(true);
            frenchFriesCount.requestFocus();
        } else {
            frenchFriesCount.setEnabled(false);
        }
    }//GEN-LAST:event_frenchFriesActionPerformed

    private void nonvegSubmarineCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nonvegSubmarineCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_nonvegSubmarineCountKeyTyped

    private void nonvegSubmarineCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonvegSubmarineCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = nonvegSubmarine.getText();
        int qty = Integer.parseInt(nonvegSubmarineCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_nonvegSubmarineCountActionPerformed

    private void nonvegSubmarineCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nonvegSubmarineCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = nonvegSubmarine.getText();
        int qty = Integer.parseInt(nonvegSubmarineCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_nonvegSubmarineCountFocusLost

    private void vegSubmarineCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_vegSubmarineCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_vegSubmarineCountKeyTyped

    private void vegSubmarineCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vegSubmarineCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = vegSubmarine.getText();
        int qty = Integer.parseInt(vegSubmarineCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vegSubmarineCountActionPerformed

    private void vegSubmarineCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_vegSubmarineCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = vegSubmarine.getText();
        int qty = Integer.parseInt(vegSubmarineCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_vegSubmarineCountFocusLost

    private void beefBurgerCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beefBurgerCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_beefBurgerCountKeyTyped

    private void beefBurgerCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beefBurgerCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = beefBurger.getText();
        int qty = Integer.parseInt(beefBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_beefBurgerCountActionPerformed

    private void beefBurgerCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_beefBurgerCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = beefBurger.getText();
        int qty = Integer.parseInt(beefBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_beefBurgerCountFocusLost

    private void chickenBurgerCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chickenBurgerCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_chickenBurgerCountKeyTyped

    private void chickenBurgerCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chickenBurgerCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = chickenBurger.getText();
        int qty = Integer.parseInt(chickenBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chickenBurgerCountActionPerformed

    private void chickenBurgerCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chickenBurgerCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = chickenBurger.getText();
        int qty = Integer.parseInt(chickenBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_chickenBurgerCountFocusLost

    private void cheeseBurgerCountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cheeseBurgerCountKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_cheeseBurgerCountKeyTyped

    private void cheeseBurgerCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheeseBurgerCountActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = cheeseBurger.getText();
        int qty = Integer.parseInt(cheeseBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cheeseBurgerCountActionPerformed

    private void cheeseBurgerCountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cheeseBurgerCountFocusLost
        DefaultTableModel dtm = (DefaultTableModel) mealsTable.getModel();
        String meal = cheeseBurger.getText();
        int qty = Integer.parseInt(cheeseBurgerCount.getText());
        Double price;

        try {
            Statement s = DatabaseConnection.getConnection();
            ResultSet rs = s.executeQuery("SELECT * FROM meal WHERE name = '" + meal + "';");
            if (rs.first()) {
                price = Double.parseDouble(rs.getString("unit_price"));
            } else {
                price = 0.0;
            }

            Vector v = new Vector();
            v.add(meal);
            v.add(qty);
            v.add(price);
            v.add(qty * price);

            dtm.addRow(v);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        double t = 0.00;
        for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
            t += Double.parseDouble(dtm.getValueAt(i, 3).toString());
        }

        double serviceCharges = (t * 0.1);
        double total = t + serviceCharges;
        serviceChargesTextField.setText(serviceCharges + " LKR");
        totalTextField.setText(total + " LKR");
    }//GEN-LAST:event_cheeseBurgerCountFocusLost

    private void nonvegSubmarineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nonvegSubmarineActionPerformed
        if (nonvegSubmarine.isSelected()) {
            nonvegSubmarineCount.setEnabled(true);
            nonvegSubmarineCount.requestFocus();
        } else {
            nonvegSubmarineCount.setEnabled(false);
        }
    }//GEN-LAST:event_nonvegSubmarineActionPerformed

    private void vegSubmarineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vegSubmarineActionPerformed
        if (vegSubmarine.isSelected()) {
            vegSubmarineCount.setEnabled(true);
            vegSubmarineCount.requestFocus();
        } else {
            vegSubmarineCount.setEnabled(false);
        }
    }//GEN-LAST:event_vegSubmarineActionPerformed

    private void chickenBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chickenBurgerActionPerformed
        if (chickenBurger.isSelected()) {
            chickenBurgerCount.setEnabled(true);
            chickenBurgerCount.requestFocus();
        } else {
            chickenBurgerCount.setEnabled(false);
        }
    }//GEN-LAST:event_chickenBurgerActionPerformed

    private void cheeseBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheeseBurgerActionPerformed
        if (cheeseBurger.isSelected()) {
            cheeseBurgerCount.setEnabled(true);
            cheeseBurgerCount.requestFocus();
        } else {
            cheeseBurgerCount.setEnabled(false);
        }
    }//GEN-LAST:event_cheeseBurgerActionPerformed

    private void beefBurgerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beefBurgerActionPerformed
        if (beefBurger.isSelected()) {
            beefBurgerCount.setEnabled(true);
            beefBurgerCount.requestFocus();
        } else {
            beefBurgerCount.setEnabled(false);
        }
    }//GEN-LAST:event_beefBurgerActionPerformed

    private void phoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }

        int i = phoneTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_phoneTextFieldKeyTyped

    private void userListFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userListFocusGained

    }//GEN-LAST:event_userListFocusGained

    private void userListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userListMouseClicked
        String selectedUser = userList.getSelectedValue();
        String su[] = selectedUser.split(" ");
        int selectedUserID = Integer.parseInt(su[0]);

        try {
            String name = "";
            String gender = "";
            String dob = "";
            String phone = "";
            String email = "";
            String address = "";
            String username = "";
            String type = "";
            int status = 0;

            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM user WHERE user_id = " + selectedUserID + "");
            ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE user_id = " + selectedUserID + "");

            while (rs1.next()) {
                name = rs1.getString("name");
                gender = rs1.getString("gender");
                dob = rs1.getString("dob");
                phone = rs1.getString("phone");
                email = rs1.getString("email");
                address = rs1.getString("address");
            }

            while (rs2.next()) {
                username = rs2.getString("username");
                type = rs2.getString("type");
                status = Integer.parseInt(rs2.getString("status"));

            }

            uidInUMLabel.setText(selectedUserID + "");
            nameInUMTextField.setText(name);
            genderInUMTextField.setText(gender);
            dobInUMTextField.setText(dob);
            phoneInUMTextField.setText(phone);
            emailInUMTextField.setText(email);
            addressInUMTextField.setText(address);
            usernameInUMTextField.setText(username);
            managementInUMCheckBox.setSelected(type.equals("Manager") ? true : false);
            activeInUMCheckBox.setSelected(status == 1 ? true : false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_userListMouseClicked

    private void confirmationPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmationPasswordFieldFocusGained
        confirmationPasswordField.setText(null);
        confirmationPasswordField.setEchoChar('●');
        confirmationPasswordField.setForeground(Color.black);
    }//GEN-LAST:event_confirmationPasswordFieldFocusGained

    private void confirmationPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmationPasswordFieldFocusLost
        if (confirmationPasswordField.getText().isEmpty()) {
            confirmationPasswordField.setForeground(Color.gray);
            confirmationPasswordField.setEchoChar((char) 0);
            confirmationPasswordField.setText("Confirm Password");
        }
    }//GEN-LAST:event_confirmationPasswordFieldFocusLost

    private void editInUMToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editInUMToggleButtonActionPerformed
        if (editInUMToggleButton.isSelected()) {
            nameInUMTextField.setEnabled(true);
            genderInUMTextField.setEnabled(true);
            dobInUMTextField.setEnabled(true);
            phoneInUMTextField.setEnabled(true);
            emailInUMTextField.setEnabled(true);
            addressInUMTextField.setEnabled(true);
            usernameInUMTextField.setEnabled(true);
            inUMPasswordField.setEnabled(true);
            inUMConfirmPasswordField.setEnabled(true);
            managementInUMCheckBox.setEnabled(true);
            activeInUMCheckBox.setEnabled(true);
        } else {
            nameInUMTextField.setEnabled(false);
            genderInUMTextField.setEnabled(false);
            dobInUMTextField.setEnabled(false);
            phoneInUMTextField.setEnabled(false);
            emailInUMTextField.setEnabled(false);
            addressInUMTextField.setEnabled(false);
            usernameInUMTextField.setEnabled(false);
            inUMPasswordField.setEnabled(false);
            inUMConfirmPasswordField.setEnabled(false);
            managementInUMCheckBox.setEnabled(false);
            activeInUMCheckBox.setEnabled(false);
        }
    }//GEN-LAST:event_editInUMToggleButtonActionPerformed

    private void saveUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveUserButtonActionPerformed
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE user_id = " + useridLoad() + " AND password = '" + confirmationPasswordField.getText() + "'");
            if (getResultSetRowCount(rs) == 1 && editInUMToggleButton.isSelected()) {
                int uid = Integer.parseInt(uidInUMLabel.getText());
                String name = nameInUMTextField.getText();
                String gender = genderInUMTextField.getText();
                String dob = dobInUMTextField.getText();
                String phone = phoneInUMTextField.getText();
                String email = emailInUMTextField.getText();
                String address = addressInUMTextField.getText();
                String username = usernameInUMTextField.getText();
                String password = inUMPasswordField.getText();
                String confirm = inUMConfirmPasswordField.getText();
                boolean access = managementInUMCheckBox.isSelected();
                boolean status = activeInUMCheckBox.isSelected();

                if (!name.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET name='" + name + "' WHERE user_id=" + uid + "");
                }
                if (!gender.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET gender='" + gender + "' WHERE user_id=" + uid + "");
                }
                if (!dob.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET dob='" + dob + "' WHERE user_id=" + uid + "");
                }
                if (!phone.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET phone='" + phone + "' WHERE user_id=" + uid + "");
                }
                if (!email.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET email='" + email + "' WHERE user_id=" + uid + "");
                }
                if (!address.isEmpty()) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE user SET address='" + address + "' WHERE user_id=" + uid + "");
                }

                if (!username.isEmpty()) {
                    ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE username = '" + username + "'");
                    if (getResultSetRowCount(rs1) == 0) {
                        DatabaseConnection.getConnection().executeUpdate("UPDATE login SET username='" + username + "' WHERE user_id=" + uid + "");
                    } else {
                        JOptionPane.showMessageDialog(this, "Username Already Exist");
                    }
                }

                if (!password.isEmpty()) {
                    if (password.equals(confirm)) {
                        DatabaseConnection.getConnection().executeUpdate("UPDATE login SET password='" + password + "' WHERE user_id=" + uid + "");
                    } else {
                        JOptionPane.showMessageDialog(this, "Passwords Are Not Match");
                    }
                }

                if (access) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE login SET type='Manager' WHERE user_id=" + uid + "");
                } else {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE login SET type='Cashier' WHERE user_id=" + uid + "");
                }

                if (status) {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE login SET status=1 WHERE user_id=" + uid + "");
                } else {
                    DatabaseConnection.getConnection().executeUpdate("UPDATE login SET status=0 WHERE user_id=" + uid + "");
                }

                JOptionPane.showMessageDialog(this, "User Information Updated");
                confirmationPasswordField.setForeground(Color.gray);
                confirmationPasswordField.setEchoChar((char) 0);
                confirmationPasswordField.setText("Confirm Password");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_saveUserButtonActionPerformed

    private void genderInUMTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderInUMTextFieldFocusLost
        if (genderInUMTextField.getText().equals("m") || genderInUMTextField.getText().equals("M")) {
            genderInUMTextField.setText("Male");
        } else if (genderInUMTextField.getText().equals("f") || genderInUMTextField.getText().equals("F")) {
            genderInUMTextField.setText("Female");
        }
    }//GEN-LAST:event_genderInUMTextFieldFocusLost

    private void phoneInUMTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneInUMTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }

        int i = phoneInUMTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_phoneInUMTextFieldKeyTyped

    private void userRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userRefreshButtonActionPerformed
        userListLoad();
    }//GEN-LAST:event_userRefreshButtonActionPerformed

    private void searchInUMTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchInUMTextFieldKeyTyped
        if (searchInUMTextField.getForeground() == Color.gray) {
            searchInUMTextField.setText(null);
        }
        searchInUMTextField.setForeground(Color.black);

        DefaultListModel dlm = new DefaultListModel();
        String s = searchInUMTextField.getText();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM user WHERE name LIKE '%" + s + "%'");
            while (rs.next()) {
                String uid = rs.getString("user_id");
                String userName = rs.getString("name");
                String listItem = uid + " | " + userName;
                dlm.addElement(listItem);
            }
            userList.setModel(dlm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_searchInUMTextFieldKeyTyped

    private void searchInUMTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchInUMTextFieldFocusLost
        if (searchInUMTextField.getText().isEmpty()) {
            searchInUMTextField.setForeground(Color.gray);
            searchInUMTextField.setText("Search Names");
        }
    }//GEN-LAST:event_searchInUMTextFieldFocusLost

    private void searchInUMTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchInUMTextFieldMouseClicked
        searchInUMTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (searchInUMTextField.getForeground() == Color.gray) {
            searchInUMTextField.setText(null);
        }
    }//GEN-LAST:event_searchInUMTextFieldMouseClicked

    private void refereshOrderDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refereshOrderDataButtonActionPerformed
        analyticsDataLoad();
    }//GEN-LAST:event_refereshOrderDataButtonActionPerformed

    private void mealsTableInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_mealsTableInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_mealsTableInputMethodTextChanged

    private void orderByDateTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderByDateTextFieldKeyTyped

    }//GEN-LAST:event_orderByDateTextFieldKeyTyped

    private void orderByDateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderByDateTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        String date = orderByDateTextField.getText();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` WHERE date LIKE '" + date + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                dtm.addRow(v);
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_orderByDateTextFieldKeyReleased

    private void orderByOIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderByOIDTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        int oid = Integer.parseInt(orderByOIDTextField.getText());
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` WHERE order_id LIKE '" + oid + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                dtm.addRow(v);
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_orderByOIDTextFieldKeyReleased

    private void orderByUIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderByUIDTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        int uid = Integer.parseInt(orderByUIDTextField.getText());
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` WHERE user_id LIKE '" + uid + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                dtm.addRow(v);
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_orderByUIDTextFieldKeyReleased

    private void orderByCIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_orderByCIDTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        int cid = Integer.parseInt(orderByCIDTextField.getText());
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` WHERE customer_id LIKE '" + cid + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                dtm.addRow(v);
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_orderByCIDTextFieldKeyReleased

    private void ordreByPHTTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ordreByPHTTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        Double profit = Double.parseDouble(ordreByPHTTextField.getText());
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order`");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                if (Double.parseDouble(v.get(5).toString()) >= profit) {
                    dtm.addRow(v);
                }
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ordreByPHTTextFieldKeyReleased

    private void ordreByPLTTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ordreByPLTTextFieldKeyReleased
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        Double profit = Double.parseDouble(ordreByPLTTextField.getText());
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order`");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount * 10 / 11) * 15 / 100) + (amount - (amount * 10 / 11)));
                if (Double.parseDouble(v.get(5).toString()) <= profit) {
                    dtm.addRow(v);
                }
            }

            Double tp = 0.00;
            for (int i = 0; i <= dtm.getRowCount() - 1; i++) {
                tp += Double.parseDouble(dtm.getValueAt(i, 5).toString());
            }
            totalProfitTextField.setText(tp + " LKR");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ordreByPLTTextFieldKeyReleased

    private void ordersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersTableMouseClicked
        int selectedRow = ordersTable.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();
        DefaultListModel dlm = new DefaultListModel();

        int oid = Integer.parseInt(dtm.getValueAt(selectedRow, 1).toString());
        int cid = Integer.parseInt(dtm.getValueAt(selectedRow, 3).toString());

        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM customer WHERE customer_id = " + cid + "");
            while (rs1.next()) {
                String cName = rs1.getString("name");
                String phone = rs1.getString("phone");
                String email = rs1.getString("email");
                String listItem1 = "Customer : " + cName;
                String listItem2 = "Phone : " + phone;
                String listItem3 = "Email : " + email;
                dlm.addElement("--------------------------- CUSTOMER DETAILS ---------------------------");
                dlm.addElement(listItem1);
                dlm.addElement(listItem2);
                dlm.addElement(listItem3);
                dlm.addElement("");
                dlm.addElement("");
                dlm.addElement("------------------------------ MEALS DETAILS ------------------------------");
            }

            ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM order_has_meal WHERE order_id = " + oid + "");
            while (rs2.next()) {
                String mid = rs2.getString("meal_id");
                String qty = rs2.getString("qty");
                String m = "";
                String up = "";
                ResultSet rs21 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM meal WHERE meal_id = " + mid + "");
                while (rs21.next()) {
                    m = rs21.getString("name");
                    up = rs21.getString("unit_price");
                }
                String listMealItem = qty + " " + m + "(s) (" + up + " LKR)";
                dlm.addElement(listMealItem);
            }
            ResultSet rs3 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM order_has_drink WHERE order_id = " + oid + "");
            while (rs3.next()) {
                String drid = rs3.getString("drink_id");
                String qty = rs3.getString("qty");
                String d = "";
                String up = "";
                ResultSet rs31 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM drink WHERE drink_id = " + drid + "");
                while (rs31.next()) {
                    d = rs31.getString("name");
                    up = rs31.getString("unit_price");
                }
                String listDrinkItem = qty + " " + d + "(s) (" + up + " LKR)";
                dlm.addElement(listDrinkItem);
            }
            ResultSet rs4 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM order_has_dessert WHERE order_id = " + oid + "");
            while (rs4.next()) {
                String did = rs4.getString("dessert_id");
                String qty = rs4.getString("qty");
                String d = "";
                String up = "";
                ResultSet rs41 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM dessert WHERE dessert_id = " + did + "");
                while (rs41.next()) {
                    d = rs41.getString("name");
                    up = rs41.getString("unit_price");
                }
                String listDessertItem = qty + " " + d + "(s) (" + up + " LKR)";
                dlm.addElement(listDessertItem);
            }

            orderDetailsList.setModel(dlm);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_ordersTableMouseClicked

    private void itemSearchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemSearchTextFieldFocusLost
        if (itemSearchTextField.getText().isEmpty()) {
            itemSearchTextField.setForeground(Color.gray);
            itemSearchTextField.setText("Search Items");
        }
    }//GEN-LAST:event_itemSearchTextFieldFocusLost

    private void itemSearchTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemSearchTextFieldMouseClicked
        itemSearchTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (itemSearchTextField.getForeground() == Color.gray) {
            itemSearchTextField.setText(null);
        }
    }//GEN-LAST:event_itemSearchTextFieldMouseClicked

    private void itemSearchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemSearchTextFieldKeyTyped
        if (itemSearchTextField.getForeground() == Color.gray) {
            itemSearchTextField.setText(null);
        }
        itemSearchTextField.setForeground(Color.black);

        DefaultTableModel dtm = (DefaultTableModel) stockTable.getModel();
        String s = itemSearchTextField.getText();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM item WHERE name LIKE '%" + s + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("item_id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("qty"));
                v.add(rs.getString("unit_price"));
                dtm.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_itemSearchTextFieldKeyTyped

    private void stockRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockRefreshButtonActionPerformed
        itemSearchTextField.setForeground(Color.gray);
        itemSearchTextField.setText("Search Items");
        reset();
        loadID();
        stockDataLoad();
    }//GEN-LAST:event_stockRefreshButtonActionPerformed

    private void stockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockTableMouseClicked
        int selectedRow = stockTable.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) stockTable.getModel();

        int iid = Integer.parseInt(dtm.getValueAt(selectedRow, 0).toString());
        itemIDTextField.setText(iid + "");

        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM item WHERE item_id = " + iid + "");
            while (rs1.next()) {
                grnItemTextField.setText(rs1.getString("name"));
                grnUnitPriceTextField.setText(rs1.getString("unit_price"));
                String sid = rs1.getString("supplier_id");
                ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM supplier WHERE supplier_id = " + sid + "");
                while (rs2.next()) {
                    supplierIDTextField.setText(sid);
                    grnSupplierTextField.setText(rs2.getString("name"));
                }

                rs2.close();
            }

            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_stockTableMouseClicked

    private void grnSupplierEmailTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierEmailTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnSupplierEmailTextFieldKeyTyped

    private void grnSupplierEmailTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierEmailTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnSupplierEmailTextFieldKeyReleased

    private void grnSupplierPhoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierPhoneTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }

        int i = grnSupplierPhoneTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_grnSupplierPhoneTextFieldKeyTyped

    private void grnSupplierPhoneTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierPhoneTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnSupplierPhoneTextFieldKeyReleased

    private void addNewItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewItemButtonActionPerformed
        String date = grnDateTextField.getText();
        String sid = supplierIDTextField.getText();
        String iid = itemIDTextField.getText();
        String item = grnItemTextField.getText();
        String unitPrice = grnUnitPriceTextField.getText();
        String qty = grnQtyTextField.getText();
        String exp = grnExpTextField.getText().isEmpty() ? "Unlimited" : grnExpTextField.getText();
        String total = grnTotalTextField.getText();
        String grnid = grnidLabel.getText();
        
        try {
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO item (item_id, name, qty, unit_price, supplier_id) VALUES (" + iid + ",'" + item + "', " + qty + ", '" + unitPrice + "', " + sid + ")");
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO grn (grn_id, date, qty, total, supplier_id, item_id, user_id) VALUES (" + grnid + ",'" + date + "', " + qty + ", '" + total + "', " + sid + ", " + iid + ","+useridLoad()+")");
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO item_batch (batch_id, batch_qty, rec_date, exp_date, item_id) VALUES (" + grnid + ", " + qty + ",'" + date + "', '" + exp + "', " + iid + ")");
            JOptionPane.showMessageDialog(this, "New Item Added");
        } catch (Exception e) {
            System.out.println(e);   
        }
    }//GEN-LAST:event_addNewItemButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        String date = grnDateTextField.getText();
        String sid = supplierIDTextField.getText();
        String iid = itemIDTextField.getText();
        DefaultTableModel dtm = (DefaultTableModel) stockTable.getModel();
        int cqty = Integer.parseInt(dtm.getValueAt(stockTable.getSelectedRow(), 2).toString());
        int qty = Integer.parseInt(grnQtyTextField.getText());
        int tqty = cqty+qty;
        String exp = grnExpTextField.getText().isEmpty() ? "Unlimited" : grnExpTextField.getText();
        String total = grnTotalTextField.getText();
        String grnid = grnidLabel.getText();
        
        try {
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO grn (grn_id, date, qty, total, supplier_id, item_id, user_id) VALUES (" + grnid + ",'" + date + "', " + qty + ", '" + total + "', " + sid + ", " + iid + ","+useridLoad()+")");
            DatabaseConnection.getConnection().executeUpdate("UPDATE item SET qty=" + tqty + " WHERE item_id="+iid+"");
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO item_batch (batch_id, batch_qty, rec_date, exp_date, item_id) VALUES (" + grnid + ", " + qty + ",'" + date + "', '" + exp + "', " + iid + ")");
            JOptionPane.showMessageDialog(this, "Item Added to the Stock");
        } catch (Exception e) {
            System.out.println(e);   
        }

    }//GEN-LAST:event_addItemButtonActionPerformed

    private void newSupplierTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newSupplierTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_newSupplierTextFieldKeyTyped

    private void newSupplierTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newSupplierTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_newSupplierTextFieldKeyReleased

    private void newSupplierIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newSupplierIDTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_newSupplierIDTextFieldKeyTyped

    private void newSupplierIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newSupplierIDTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_newSupplierIDTextFieldKeyReleased

    private void grnUnitPriceTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnUnitPriceTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnUnitPriceTextFieldKeyTyped

    private void grnUnitPriceTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnUnitPriceTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnUnitPriceTextFieldKeyReleased

    private void grnDateTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnDateTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnDateTextFieldKeyTyped

    private void grnDateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnDateTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnDateTextFieldKeyReleased

    private void grnItemTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnItemTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnItemTextFieldKeyTyped

    private void grnItemTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnItemTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnItemTextFieldKeyReleased

    private void grnTotalTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnTotalTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnTotalTextFieldKeyTyped

    private void grnTotalTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnTotalTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnTotalTextFieldKeyReleased

    private void grnQtyTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnQtyTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnQtyTextFieldKeyTyped

    private void grnQtyTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnQtyTextFieldKeyReleased
        double up = Double.parseDouble(grnUnitPriceTextField.getText());
        double qty = Double.parseDouble(grnQtyTextField.getText());

        double total = up * qty;
        grnTotalTextField.setText(total + "");
    }//GEN-LAST:event_grnQtyTextFieldKeyReleased

    private void itemIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemIDTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDTextFieldKeyTyped

    private void itemIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemIDTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_itemIDTextFieldKeyReleased

    private void supplierIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplierIDTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIDTextFieldKeyReleased

    private void supplierIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplierIDTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIDTextFieldKeyTyped

    private void grnSupplierTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnSupplierTextFieldKeyReleased

    private void grnSupplierTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnSupplierTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnSupplierTextFieldKeyTyped

    private void addSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierButtonActionPerformed
        String sid = newSupplierIDTextField.getText();
        String name = newSupplierTextField.getText();
        String phone = grnSupplierPhoneTextField.getText();
        String email = grnSupplierEmailTextField.getText();
        
        try {
            DatabaseConnection.getConnection().executeUpdate("INSERT INTO supplier (supplier_id, name, phone, email) VALUES (" + sid + ",'" + name + "', '" + phone + "', '" + email + "')");
            JOptionPane.showMessageDialog(this, "New Supplier Added");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_addSupplierButtonActionPerformed

    private void updateSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSupplierButtonActionPerformed
        String sid = newSupplierIDTextField.getText();
        String name = newSupplierTextField.getText();
        String phone = grnSupplierPhoneTextField.getText();
        String email = grnSupplierEmailTextField.getText();
        
        try {
            DatabaseConnection.getConnection().executeUpdate("UPDATE supplier SET name='"+name+"', phone='"+phone+"', email='"+email+"' WHERE supplier_id="+sid+"");
            JOptionPane.showMessageDialog(this, "Supplier Details Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_updateSupplierButtonActionPerformed

    private void supplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierTableMouseClicked
        int selectedRow = supplierTable.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) supplierTable.getModel();

        int sid = Integer.parseInt(dtm.getValueAt(selectedRow, 0).toString());
        newSupplierIDTextField.setText(sid + "");

        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM supplier WHERE supplier_id = " + sid + "");
            while (rs1.next()) {
                newSupplierTextField.setText(rs1.getString("name"));
                grnSupplierPhoneTextField.setText(rs1.getString("phone"));
                grnSupplierEmailTextField.setText(rs1.getString("email"));
            }

            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_supplierTableMouseClicked

    private void supplierSearchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_supplierSearchTextFieldFocusLost
        if (supplierSearchTextField.getText().isEmpty()) {
            supplierSearchTextField.setForeground(Color.gray);
            supplierSearchTextField.setText("Search Items");
        }
    }//GEN-LAST:event_supplierSearchTextFieldFocusLost

    private void supplierSearchTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierSearchTextFieldMouseClicked
        supplierSearchTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (supplierSearchTextField.getForeground() == Color.gray) {
            supplierSearchTextField.setText(null);
        }
    }//GEN-LAST:event_supplierSearchTextFieldMouseClicked

    private void supplierSearchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_supplierSearchTextFieldKeyTyped
        if (supplierSearchTextField.getForeground() == Color.gray) {
            supplierSearchTextField.setText(null);
        }
        supplierSearchTextField.setForeground(Color.black);

        DefaultTableModel dtm = (DefaultTableModel) supplierTable.getModel();
        String s = supplierSearchTextField.getText();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM supplier WHERE name LIKE '%" + s + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("supplier_id"));
                v.add(rs.getString("name"));
                dtm.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_supplierSearchTextFieldKeyTyped

    private void supplierRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierRefreshButtonActionPerformed
        supplierSearchTextField.setForeground(Color.gray);
        supplierSearchTextField.setText("Search Items");
        reset();
        supplierDataLoad();
        loadID();
    }//GEN-LAST:event_supplierRefreshButtonActionPerformed

    private void addItemButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButton1ActionPerformed
        new GRNList().setVisible(true);
    }//GEN-LAST:event_addItemButton1ActionPerformed

    private void inUMPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inUMPasswordFieldFocusGained
        inUMPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        inUMPasswordField.setText(null);
    }//GEN-LAST:event_inUMPasswordFieldFocusGained

    private void inUMPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inUMPasswordFieldFocusLost
        int i = inUMPasswordField.getText().length();
        if (i >= 8) {
            inUMPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#32CD32")));
        } else if (i < 8) {
            inUMPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
            inUMPasswordField.setToolTipText("Password Must Have More Than 8 Characters");
        }
    }//GEN-LAST:event_inUMPasswordFieldFocusLost

    private void inUMConfirmPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inUMConfirmPasswordFieldFocusGained
        inUMConfirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        inUMConfirmPasswordField.setText(null);
    }//GEN-LAST:event_inUMConfirmPasswordFieldFocusGained

    private void inUMConfirmPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inUMConfirmPasswordFieldFocusLost
        int i = inUMConfirmPasswordField.getText().length();
        String s = inUMConfirmPasswordField.getText();
        if (i >= 8 && s.equals(inUMPasswordField.getText())) {
            inUMConfirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#32CD32")));
        } else if (i < 8) {
                inUMConfirmPasswordField.setToolTipText("Password Must Have More Than 8 Characters");
                inUMConfirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
        } else if (!(s.equals(inUMPasswordField.getText()))) {
            inUMConfirmPasswordField.setToolTipText("Passwords are Not Matching");
            inUMConfirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
        }   
    }//GEN-LAST:event_inUMConfirmPasswordFieldFocusLost

    private void dobInUMTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobInUMTextFieldKeyTyped
        int i = dobInUMTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_dobInUMTextFieldKeyTyped

    private void stockInKitchenTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockInKitchenTableMouseClicked
        int selectedRow = stockInKitchenTable.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) stockInKitchenTable.getModel();

        int iid = Integer.parseInt(dtm.getValueAt(selectedRow, 0).toString());
        kitchenItemIDTextField.setText(iid + "");

        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT * FROM item WHERE item_id = " + iid + "");
            while (rs1.next()) {
                kitchenItemTextField.setText(rs1.getString("name"));
            }

            rs1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_stockInKitchenTableMouseClicked

    private void itemSearchInKitchenTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemSearchInKitchenTextFieldFocusLost
        if (itemSearchInKitchenTextField.getText().isEmpty()) {
            itemSearchInKitchenTextField.setForeground(Color.gray);
            itemSearchInKitchenTextField.setText("Search Items");
        }
    }//GEN-LAST:event_itemSearchInKitchenTextFieldFocusLost

    private void itemSearchInKitchenTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemSearchInKitchenTextFieldMouseClicked
        itemSearchInKitchenTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (itemSearchInKitchenTextField.getForeground() == Color.gray) {
            itemSearchInKitchenTextField.setText(null);
        }
    }//GEN-LAST:event_itemSearchInKitchenTextFieldMouseClicked

    private void itemSearchInKitchenTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemSearchInKitchenTextFieldKeyTyped
        if (itemSearchInKitchenTextField.getForeground() == Color.gray) {
            itemSearchInKitchenTextField.setText(null);
        }
        itemSearchInKitchenTextField.setForeground(Color.black);

        DefaultTableModel dtm = (DefaultTableModel) stockInKitchenTable.getModel();
        String s = itemSearchInKitchenTextField.getText();
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM item WHERE name LIKE '%" + s + "%'");
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("item_id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("qty"));
                v.add(rs.getString("unit_price"));
                dtm.addRow(v);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_itemSearchInKitchenTextFieldKeyTyped

    private void stockRefreshInKitchenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockRefreshInKitchenButtonActionPerformed
        itemSearchTextField.setForeground(Color.gray);
        itemSearchTextField.setText("Search Items");
        reset();
        loadID();
        stockDataLoad();
    }//GEN-LAST:event_stockRefreshInKitchenButtonActionPerformed

    private void kitchenItemIDTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenItemIDTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenItemIDTextFieldKeyReleased

    private void kitchenItemIDTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenItemIDTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenItemIDTextFieldKeyTyped

    private void kitchenItemTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenItemTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenItemTextFieldKeyReleased

    private void kitchenItemTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenItemTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenItemTextFieldKeyTyped

    private void kitchenDateTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenDateTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenDateTextFieldKeyReleased

    private void kitchenDateTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenDateTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenDateTextFieldKeyTyped

    private void kitchenQtyTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenQtyTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenQtyTextFieldKeyReleased

    private void kitchenQtyTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kitchenQtyTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kitchenQtyTextFieldKeyTyped

    private void getItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getItemButtonActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) stockInKitchenTable.getModel();
        String rid = kridLabel.getText();
        String date = kitchenDateTextField.getText();
        String iid = kitchenItemIDTextField.getText();
        int cqty = Integer.parseInt(dtm.getValueAt(stockInKitchenTable.getSelectedRow(), 2).toString());
        int qty = Integer.parseInt(kitchenQtyTextField.getText());
        int tqty = cqty-qty;
        
        if(cqty >= qty){
            try {
                Statement s1 = DatabaseConnection.getConnection();
                s1.executeUpdate("INSERT INTO kitchen_records (record_id, date, qty, item_id, user_id) VALUES ("+rid+", '"+date+"', "+qty+", "+iid+", "+useridLoad()+")");
            
                Statement s2 = DatabaseConnection.getConnection();
                s2.executeUpdate("UPDATE item SET qty="+tqty+" WHERE item_id="+iid+"");
            
                s1.close();
                s2.close();
                
                JOptionPane.showMessageDialog(this, "Items Transfered to Kitchen");
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Not Enough Amount of This Item in Kitchen");
        }
    }//GEN-LAST:event_getItemButtonActionPerformed

    private void grnExpTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnExpTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_grnExpTextFieldKeyReleased

    private void grnExpTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grnExpTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_grnExpTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(ManagerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activeInUMCheckBox;
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton addItemButton1;
    private javax.swing.JButton addNewItemButton;
    private javax.swing.JButton addSupplierButton;
    private javax.swing.JButton addUserButton;
    private javax.swing.JLabel addressInUMLabel;
    private javax.swing.JTextField addressInUMTextField;
    private javax.swing.JPanel analyticsPanel;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JTextField balanceTextField;
    private javax.swing.JCheckBox beefBurger;
    private javax.swing.JTextField beefBurgerCount;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel casualMealsLabel;
    private javax.swing.JPanel casualMealsPanel;
    private javax.swing.JCheckBox cheeseBurger;
    private javax.swing.JTextField cheeseBurgerCount;
    private javax.swing.JCheckBox chickenBurger;
    private javax.swing.JTextField chickenBurgerCount;
    private javax.swing.JCheckBox chickenNuggets;
    private javax.swing.JTextField chickenNuggetsCount;
    private javax.swing.JCheckBox chocolateCone;
    private javax.swing.JTextField chocolateConeCount;
    private javax.swing.JCheckBox chocolateSundaes;
    private javax.swing.JTextField chocolateSundaesCount;
    private javax.swing.JLabel cidLabel;
    private javax.swing.JCheckBox cocacolaL;
    private javax.swing.JTextField cocacolaLCount;
    private javax.swing.JCheckBox cocacolaS;
    private javax.swing.JTextField cocacolaSCount;
    private javax.swing.JPasswordField confirmationPasswordField;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel customerIDLabel;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dessertLabel;
    private javax.swing.JPanel dessertPanel;
    private javax.swing.JLabel dobInUMLabel;
    private javax.swing.JTextField dobInUMTextField;
    private javax.swing.JLabel drinksLabel;
    private javax.swing.JPanel drinksPanel;
    private javax.swing.JToggleButton editInUMToggleButton;
    private javax.swing.JLabel emailInUMLabel;
    private javax.swing.JTextField emailInUMTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel filterByCashierLabel;
    private javax.swing.JLabel filterByCustomerLabel;
    private javax.swing.JLabel filterByDateLabel;
    private javax.swing.JLabel filterByOrderLabel;
    private javax.swing.JLabel filterByPHTLabel;
    private javax.swing.JLabel filterByPLTLabel;
    private javax.swing.JPanel filterOrdersPanel;
    private javax.swing.JCheckBox frenchFries;
    private javax.swing.JTextField frenchFriesCount;
    private javax.swing.JLabel genderInUMLabel;
    private javax.swing.JTextField genderInUMTextField;
    private javax.swing.JPanel getCustomerDetailsPanel;
    private javax.swing.JButton getItemButton;
    private javax.swing.JLabel grnDateLabel;
    private javax.swing.JTextField grnDateTextField;
    private javax.swing.JLabel grnExpLabel;
    private javax.swing.JTextField grnExpTextField;
    private javax.swing.JLabel grnItemIDLabel;
    private javax.swing.JLabel grnItemLabel;
    private javax.swing.JLabel grnItemReceiveLabel2;
    private javax.swing.JLabel grnItemReceiveLabel3;
    private javax.swing.JTextField grnItemTextField;
    private javax.swing.JPanel grnMainPanel;
    private javax.swing.JLabel grnNoLabel;
    private javax.swing.JPanel grnPanel;
    private javax.swing.JLabel grnQtyLabel;
    private javax.swing.JTextField grnQtyTextField;
    private javax.swing.JLabel grnSupplierEmailLabel;
    private javax.swing.JTextField grnSupplierEmailTextField;
    private javax.swing.JLabel grnSupplierIDLabel;
    private javax.swing.JLabel grnSupplierIDLabel1;
    private javax.swing.JLabel grnSupplierLabel;
    private javax.swing.JLabel grnSupplierLabel1;
    private javax.swing.JLabel grnSupplierPhoneLabel;
    private javax.swing.JTextField grnSupplierPhoneTextField;
    private javax.swing.JTextField grnSupplierTextField;
    private javax.swing.JLabel grnTotalLabel;
    private javax.swing.JTextField grnTotalTextField;
    private javax.swing.JLabel grnUnitPriceLabel;
    private javax.swing.JTextField grnUnitPriceTextField;
    private javax.swing.JLabel grnidLabel;
    private javax.swing.JLabel hiLabel;
    private javax.swing.JCheckBox hotChocolate;
    private javax.swing.JTextField hotChocolateCount;
    private javax.swing.JCheckBox iceCoffee;
    private javax.swing.JTextField iceCoffeeCount;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPasswordField inUMConfirmPasswordField;
    private javax.swing.JPasswordField inUMPasswordField;
    private javax.swing.JTextField itemIDTextField;
    private javax.swing.JTextField itemSearchInKitchenTextField;
    private javax.swing.JTextField itemSearchTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel kitchenDateLabel;
    private javax.swing.JTextField kitchenDateTextField;
    private javax.swing.JLabel kitchenItemIDLabel;
    private javax.swing.JTextField kitchenItemIDTextField;
    private javax.swing.JLabel kitchenItemLabel;
    private javax.swing.JTextField kitchenItemTextField;
    private javax.swing.JPanel kitchenManagementPanel;
    private javax.swing.JLabel kitchenQtyLabel;
    private javax.swing.JTextField kitchenQtyTextField;
    private javax.swing.JLabel kridLabel;
    private javax.swing.JCheckBox lavaCake;
    private javax.swing.JTextField lavaCakeCount;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel makeOrdersPanel;
    private javax.swing.JCheckBox managementInUMCheckBox;
    private javax.swing.JPanel mealsPanel;
    private javax.swing.JScrollPane mealsTabelPanel;
    private javax.swing.JTable mealsTable;
    private javax.swing.JCheckBox milkShake;
    private javax.swing.JTextField milkShakeCount;
    private javax.swing.JLabel nameInUMLabel;
    private javax.swing.JTextField nameInUMTextField;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTabbedPane navPanel;
    private javax.swing.JTextField newSupplierIDTextField;
    private javax.swing.JTextField newSupplierTextField;
    private javax.swing.JCheckBox nonvegSubmarine;
    private javax.swing.JTextField nonvegSubmarineCount;
    private javax.swing.JLabel oidLabel;
    private javax.swing.JTextField orderByCIDTextField;
    private javax.swing.JTextField orderByDateTextField;
    private javax.swing.JTextField orderByOIDTextField;
    private javax.swing.JTextField orderByUIDTextField;
    private javax.swing.JLabel orderDetailsLabel;
    private javax.swing.JList<String> orderDetailsList;
    private javax.swing.JScrollPane orderDetailsPanel;
    private javax.swing.JLabel orderNoLabel;
    private javax.swing.JLabel orderNoLabel1;
    private javax.swing.JLabel orderNoLabel2;
    private javax.swing.JScrollPane ordersTabelPanel1;
    private javax.swing.JTable ordersTable;
    private javax.swing.JLabel ordersTableLabel;
    private javax.swing.JLabel ordersTableLabel1;
    private javax.swing.JPanel ordersTablePanel;
    private javax.swing.JTextField ordreByPHTTextField;
    private javax.swing.JTextField ordreByPLTTextField;
    private javax.swing.JLabel passwordInUMLabel;
    private javax.swing.JLabel passwordInUMLabel1;
    private javax.swing.JLabel phoneInUMLabel;
    private javax.swing.JTextField phoneInUMTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton proceedButton;
    private javax.swing.JPanel receiveItemsPanel;
    private javax.swing.JLabel recievedLabel;
    private javax.swing.JTextField recievedTextField;
    private javax.swing.JLabel recordIDLabel;
    private javax.swing.JButton refereshOrderDataButton;
    private javax.swing.JButton saveUserButton;
    private javax.swing.JTextField searchInUMTextField;
    private javax.swing.JLabel serviceChargesLabel;
    private javax.swing.JTextField serviceChargesTextField;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JCheckBox spriteL;
    private javax.swing.JTextField spriteLCount;
    private javax.swing.JCheckBox spriteS;
    private javax.swing.JTextField spriteSCount;
    private javax.swing.JPanel stockDetailsPanel;
    private javax.swing.JPanel stockDetailsPanel1;
    private javax.swing.JPanel stockDetailsPanel2;
    private javax.swing.JTable stockInKitchenTable;
    private javax.swing.JButton stockRefreshButton;
    private javax.swing.JButton stockRefreshInKitchenButton;
    private javax.swing.JScrollPane stockTabelPanel;
    private javax.swing.JScrollPane stockTabelPanel1;
    private javax.swing.JScrollPane stockTabelPanel2;
    private javax.swing.JTable stockTable;
    private javax.swing.JLabel stockTableLabel;
    private javax.swing.JLabel stockTableLabel1;
    private javax.swing.JLabel stockTableLabel2;
    private javax.swing.JLabel stockTableLabel3;
    private javax.swing.JCheckBox strawberryCone;
    private javax.swing.JTextField strawberryConeCount;
    private javax.swing.JPanel supplierDetailsPanel;
    private javax.swing.JTextField supplierIDTextField;
    private javax.swing.JButton supplierRefreshButton;
    private javax.swing.JTextField supplierSearchTextField;
    private javax.swing.JTable supplierTable;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalProfitLabel;
    private javax.swing.JTextField totalProfitTextField;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JLabel uidInUMLabel;
    private javax.swing.JButton updateSupplierButton;
    private javax.swing.JLabel userDetailsLabel;
    private javax.swing.JPanel userDetailsPanel;
    private javax.swing.JList<String> userList;
    private javax.swing.JLabel userListLabel;
    private javax.swing.JLabel userListLabel1;
    private javax.swing.JScrollPane userListScrollPane;
    private javax.swing.JPanel userManagementPanel;
    private javax.swing.JButton userRefreshButton;
    private javax.swing.JLabel usernameInUMLabel;
    private javax.swing.JTextField usernameInUMTextField;
    private javax.swing.JCheckBox vanilaCone;
    private javax.swing.JTextField vanilaConeCount;
    private javax.swing.JCheckBox vanilaSundaes;
    private javax.swing.JTextField vanilaSundaesCount;
    private javax.swing.JCheckBox vegSubmarine;
    private javax.swing.JTextField vegSubmarineCount;
    // End of variables declaration//GEN-END:variables
}
