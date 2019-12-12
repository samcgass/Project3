import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JPasswordField(20);
    public JTextField txtFullname = new JTextField(20);
    public JTextField txtUsertype = new JTextField(20);

    UserModel user;

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Username "));
        line.add(txtUsername);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password "));
        line.add(txtPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Full Name "));
        line.add(txtFullname);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Usertype "));
        line.add(txtUsertype);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

//        txtProductID.addFocusListener(new AddPurchaseUI.ProductIDFocusListener());
//        txtCustomerID.addFocusListener(new AddPurchaseUI.CustomerIDFocusListener());
//        txtQuantity.getDocument().addDocumentListener(new AddPurchaseUI.QuantityChangeListener());

        btnAdd.addActionListener(new AddButtonListener());
        btnCancel.addActionListener(new AddUserUI.CancelButtonListener());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    class CancelButtonListener implements ActionListener{
        @Override
        public void actionPerformed (ActionEvent actionEvent) {
            view.setVisible(false);
        }
    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String id = txtUsername.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }

            try {
                user.mUsername = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Username is invalid!");
                return;
            }

            id = txtPassword.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be null!");
                return;
            }

            try {
                user.mPassword = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Password is invalid!");
                return;
            }

            id = txtFullname.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be null!");
                return;
            }

            try {
                user.mFullname = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Name is invalid!");
                return;
            }

            id = txtUsertype.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Usertype cannot be null!");
                return;
            }

            if (Integer.parseInt(id) > 3 || Integer.parseInt(id) < 0) {
                JOptionPane.showMessageDialog(null, "Usertype must be 0,1,2, or 3");
            }

            try {
                user.mUserType = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Usertype is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().saveUser(user);
            if (res == SQLiteDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User NOT added successfully!");
            else
                JOptionPane.showMessageDialog(null, "User added successfully!" + user);
        }
    }
}
