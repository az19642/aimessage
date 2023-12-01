package data_access;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.audio.CreateSpeechRequest;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import use_case.password_generator.PasswordGeneratorUserDataAccessInterface;
import use_case.text_to_speech.TextToSpeechDataAccessInterface;

import okhttp3.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a data access object for generating secure passwords using the GPT model from OpenAI.
 */
public class GPTDataAccessObject implements PasswordGeneratorUserDataAccessInterface, TextToSpeechDataAccessInterface {
    private final OpenAiService service;

    /**
     * Initializes a new GPTDataAccessObject with the provided OpenAI API key.
     *
     * @param openaiApiKey The API key for accessing the OpenAI service.
     */
    public GPTDataAccessObject(String openaiApiKey) {
        this.service = new OpenAiService(openaiApiKey);
    }

    /**
     * Get the OpenAiService instance associated with this data access object.
     *
     * @return The OpenAiService instance used for interacting with the OpenAI API.
     */
    public OpenAiService getService() {
        return service;
    }

    /**
     * Generates a secure password based on the provided prompt using the GPT model.
     *
     * @param prompt The prompt for generating the password.
     * @return The generated secure password as a string.
     */
    public String generateSecurePassword(String prompt) {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
        messages.add(userMessage);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-4")
                .messages(messages)
                .maxTokens(10)
                .build();

        try {
            ChatMessage responseMessage = service.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();
            return responseMessage.getContent();
        } catch (Exception e) {
            return "Error: Failed to generate a password.";
        }
    }

    /**
     * Generates audio from the provided text using a text-to-speech service and plays it.
     *
     * @param message The text to be converted into audio.
     * @return {@code true} if the audio generation and playback were successful; {@code false} otherwise.
     * @throws IOException If an I/O error occurs during the processing of the audio content.
     */
    public boolean generateAudio(String message) {
        CreateSpeechRequest createSpeechRequest = CreateSpeechRequest.builder()
                .model("tts-1")
                .input(message)
                .voice("alloy")
                .build();

        try {
            ResponseBody speech = service.createSpeech(createSpeechRequest);

            // Play the MP3 directly without saving to a file
            playMP3(new ByteArrayInputStream(speech.bytes()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void playMP3(InputStream inputStream) {
        try {
            AdvancedPlayer player = new AdvancedPlayer(inputStream);

            // Start playing the MP3 file
            player.play();

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}

