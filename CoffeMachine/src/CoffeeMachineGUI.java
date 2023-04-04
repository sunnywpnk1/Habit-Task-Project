import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CoffeeMachineGUI extends CoffeeMachineInterface {
    CoffeeMachineGUI(int water, int milk, int beans, int cups, int cash) {
        super(water, milk, beans, cups, cash);
    }

    public void MainFrame() throws IOException {

        JFrame frame = new JFrame(); // initialize frame

        // icons

        ImageIcon icon = new ImageIcon(
                ImageIO.read(CoffeeMachineGUI.class.getClassLoader().getResourceAsStream("Assets/Logo.png")));
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        ImageIcon icon2 = new ImageIcon(
                ImageIO.read(CoffeeMachineGUI.class.getClassLoader().getResourceAsStream("Assets/Esspresso.png")));
        icon2 = new ImageIcon(icon2.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        ImageIcon icon3 = new ImageIcon(
                ImageIO.read(CoffeeMachineGUI.class.getClassLoader().getResourceAsStream("Assets/Cappucino.png")));
        icon3 = new ImageIcon(icon3.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        ImageIcon icon4 = new ImageIcon(
                ImageIO.read(CoffeeMachineGUI.class.getClassLoader().getResourceAsStream("Assets/Latte.png")));
        icon4 = new ImageIcon(icon4.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        JLabel image1 = new JLabel(icon2);
        image1.setIcon(icon2);

        JLabel image2 = new JLabel(icon2);
        image2.setIcon(icon3);

        JLabel image3 = new JLabel(icon2);
        image3.setIcon(icon4);

        image1.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 20));
        image2.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 20));
        image3.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 20));

        // กรอบกลาง(238,218,191)
        final Color cmiddle = new Color(238, 218, 191);

        // checkboxes
        JCheckBox ch1 = new JCheckBox();
        ch1.setBackground(cmiddle);
        ch1.setText("Esspresso     ");
        ch1.setFocusable(false);
        JCheckBox ch2 = new JCheckBox();
        ch2.setBackground(cmiddle);
        ch2.setText("Latte     ");
        ch2.setFocusable(false);
        JCheckBox ch3 = new JCheckBox();
        ch3.setBackground(cmiddle);
        ch3.setText("Cappucino   ");
        ch3.setFocusable(false);

        // title header
        JLabel headerLabel = new JLabel();
        headerLabel.setText("Coffee Machine ");
        headerLabel.setIcon(icon);
        headerLabel.setFont(new Font("Georgia", Font.ITALIC + Font.BOLD, 36));
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        titlePanel.setPreferredSize(new Dimension(0, 60));

        // กรอบบน
        final Color ctop = new Color(248, 129, 88);
        titlePanel.setBackground(ctop);
        titlePanel.add(headerLabel);

        // selection menu panel
        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 0, 0));

        // ซ้าย
        final Color cleft = new Color(238, 218, 190);
        menuPanel.setBackground(cleft);

        menuPanel.add(image1);
        menuPanel.add(ch1);
        menuPanel.add(image2);
        menuPanel.add(ch2);
        menuPanel.add(image3);
        menuPanel.add(ch3);

        // pricing panel
        JPanel pricePanel = new JPanel(new FlowLayout());

        // ขวา
        final Color cright = new Color(255, 197, 148);
        pricePanel.setBackground(cright);

        JLabel priceTag = new JLabel("Total price: ");
        JLabel moneyInsert = new JLabel("Insert Money: ");
        JLabel change = new JLabel("Change: ");

        JTextField priceBox = new JTextField(12);
        priceBox.setEditable(false);

        JTextField changeBox = new JTextField(12);
        changeBox.setEditable(false);

        JLabel reportField = new JLabel(" ");

        JTextField insertBox = new JTextField(10);
        insertBox.addActionListener(e -> {
            moneyInserted = Integer.parseInt(insertBox.getText());
            changeMoney = moneyInserted - price;
            if (moneyInserted >= price) {
                if (this.canMakeCappucino() && canMakeEsspresso() && canMakeLatte()) {
                    changeBox.setText(Integer.toString(changeMoney));
                    this.cash += moneyInserted - changeMoney;
                    this.price = 0;
                    if (ch1.isSelected()) {
                        this.makeEspresso();
                    }
                    if (ch2.isSelected() && canMakeLatte()) {
                        this.makeCappuccino();
                    }
                    if (ch3.isSelected() && canMakeCappucino()) {
                        this.makeLatte();
                    }
                    reportField.setText("Thank you very much");
                }else{
                    reportField.setText("Please refill ingredients");
                }

                priceBox.setText(null);
                ch1.setSelected(false);
                ch2.setSelected(false);
                ch3.setSelected(false);
            } else {
                changeBox.setText("Not enough money");
            }
            insertBox.setText(null);
        });

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFocusable(false);

        confirmButton.addActionListener(e -> {
            if (ch1.isSelected()) {
                this.price += ESPRESSO_PRICE;
            }
            if (ch2.isSelected()) {
                this.price += LATTE_PRICE;
            }
            if (ch3.isSelected()) {
                this.price += CAPPUCCINO_PRICE;
            }

            priceBox.setText(Integer.toString(this.price));
            changeBox.setText(null);
        });

        JButton resetButton = new JButton("Cancel Order");
        resetButton.setFocusable(false);

        resetButton.addActionListener(e -> {
            this.price = 0;
            reportField.setText(null);
            priceBox.setText(null);
            changeBox.setText(null);
            ch1.setSelected(false);
            ch2.setSelected(false);
            ch3.setSelected(false);
        });

        pricePanel.add(priceTag);
        pricePanel.add(priceBox);
        pricePanel.add(new JLabel("                  "));
        pricePanel.add(moneyInsert);
        pricePanel.add(insertBox);
        pricePanel.add(new JLabel("                    "));
        pricePanel.add(change);
        pricePanel.add(changeBox);
        pricePanel.add(new JLabel("                  "));
        pricePanel.add(confirmButton);
        pricePanel.add(resetButton);

        // filling panel
        JPanel fillingPanel = new JPanel();

        // กรอบล่าง
        final Color color = new Color(248, 129, 88);
        fillingPanel.setBackground(color);
        JLabel waterText = new JLabel("Add water(ml):");
        JLabel milkText = new JLabel("Add milk(ml):");
        JLabel beanText = new JLabel("Add coffee(g):");
        JLabel cupText = new JLabel("Add cups:");

        JTextField addWater = new JTextField(3);
        JTextField addMilk = new JTextField(3);
        JTextField addBean = new JTextField(3);
        JTextField addCup = new JTextField(3);

        addWater.addActionListener(e -> {
            this.water += Integer.parseInt(addWater.getText().trim());
            addWater.setText(null);
        });
        addMilk.addActionListener(e -> {
            this.milk += Integer.parseInt(addMilk.getText().trim());
            addMilk.setText(null);
        });
        addBean.addActionListener(e -> {
            this.beans += Integer.parseInt(addBean.getText().trim());
            addBean.setText(null);
        });
        addCup.addActionListener(e -> {
            this.cups += Integer.parseInt(addCup.getText().trim());
            addCup.setText(null);
        });

        fillingPanel.add(waterText);
        fillingPanel.add(addWater);
        fillingPanel.add(milkText);
        fillingPanel.add(addMilk);
        fillingPanel.add(beanText);
        fillingPanel.add(addBean);
        fillingPanel.add(cupText);
        fillingPanel.add(addCup);

        // status button
        JToggleButton statusButton = new JToggleButton("Status");
        statusButton.setFocusable(false);
        statusButton.addActionListener(e -> {
            if (statusButton.isSelected()) {
                addWater.setText(Integer.toString(this.water));
                addBean.setText(Integer.toString(this.beans));
                addMilk.setText(Integer.toString(this.milk));
                addCup.setText(Integer.toString(this.cups));
                
            } else {
                addWater.setText(null);
                addBean.setText(null);
                addMilk.setText(null);
                addCup.setText(null);
            }
        });
        pricePanel.add(statusButton);

        // take money
        reportField.setFont(new Font("Georgia", Font.BOLD, 15));
        JButton takeButton = new JButton("Take out cash");
        takeButton.setFocusable(false);
        takeButton.addActionListener(e -> {
            reportField.setText(this.cash + " baht has been taken out");
            this.doTakeMoney();
        });

        pricePanel.add(takeButton);
        pricePanel.add(reportField);

        // frame components
        frame.setLayout(new BorderLayout());
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(pricePanel, BorderLayout.CENTER);
        frame.add(fillingPanel, BorderLayout.SOUTH);

        // frame defaults
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}