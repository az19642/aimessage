package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the view for the login screen.
 * Allows users to input their username and password, and provides login and cancel buttons.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    final JTextField usernameInputField = new JTextField(15);
    final JPasswordField passwordInputField = new JPasswordField(15);
    final JButton logIn;
    final JButton cancel;
    private final LoginViewModel loginViewModel;
    private final JLabel usernameErrorField = new JLabel();
    private final JLabel passwordErrorField = new JLabel();
    private final LoginController loginController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * Reacts to a button click event.
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    /**
     * Sets the fields based on the provided LoginState.
     *
     * @param state The LoginState to set the fields from.
     */
    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}