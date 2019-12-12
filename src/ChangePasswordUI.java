import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordUI {
    public JFrame view;

    public JButton btnUpdate = new JButton("Update");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtOldPassword = new JPasswordField(20);
    public JTextField txtNewPassword = new JPasswordField(20);

    UserModel user;

    public ChangePasswordUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Change Password");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Username "));
        line.add(txtUsername);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Old Password "));
        line.add(txtOldPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("New Password "));
        line.add(txtNewPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnUpdate);
        line.add(btnCancel);
        view.getContentPane().add(line);

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

            String id = txtUsername.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }
            user.mUsername = id;

            String oldPassword = txtOldPassword.getText();
            if (oldPassword.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password name cannot be empty!");
                return;
            }

            String password = txtNewPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password name cannot be empty!");
                return;
            }
            user.mPassword = password;

            JOptionPane.showMessageDialog(null, "Password Updated!");


        }
    }

    class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent actionEvent) {
            view.setVisible(false);
        }
    }
 }
