import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUserUI {
    public JFrame view;

    public JButton btnLoad = new JButton("Load");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JPasswordField(20);
    public JTextField txtFullname = new JTextField(20);
    public JTextField txtUsertype = new JTextField(20);

    UserModel user;

    public ManageUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage User");
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
        line.add(btnLoad);
        line.add(btnUpdate);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnLoad.addActionListener(new LoadButtonListener());
        btnUpdate.addActionListener(new UpdateButtonListener());
        btnCancel.addActionListener(new CancelButtonListener());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            //Gson gson = new Gson();

            String id = txtUsername.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }
            user.mUsername = id;

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password name cannot be empty!");
                return;
            }
            user.mPassword = password;

            String name = txtFullname.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name name cannot be empty!");
                return;
            }
            user.mFullname = name;

            String type = txtUsertype.getText();
            if (type.length() == 0) {
                JOptionPane.showMessageDialog(null, "usertype name cannot be empty!");
                return;
            }
            try {
                user.mUserType = Integer.parseInt(type);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Usertype is invalid!");
                return;
            }

            int check = StoreManager.getInstance().getDataAdapter().saveUser(user);

            if (check == IDataAdapter.USER_SAVE_FAILED) {
                JOptionPane.showMessageDialog(null, "User update failed!");
            } else {
                JOptionPane.showMessageDialog(null, "User update success!");
            }


        }
    }

    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent actionEvent) {
            view.setVisible(false);
        }
    }

    class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
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

            // call data access!

            user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUsername);

            if (user == null) {
                JOptionPane.showMessageDialog(null, "User NOT exists!");
            } else {
                txtPassword.setText(user.mPassword);
                txtFullname.setText(user.mFullname);
                txtUsertype.setText(Integer.toString(user.mUserType));
            }
        }
    }
}
