package views;

import interface_adapter.conversation.ConversationState;
import services.view_data_sync.update_conversation.ConversationViewModel;
import services.messaging.send_message.interface_adapters.SendMessageController;

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
    private final ConversationViewModel syncConversationViewModel;

    private final SendMessageController sendMessageController;

    public ConversationView(ConversationViewModel syncConversationViewModel, SendMessageController sendMessageController) {
        this.syncConversationViewModel = syncConversationViewModel;
        this.sendMessageController = sendMessageController;

        conversationHistory = new JTextArea();
        conversationHistory.setEditable(false);

        messageInput = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addPropertyChangeListener(evt -> {
            if (evt.getSource() == sendButton) {
                String message = messageInput.getText();
                sendMessageController.execute(syncConversationViewModel.getState().getContactName(), message);
                syncConversationViewModel.firePropertyChanged();
                messageInput.setText("");
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(conversationHistory), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(messageInput, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        this.add(bottomPanel, BorderLayout.SOUTH);

        syncConversationViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ConversationState state = syncConversationViewModel.getState();
        conversationHistory.setText("");
        for (Map.Entry<LocalDateTime, String> entry : state.getTimestampToMessage().entrySet()) {
            String timestamp = entry.getKey().toString();
            String message = entry.getValue();
            conversationHistory.append(message + " " + timestamp + "\n");
        }
    }
}