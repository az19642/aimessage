package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.password_generator.PasswordGeneratorController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the view for the signup screen.
 * Allows users to input their desired username, password, and repeat password,
 * and provides signup, login, and password generation buttons.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignupController signupController;

    private final JButton signUp;
    private final JButton goToLogin;
    private final JButton generatePassword;
    private final JComboBox<String> languageDropdown;

    public SignupView(SignupController controller, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                      ViewManagerModel viewManagerModel, PasswordGeneratorController passwordGeneratorController) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;

        signupViewModel.addPropertyChangeListener(this);

        // Set up main panel with grid layout
        GridLayout gridLayout = new GridLayout(8, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_LABEL);
        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_LABEL);
        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        JLabel preferredLanguageLabel = new JLabel(SignupViewModel.PREFERRED_LANGUAGE_LABEL);
        JLabel haveAccountLabel = new JLabel(SignupViewModel.HAVE_ACCOUNT_LABEL);

        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        preferredLanguageLabel.setHorizontalAlignment(JLabel.RIGHT);
        haveAccountLabel.setHorizontalAlignment(JLabel.RIGHT);

        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        goToLogin = new JButton(SignupViewModel.GO_TO_LOGIN_BUTTON_LABEL);
        generatePassword = new JButton(SignupViewModel.GENERATE_PASSWORD_LABEL);

        // Create a dropdown menu with language options
        String[] languages = {"English", "French", "Korean", "Arabic", "Chinese", "Spanish"};
        languageDropdown = new JComboBox<>(languages);

        signUp.addActionListener(evt -> {
            if (evt.getSource().equals(signUp)) {
                SignupState currentState = signupViewModel.getState();
                String preferredLanguage = languageDropdown.getItemAt(languageDropdown.getSelectedIndex());

                signupController.execute(currentState.getUsername(), currentState.getPassword(),
                        currentState.getRepeatPassword(), preferredLanguage
                );
            }
        });

        goToLogin.addActionListener(evt -> {
            if (evt.getSource().equals(goToLogin)) {
                viewManagerModel.setActiveView(loginViewModel.getViewName());
                viewManagerModel.firePropertyChanged();
            }
        });

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                String text = usernameInputField.getText() + e.getKeyChar();
                currentState.setUsername(text);
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        repeatPasswordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                signupViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        generatePassword.addActionListener(evt -> {
            if (evt.getSource().equals(generatePassword)) {
                String generatedPassword = passwordGeneratorController.execute("Generate a secure password.");
                JOptionPane.showMessageDialog(SignupView.this, generatedPassword);
                passwordInputField.setText(generatedPassword);
                SignupState currentState = signupViewModel.getState();
                currentState.setPassword(generatedPassword);
                signupViewModel.setState(currentState);
            }
        });

        // Add components to main panel in correct order; empty labels are for spacing
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(generatePassword);
        mainPanel.add(new JLabel(""));

        mainPanel.add(repeatPasswordLabel);
        mainPanel.add(repeatPasswordInputField);
        mainPanel.add(new JLabel(""));

        mainPanel.add(preferredLanguageLabel);
        mainPanel.add(languageDropdown);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(signUp);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

        mainPanel.add(haveAccountLabel);
        mainPanel.add(goToLogin);
        mainPanel.add(new JLabel(""));

        // Create a grid panel to produce pseudo margins on the top and bottom; centers main panel
        JPanel pseudoMarginsPanel = new JPanel(new GridLayout(3, 1));
        pseudoMarginsPanel.add(new JLabel(""));
        pseudoMarginsPanel.add(mainPanel);
        pseudoMarginsPanel.add(new JLabel(""));

        // Add everything to (this) signup view panel
        this.add(pseudoMarginsPanel);
    }

    /**
     * Reacts to a button click event.
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}