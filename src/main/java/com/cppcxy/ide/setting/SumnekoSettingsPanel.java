package com.cppcxy.ide.setting;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SumnekoSettingsPanel implements SearchableConfigurable, Configurable.NoScroll {
    private JPanel myPanel;
    private JComboBox locale;
    private JTextField location;

    private SumnekoSettings settings = SumnekoSettings.getInstance();

    public SumnekoSettingsPanel() {
        DefaultComboBoxModel<SumnekoSupportLocale> model = new DefaultComboBoxModel<>();
        for (SumnekoSupportLocale value : SumnekoSupportLocale.values()) {
            model.addElement(value);
        }

        locale.addActionListener(e -> {
            settings.setLocale((SumnekoSupportLocale) locale.getSelectedItem());
        });

        locale.setModel(model);
        locale.setSelectedItem(SumnekoSettings.getInstance().getLocale());
        location.setText(settings.getLocation());
    }

    @Override
    public @NotNull @NonNls String getId() {
        return "SumnekoSettingsPanel";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Sumneko Settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return myPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
          settings.setLocale((SumnekoSupportLocale) locale.getSelectedItem());
          settings.setLocation(location.getText());
    }
}
