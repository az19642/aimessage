package interface_adapter.password_generator;

import interface_adapter.ViewModel;
import interface_adapter.password_generator.PasswordGeneratorState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PasswordGeneratorViewModel extends ViewModel {
    private PasswordGeneratorState state = new PasswordGeneratorState();

    public PasswordGeneratorViewModel() {
        super("Generate a secure password.");
    }

    public void setState(PasswordGeneratorState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public PasswordGeneratorState getState() {
        return state;
    }
}
