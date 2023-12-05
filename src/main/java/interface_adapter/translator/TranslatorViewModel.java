package interface_adapter.translator;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TranslatorViewModel extends ViewModel {
    private TranslatorState state = new TranslatorState();

    public TranslatorViewModel() {
        super("Create translation from text.");
    }

    public void setState(TranslatorState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public TranslatorState getState() {
        return state;
    }
}
