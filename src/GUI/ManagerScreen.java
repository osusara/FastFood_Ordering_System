package GUI;

import Database.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        confirmationPasswordField.setEchoChar((char) 0);
        confirmationPasswordField.setForeground(Color.gray);
        searchInUMTextField.setForeground(Color.gray);
    }

    public void loadID() {
        try {
            ResultSet rs1 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('order_id') AS C FROM `restaurentsystem`.`order`");
            ResultSet rs2 = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('customer_id') AS D FROM `restaurentsystem`.`customer`");
            if (rs1.next()) {
                int i = rs1.getInt("C");
                oidLabel.setText("" + (++i));
            }
            if (rs2.next()) {
                int i = rs2.getInt("D");
                cidLabel.setText("" + (++i));
            }
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
    
    public void analyticsDataLoad(){
        DefaultTableModel dtm = (DefaultTableModel) ordersTable.getModel();;
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM `restaurentsystem`.`order` ORDER BY order_id DESC");
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("date"));
                v.add(rs.getString("order_id"));
                v.add(rs.getString("user_id"));
                v.add(rs.getString("customer_id"));
                Double amount = Double.parseDouble(rs.getString("total"));
                v.add(amount);
                v.add(((amount*10/11)*15/100)+(amount-(amount*10/11)));
                dtm.addRow(v);
            }
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
        userManagementPanel = new javax.swing.JPanel();
        userListScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
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
        userDetailsLabel = new javax.swing.JLabel();
        controlPanel = new javax.swing.JPanel();
        confirmationPasswordField = new javax.swing.JPasswordField();
        saveUserButton = new javax.swing.JButton();
        addUserButton = new javax.swing.JButton();
        userListLabel1 = new javax.swing.JLabel();
        saveUserButton1 = new javax.swing.JButton();
        searchInUMTextField = new javax.swing.JTextField();
        AnalyticsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        userListLabel2 = new javax.swing.JLabel();
        saveUserButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurent Orders Management System | Home");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));

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
                .addGap(58, 58, 58)
                .addComponent(logoutButton)
                .addContainerGap(61, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 464, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(35, 35, 35))
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
                .addGap(40, 40, 40)
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
                .addGap(81, 81, 81))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(13, 13, 13)
                .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        makeOrdersPanelLayout.setVerticalGroup(
            makeOrdersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makeOrdersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mealsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        navPanel.addTab("Make Orders", new javax.swing.ImageIcon(getClass().getResource("/Resources/food.png")), makeOrdersPanel, ""); // NOI18N

        userManagementPanel.setBackground(new java.awt.Color(255, 255, 255));
        userManagementPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userManagementPanel.setInheritsPopupMenu(true);

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

        passwordInUMLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        passwordInUMLabel.setText("Password");

        inUMConfirmPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        inUMConfirmPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inUMConfirmPasswordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        inUMConfirmPasswordField.setEnabled(false);

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
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        userDetailsLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userDetailsLabel.setText("User Details");

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
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmationPasswordField)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(userListLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        saveUserButton1.setBackground(new java.awt.Color(255, 255, 255));
        saveUserButton1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        saveUserButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        saveUserButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveUserButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout userManagementPanelLayout = new javax.swing.GroupLayout(userManagementPanel);
        userManagementPanel.setLayout(userManagementPanelLayout);
        userManagementPanelLayout.setHorizontalGroup(
            userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userManagementPanelLayout.createSequentialGroup()
                .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userManagementPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userListLabel)
                            .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(userManagementPanelLayout.createSequentialGroup()
                                    .addComponent(searchInUMTextField)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(saveUserButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(userListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(userManagementPanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userDetailsLabel))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        userManagementPanelLayout.setVerticalGroup(
            userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userManagementPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userManagementPanelLayout.createSequentialGroup()
                        .addComponent(userDetailsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(userManagementPanelLayout.createSequentialGroup()
                        .addComponent(userListLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(saveUserButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchInUMTextField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        navPanel.addTab("User Management", new javax.swing.ImageIcon(getClass().getResource("/Resources/userManagement.png")), userManagementPanel); // NOI18N

        AnalyticsPanel.setBackground(new java.awt.Color(255, 255, 255));
        AnalyticsPanel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        ordersTable.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Order ID", "Cashier ID", "Customer ID", "Amount", "Profit"
            }
        ));
        ordersTable.setGridColor(new java.awt.Color(153, 153, 153));
        ordersTable.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jScrollPane1.setViewportView(ordersTable);

        userListLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userListLabel2.setText("Order Details");

        saveUserButton2.setBackground(new java.awt.Color(255, 255, 255));
        saveUserButton2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        saveUserButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/refresh.png"))); // NOI18N
        saveUserButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveUserButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AnalyticsPanelLayout = new javax.swing.GroupLayout(AnalyticsPanel);
        AnalyticsPanel.setLayout(AnalyticsPanelLayout);
        AnalyticsPanelLayout.setHorizontalGroup(
            AnalyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnalyticsPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(AnalyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userListLabel2)
                    .addGroup(AnalyticsPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveUserButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(291, Short.MAX_VALUE))
        );
        AnalyticsPanelLayout.setVerticalGroup(
            AnalyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AnalyticsPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(userListLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AnalyticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveUserButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(328, Short.MAX_VALUE))
        );

        navPanel.addTab("Analytics", new javax.swing.ImageIcon(getClass().getResource("/Resources/analytics.png")), AnalyticsPanel); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(navPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(sidePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        navPanel.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        new UserRegistration().setVisible(true);
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

    private void saveUserButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveUserButton1ActionPerformed
        userListLoad();
    }//GEN-LAST:event_saveUserButton1ActionPerformed

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

    private void saveUserButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveUserButton2ActionPerformed
        analyticsDataLoad();
    }//GEN-LAST:event_saveUserButton2ActionPerformed

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
    private javax.swing.JPanel AnalyticsPanel;
    private javax.swing.JCheckBox activeInUMCheckBox;
    private javax.swing.JButton addUserButton;
    private javax.swing.JLabel addressInUMLabel;
    private javax.swing.JTextField addressInUMTextField;
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
    private javax.swing.JCheckBox frenchFries;
    private javax.swing.JTextField frenchFriesCount;
    private javax.swing.JLabel genderInUMLabel;
    private javax.swing.JTextField genderInUMTextField;
    private javax.swing.JPanel getCustomerDetailsPanel;
    private javax.swing.JLabel hiLabel;
    private javax.swing.JCheckBox hotChocolate;
    private javax.swing.JTextField hotChocolateCount;
    private javax.swing.JCheckBox iceCoffee;
    private javax.swing.JTextField iceCoffeeCount;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPasswordField inUMConfirmPasswordField;
    private javax.swing.JPasswordField inUMPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
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
    private javax.swing.JCheckBox nonvegSubmarine;
    private javax.swing.JTextField nonvegSubmarineCount;
    private javax.swing.JLabel oidLabel;
    private javax.swing.JLabel orderNoLabel;
    private javax.swing.JLabel orderNoLabel1;
    private javax.swing.JLabel orderNoLabel2;
    private javax.swing.JTable ordersTable;
    private javax.swing.JLabel passwordInUMLabel;
    private javax.swing.JLabel passwordInUMLabel1;
    private javax.swing.JLabel phoneInUMLabel;
    private javax.swing.JTextField phoneInUMTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton proceedButton;
    private javax.swing.JLabel recievedLabel;
    private javax.swing.JTextField recievedTextField;
    private javax.swing.JButton saveUserButton;
    private javax.swing.JButton saveUserButton1;
    private javax.swing.JButton saveUserButton2;
    private javax.swing.JTextField searchInUMTextField;
    private javax.swing.JLabel serviceChargesLabel;
    private javax.swing.JTextField serviceChargesTextField;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JCheckBox spriteL;
    private javax.swing.JTextField spriteLCount;
    private javax.swing.JCheckBox spriteS;
    private javax.swing.JTextField spriteSCount;
    private javax.swing.JCheckBox strawberryCone;
    private javax.swing.JTextField strawberryConeCount;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JLabel uidInUMLabel;
    private javax.swing.JLabel userDetailsLabel;
    private javax.swing.JPanel userDetailsPanel;
    private javax.swing.JList<String> userList;
    private javax.swing.JLabel userListLabel;
    private javax.swing.JLabel userListLabel1;
    private javax.swing.JLabel userListLabel2;
    private javax.swing.JScrollPane userListScrollPane;
    private javax.swing.JPanel userManagementPanel;
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
