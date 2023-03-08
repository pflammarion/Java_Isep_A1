package com.isep.harrypotter;


import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

public class Chapter {
    @Setter
    private String name;
    @Getter
    @Setter
    private int number = 1;

    @Getter
    @Setter
    private boolean isChapterInit = false;
    @Getter
    @Setter
    private boolean isBossPassed = false;

    public String getName() {
        switch (this.number){
            case 1 ->
                    setName("  _______ _            _____  _     _ _                       _                      _____ _                   \n" +
                            " |__   __| |          |  __ \\| |   (_) |                     | |                    / ____| |                  \n" +
                            "    | |  | |__   ___  | |__) | |__  _| | ___  ___  ___  _ __ | |__   ___ _ __ ___  | (___ | |_ ___  _ __   ___ \n" +
                            "    | |  | '_ \\ / _ \\ |  ___/| '_ \\| | |/ _ \\/ __|/ _ \\| '_ \\| '_ \\ / _ \\ '__/ __|  \\___ \\| __/ _ \\| '_ \\ / _ \\\n" +
                            "    | |  | | | |  __/ | |    | | | | | | (_) \\__ \\ (_) | |_) | | | |  __/ |  \\__ \\  ____) | || (_) | | | |  __/\n" +
                            "    |_|  |_| |_|\\___| |_|    |_| |_|_|_|\\___/|___/\\___/| .__/|_| |_|\\___|_|  |___/ |_____/ \\__\\___/|_| |_|\\___|\n" +
                            "                                                       | |                                                     \n" +
                            "                                                       |_|                                                     ");

            case 2 ->
                    setName("\n" +
                            "\n" +
                            "  _______ _             _____ _                     _                        __    _____                    _       \n" +
                            " |__   __| |           / ____| |                   | |                      / _|  / ____|                  | |      \n" +
                            "    | |  | |__   ___  | |    | |__   __ _ _ __ ___ | |__   ___ _ __    ___ | |_  | (___   ___  ___ _ __ ___| |_ ___ \n" +
                            "    | |  | '_ \\ / _ \\ | |    | '_ \\ / _` | '_ ` _ \\| '_ \\ / _ \\ '__|  / _ \\|  _|  \\___ \\ / _ \\/ __| '__/ _ \\ __/ __|\n" +
                            "    | |  | | | |  __/ | |____| | | | (_| | | | | | | |_) |  __/ |    | (_) | |    ____) |  __/ (__| | |  __/ |_\\__ \\\n" +
                            "    |_|  |_| |_|\\___|  \\_____|_| |_|\\__,_|_| |_| |_|_.__/ \\___|_|     \\___/|_|   |_____/ \\___|\\___|_|  \\___|\\__|___/\n" +
                            "                                                                                                                    \n" +
                            "                                                                                                                    \n" +
                            "\n");

            case 3 -> setName("\n" +
                    "\n" +
                    "  _______ _            _____      _                                          __                 _         _                 \n" +
                    " |__   __| |          |  __ \\    (_)                                        / _|     /\\        | |       | |                \n" +
                    "    | |  | |__   ___  | |__) | __ _ ___  ___  _ __  _ __   ___ _ __    ___ | |_     /  \\    ___| | ____ _| |__   __ _ _ __  \n" +
                    "    | |  | '_ \\ / _ \\ |  ___/ '__| / __|/ _ \\| '_ \\| '_ \\ / _ \\ '__|  / _ \\|  _|   / /\\ \\  |_  / |/ / _` | '_ \\ / _` | '_ \\ \n" +
                    "    | |  | | | |  __/ | |   | |  | \\__ \\ (_) | | | | | | |  __/ |    | (_) | |    / ____ \\  / /|   < (_| | |_) | (_| | | | |\n" +
                    "    |_|  |_| |_|\\___| |_|   |_|  |_|___/\\___/|_| |_|_| |_|\\___|_|     \\___/|_|   /_/    \\_\\/___|_|\\_\\__,_|_.__/ \\__,_|_| |_|\n" +
                    "                                                                                                                            \n" +
                    "                                                                                                                            \n" +
                    "\n");

            case 5 -> setName("\n" +
                    "\n" +
                    "  _______ _             _____       _     _      _            __   ______ _          \n" +
                    " |__   __| |           / ____|     | |   | |    | |          / _| |  ____(_)         \n" +
                    "    | |  | |__   ___  | |  __  ___ | |__ | | ___| |_    ___ | |_  | |__   _ _ __ ___ \n" +
                    "    | |  | '_ \\ / _ \\ | | |_ |/ _ \\| '_ \\| |/ _ \\ __|  / _ \\|  _| |  __| | | '__/ _ \\\n" +
                    "    | |  | | | |  __/ | |__| | (_) | |_) | |  __/ |_  | (_) | |   | |    | | | |  __/\n" +
                    "    |_|  |_| |_|\\___|  \\_____|\\___/|_.__/|_|\\___|\\__|  \\___/|_|   |_|    |_|_|  \\___|\n" +
                    "                                                                                     \n" +
                    "                                                                                     \n" +
                    "\n");

            case 6 -> setName("\n" +
                    "\n" +
                    "  _______ _             ____          _                    __   _   _            _____  _                _      \n" +
                    " |__   __| |           / __ \\        | |                  / _| | | | |          |  __ \\| |              (_)     \n" +
                    "    | |  | |__   ___  | |  | |_ __ __| | ___ _ __    ___ | |_  | |_| |__   ___  | |__) | |__   ___ _ __  ___  __\n" +
                    "    | |  | '_ \\ / _ \\ | |  | | '__/ _` |/ _ \\ '__|  / _ \\|  _| | __| '_ \\ / _ \\ |  ___/| '_ \\ / _ \\ '_ \\| \\ \\/ /\n" +
                    "    | |  | | | |  __/ | |__| | | | (_| |  __/ |    | (_) | |   | |_| | | |  __/ | |    | | | |  __/ | | | |>  < \n" +
                    "    |_|  |_| |_|\\___|  \\____/|_|  \\__,_|\\___|_|     \\___/|_|    \\__|_| |_|\\___| |_|    |_| |_|\\___|_| |_|_/_/\\_\\\n" +
                    "                                                                                                                \n" +
                    "                                                                                                                \n" +
                    "\n");

            case 7 -> setName("\n" +
                    "\n" +
                    "  _______ _            _    _       _  __        ____  _                 _   _____      _                \n" +
                    " |__   __| |          | |  | |     | |/ _|      |  _ \\| |               | | |  __ \\    (_)               \n" +
                    "    | |  | |__   ___  | |__| | __ _| | |_ ______| |_) | | ___   ___   __| | | |__) | __ _ _ __   ___ ___ \n" +
                    "    | |  | '_ \\ / _ \\ |  __  |/ _` | |  _|______|  _ <| |/ _ \\ / _ \\ / _` | |  ___/ '__| | '_ \\ / __/ _ \\\n" +
                    "    | |  | | | |  __/ | |  | | (_| | | |        | |_) | | (_) | (_) | (_| | | |   | |  | | | | | (_|  __/\n" +
                    "    |_|  |_| |_|\\___| |_|  |_|\\__,_|_|_|        |____/|_|\\___/ \\___/ \\__,_| |_|   |_|  |_|_| |_|\\___\\___|\n" +
                    "                                                                                                         \n" +
                    "                                                                                                         \n" +
                    "\n");

            case 8 -> setName("\n" +
                    "\n" +
                    "  _______ _            _____             _   _     _         _    _       _ _                   \n" +
                    " |__   __| |          |  __ \\           | | | |   | |       | |  | |     | | |                  \n" +
                    "    | |  | |__   ___  | |  | | ___  __ _| |_| |__ | |_   _  | |__| | __ _| | | _____      _____ \n" +
                    "    | |  | '_ \\ / _ \\ | |  | |/ _ \\/ _` | __| '_ \\| | | | | |  __  |/ _` | | |/ _ \\ \\ /\\ / / __|\n" +
                    "    | |  | | | |  __/ | |__| |  __/ (_| | |_| | | | | |_| | | |  | | (_| | | | (_) \\ V  V /\\__ \\\n" +
                    "    |_|  |_| |_|\\___| |_____/ \\___|\\__,_|\\__|_| |_|_|\\__, | |_|  |_|\\__,_|_|_|\\___/ \\_/\\_/ |___/\n" +
                    "                                                      __/ |                                     \n" +
                    "                                                     |___/                                      \n" +
                    "\n");
        }
        return name;
    }
    public int menu(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("\nWhat a nice day, what are you going to do today ?");
        System.out.println("1. Go to school");
        System.out.println("2. Skipping school");
        return scanner.nextInt();

    }
}
