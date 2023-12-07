package views;

import services.conversation.interface_adapters.ConversationState;
import services.conversation.interface_adapters.ConversationViewModel;
import services.conversation.sync_conversation_view.interface_adapters.ConversationSyncController;
import services.send_message.interface_adapters.MessageSenderController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ConversationView extends JPanel implements PropertyChangeListener {
    public final String viewName = "conversation";
    private final JTextArea conversationHistory;
    private final JTextField messageInput;
    private final JButton sendButton;
    private final ConversationViewModel conversationViewModel;

    private final MessageSenderController messageSenderController;
    private final ConversationSyncController conversationSyncController;

    public ConversationView(ConversationViewModel conversationViewModel,
                            MessageSenderController messageSenderController,
                            ConversationSyncController conversationSyncController) {
        this.conversationViewModel = conversationViewModel;
        this.messageSenderController = messageSenderController;
        this.conversationSyncController = conversationSyncController;

        conversationHistory = new JTextArea();
        conversationHistory.setEditable(false);

        messageInput = new JTextField();
        sendButton = new JButton("Send");

        sendButton.addActionListener(evt -> {
            if (evt.getSource() == sendButton) {
                ConversationState conversationState = conversationViewModel.getState();
                String message = messageInput.getText();
                messageSenderController.execute(conversationState.getContactName(), message);
                conversationSyncController.execute(conversationState.getContactName());
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
        for (Map.Entry<LocalDateTime, List<String>> entry : state.getTimestampToMessage().entrySet()) {
            String timestamp = entry.getKey().toString();
            String sender = entry.getValue().get(0);
            String message = entry.getValue().get(1);
            conversationHistory.append(message + sender + " " + timestamp + "\n");
        }
    }
}