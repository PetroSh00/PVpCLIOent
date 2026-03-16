package com.myclient.core;

import com.myclient.modules.BrightnessBoostModule;
import com.myclient.modules.SprintAssistModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        // Intentionally includes only non-cheating modules.
        modules.add(new SprintAssistModule());
        modules.add(new BrightnessBoostModule());
    }

    public List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }
}
