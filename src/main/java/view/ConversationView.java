package view;

import interface_adapter.conversation.ConversationState;
import interface_adapter.conversation.ConversationViewModel;
import interface_adapter.send_message.SendMessageController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.Map;

public class ConversationView extends JPanel implements PropertyChangeListener {
    public final String viewName = "conversation";
    private final JTextArea conversationHistory;
    private final JTextField messageInput;
    private final JButton sendButton;
    private final ConversationViewModel conversationViewModel;

    private final SendMessageController sendMessageController;

    public ConversationView(ConversationViewModel conversationViewModel, SendMessageController sendMessageController) {
        this.conversationViewModel = conversationViewModel;
        this.sendMessageController = sendMessageController;

        conversationHistory = new JTextArea();
        conversationHistory.setEditable(false);

        messageInput = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addPropertyChangeListener(evt -> {
            if (evt.getSource() == sendButton) {
                String message = messageInput.getText();
                sendMessageController.execute(conversationViewModel.getState().getContactName(), message);
                conversationViewModel.firePropertyChanged();
                messageInput.setText("");
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(conversationHistory), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);

        conversationViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConversationState state = conversationViewModel.getState();
        conversationHistory.setText("");
        for (Map.Entry<LocalDateTime, String> entry : state.getTimestampToMessage().entrySet()) {
            String timestamp = entry.getKey().toString();
            String message = entry.getValue();
            conversationHistory.append(message + " " + timestamp + "\n");
        }
    }
}