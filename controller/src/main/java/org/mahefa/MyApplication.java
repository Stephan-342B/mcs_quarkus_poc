package org.mahefa;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.configuration.ProfileManager;

@QuarkusMain
public class MyApplication {

    public static void main(String[] args)  {
        Quarkus.run(MyApp.class, args);
    }

    public static class MyApp implements QuarkusApplication {

        @Override
        public int run(String... args)  {
            System.out.println("Server UP [Active profile: " + ProfileManager.getActiveProfile().toUpperCase() + "]");
            Quarkus.waitForExit();
            return 0;
        }
    }
}
