package view;

import interface_adapter.load_contacts_to_view.LoadContactsToViewController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

/**
 * Represents the view for the logged-in screen.
 * Displays user information and provides a logout button.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final JButton reload;
    private final LoggedInViewModel loggedInViewModel;
    private final LoadContactsToViewController loadContactsToViewController;
    private JList<Map.Entry<String, String>> contactToLastMessage;


    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel,
                        LoadContactsToViewController loadContactsToViewController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loadContactsToViewController = loadContactsToViewController;

        reload = new JButton("Reload contacts (for testing purposes)");
        reload.addActionListener(this);

        JPanel buttons = new JPanel();
        buttons.add(reload);

        contactToLastMessage = new JList<>();
        contactToLastMessage.setCellRenderer(new ContactToLastMessageCellRenderer());

        this.add(new JScrollPane(contactToLastMessage));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(buttons);
    }


    private static class ContactToLastMessageCellRenderer extends JLabel implements ListCellRenderer<Map.Entry<String
            , String>> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Map.Entry<String, String>> list,
                                                      Map.Entry<String, String> contact, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(contact.getKey() + " | " + contact.getValue());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }

    /**
     * Reacts to a button click event.
     *
     * @param evt The ActionEvent representing the button click.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == reload) {
            loadContactsToViewController.execute(loggedInViewModel.getState().getUsername());
        }
    }

    /**
     * Handles property change events.
     *
     * @param evt The PropertyChangeEvent representing the change.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        if (state.getContactToLastMessage() == null) { // first time loading
            loadContactsToViewController.execute(state.getUsername());
        } else {
            DefaultListModel<Map.Entry<String, String>> listModel = new DefaultListModel<>();
            for (Map.Entry<String, String> contact : state.getContactToLastMessage().entrySet()) {
                listModel.addElement(contact);
            }
            contactToLastMessage.setModel(listModel);
        }
    }
}