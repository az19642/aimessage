package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.password_generator.PasswordGeneratorController;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

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

    private final PasswordGeneratorController passwordGeneratorController;

    /**
     * Constructs a SignupView.
     *
     * @param controller                  The SignupController for handling signup actions.
     * @param signupViewModel             The SignupViewModel for managing the signup view's state.
     * @param loginViewModel              The LoginViewModel for managing the login view's state.
     * @param viewManagerModel            The ViewManagerModel for controlling the active view.
     * @param passwordGeneratorController The PasswordGeneratorController for generating secure passwords.
     */
    public SignupView(SignupController controller, SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                      ViewManagerModel viewManagerModel, PasswordGeneratorController passwordGeneratorController) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.passwordGeneratorController = passwordGeneratorController;

        signupViewModel.addPropertyChangeListener(this);

        // Set up main panel with grid layout
        GridLayout gridLayout = new GridLayout(12, 3);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        JPanel mainPanel = new JPanel(gridLayout);

        JLabel titleLabel = new JLabel(SignupViewModel.TITLE_LABEL);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 24));

        JLabel groupLabel = new JLabel(SignupViewModel.GROUP_LABEL);
        groupLabel.setFont(new Font("Helvetica", Font.BOLD, 12));

        JLabel usernameLabel = new JLabel(SignupViewModel.USERNAME_LABEL);
        usernameLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        JLabel passwordLabel = new JLabel(SignupViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        JLabel repeatPasswordLabel = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        JLabel preferredLanguageLabel = new JLabel(SignupViewModel.PREFERRED_LANGUAGE_LABEL);
        preferredLanguageLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        JLabel haveAccountLabel = new JLabel(SignupViewModel.HAVE_ACCOUNT_LABEL);
        haveAccountLabel.setFont(new Font("Helvetica", Font.BOLD, 14));

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        groupLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setHorizontalAlignment(JLabel.RIGHT);
        passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
        repeatPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        preferredLanguageLabel.setHorizontalAlignment(JLabel.RIGHT);
        haveAccountLabel.setHorizontalAlignment(JLabel.RIGHT);

        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setFont(new Font("Helvetica", Font.PLAIN, 12));
        goToLogin = new JButton(SignupViewModel.GO_TO_LOGIN_BUTTON_LABEL);
        goToLogin.setFont(new Font("Helvetica", Font.PLAIN, 12));
        generatePassword = new JButton(SignupViewModel.GENERATE_PASSWORD_LABEL);
        generatePassword.setFont(new Font("Helvetica", Font.PLAIN, 12));

        // Create a dropdown menu with language options
        String[] languages = {"English", "French", "Korean", "Arabic", "Chinese", "Spanish"};
        languageDropdown = new JComboBox<>(languages);
        languageDropdown.setFont(new Font("Helvetica", Font.PLAIN, 12));

        // Styling for input fields
        Dimension inputFieldSize = new Dimension(100, 30);
        Color inputFieldBackground = new Color(240, 240, 240);

        usernameInputField.setPreferredSize(inputFieldSize);
        usernameInputField.setBackground(inputFieldBackground);

        passwordInputField.setPreferredSize(inputFieldSize);
        passwordInputField.setBackground(inputFieldBackground);

        repeatPasswordInputField.setPreferredSize(inputFieldSize);
        repeatPasswordInputField.setBackground(inputFieldBackground);

        // Calls helper method to add appropriate listeners to components
        addListeners();

        // Calls helper method to add all components to mainPanel in the correct order
        addComponents(mainPanel, titleLabel, groupLabel, usernameLabel, passwordLabel, repeatPasswordLabel,
                preferredLanguageLabel, haveAccountLabel);

        mainPanel.setBackground(Color.WHITE);

        // Add everything to parent (this) frame signup view panel
        this.add(mainPanel);
        // Set parent layout to vertical box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addListeners() {
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
    }

    private void addComponents(JPanel mainPanel, JLabel titleLabel, JLabel groupLabel, JLabel usernameLabel,
                               JLabel passwordLabel, JLabel repeatPasswordLabel, JLabel preferredLanguageLabel,
                               JLabel haveAccountLabel) {
        // Empty labels are for spacing
        mainPanel.add(new JLabel(""));
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(groupLabel);
        mainPanel.add(new JLabel(""));

        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));
        mainPanel.add(new JLabel(""));

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