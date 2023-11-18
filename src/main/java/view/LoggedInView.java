package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Represents the view for the logged-in screen.
 * Displays user information and provides a logout button.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    final JButton logOut;
    private final LoggedInViewModel loggedInViewModel;
    JLabel username;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
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
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}