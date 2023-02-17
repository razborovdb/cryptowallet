package com.development.util;

import com.development.dependency.DaggerServiceComponent;
import com.development.dependency.ServiceComponent;

public class DaggerService {
    public static ServiceComponent getDagger() {
        return DaggerServiceComponent.create();
    }
}
