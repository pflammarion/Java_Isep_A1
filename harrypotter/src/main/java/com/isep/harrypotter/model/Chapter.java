package com.isep.harrypotter.model;


import com.isep.harrypotter.model.characters.Boss;
import lombok.Getter;
import lombok.Setter;

public class Chapter {
    @Getter
    private String name;
    @Getter
    @Setter
    private int number;
    @Getter
    @Setter
    private boolean isChapterInit = false;
    @Getter
    @Setter
    private boolean isBossPassed = false;

    @Getter
    @Setter
    private int day;

    @Getter
    private Boss boss;

    public Chapter(int number) {
        switch (number) {
            case 1 -> {
                this.number = number;
                this.name =
                        """
                                  _______ _            _____  _     _ _                       _                      _____ _                  \s
                                 |__   __| |          |  __ \\| |   (_) |                     | |                    / ____| |                 \s
                                    | |  | |__   ___  | |__) | |__  _| | ___  ___  ___  _ __ | |__   ___ _ __ ___  | (___ | |_ ___  _ __   ___\s
                                    | |  | '_ \\ / _ \\ |  ___/| '_ \\| | |/ _ \\/ __|/ _ \\| '_ \\| '_ \\ / _ \\ '__/ __|  \\___ \\| __/ _ \\| '_ \\ / _ \\
                                    | |  | | | |  __/ | |    | | | | | | (_) \\__ \\ (_) | |_) | | | |  __/ |  \\__ \\  ____) | || (_) | | | |  __/
                                    |_|  |_| |_|\\___| |_|    |_| |_|_|_|\\___/|___/\\___/| .__/|_| |_|\\___|_|  |___/ |_____/ \\__\\___/|_| |_|\\___|
                                                                                       | |                                                    \s
                                                                                       |_|                                                     \
                                """;
                this.boss = new Boss(100, 100, 1, 50, 0.1, "Troll", null, "Wingardium Leviosa");
            }
            case 2 -> {
                this.number = number;
                this.name = """


                          _______ _             _____ _                     _                        __    _____                    _      \s
                         |__   __| |           / ____| |                   | |                      / _|  / ____|                  | |     \s
                            | |  | |__   ___  | |    | |__   __ _ _ __ ___ | |__   ___ _ __    ___ | |_  | (___   ___  ___ _ __ ___| |_ ___\s
                            | |  | '_ \\ / _ \\ | |    | '_ \\ / _` | '_ ` _ \\| '_ \\ / _ \\ '__|  / _ \\|  _|  \\___ \\ / _ \\/ __| '__/ _ \\ __/ __|
                            | |  | | | |  __/ | |____| | | | (_| | | | | | | |_) |  __/ |    | (_) | |    ____) |  __/ (__| | |  __/ |_\\__ \\
                            |_|  |_| |_|\\___|  \\_____|_| |_|\\__,_|_| |_| |_|_.__/ \\___|_|     \\___/|_|   |_____/ \\___|\\___|_|  \\___|\\__|___/
                                                                                                                                           \s
                                                                                                                                           \s

                        """;
                this.boss = new Boss(100, 100, 1, 30, 0.2, "Basilic", "Sword", "Accio");
            }
            case 3 -> {
                this.number = number;
                this.name = """


                          _______ _            _____      _                                          __                 _         _                \s
                         |__   __| |          |  __ \\    (_)                                        / _|     /\\        | |       | |               \s
                            | |  | |__   ___  | |__) | __ _ ___  ___  _ __  _ __   ___ _ __    ___ | |_     /  \\    ___| | ____ _| |__   __ _ _ __ \s
                            | |  | '_ \\ / _ \\ |  ___/ '__| / __|/ _ \\| '_ \\| '_ \\ / _ \\ '__|  / _ \\|  _|   / /\\ \\  |_  / |/ / _` | '_ \\ / _` | '_ \\\s
                            | |  | | | |  __/ | |   | |  | \\__ \\ (_) | | | | | | |  __/ |    | (_) | |    / ____ \\  / /|   < (_| | |_) | (_| | | | |
                            |_|  |_| |_|\\___| |_|   |_|  |_|___/\\___/|_| |_|_| |_|\\___|_|     \\___/|_|   /_/    \\_\\/___|_|\\_\\__,_|_.__/ \\__,_|_| |_|
                                                                                                                                                   \s
                                                                                                                                                   \s

                        """;
                this.boss = new Boss(100, 100, 1, 10, 0.3, "Détraqueurs", null, "Expecto Patronum");
            }
            case 4 -> {
                this.number = number;
                this.name = """


                          _______ _             _____       _     _      _            __   ______ _         \s
                         |__   __| |           / ____|     | |   | |    | |          / _| |  ____(_)        \s
                            | |  | |__   ___  | |  __  ___ | |__ | | ___| |_    ___ | |_  | |__   _ _ __ ___\s
                            | |  | '_ \\ / _ \\ | | |_ |/ _ \\| '_ \\| |/ _ \\ __|  / _ \\|  _| |  __| | | '__/ _ \\
                            | |  | | | |  __/ | |__| | (_) | |_) | |  __/ |_  | (_) | |   | |    | | | |  __/
                            |_|  |_| |_|\\___|  \\_____|\\___/|_.__/|_|\\___|\\__|  \\___/|_|   |_|    |_|_|  \\___|
                                                                                                            \s
                                                                                                            \s

                        """;
                this.boss = new Boss(100, 100, 1, 70, 0.4, "Voldemort and Peter Pettigrow", null, "Accio");
            }
            case 5 -> {
                this.number = number;
                this.name = """


                          _______ _             ____          _                    __   _   _            _____  _                _     \s
                         |__   __| |           / __ \\        | |                  / _| | | | |          |  __ \\| |              (_)    \s
                            | |  | |__   ___  | |  | |_ __ __| | ___ _ __    ___ | |_  | |_| |__   ___  | |__) | |__   ___ _ __  ___  __
                            | |  | '_ \\ / _ \\ | |  | | '__/ _` |/ _ \\ '__|  / _ \\|  _| | __| '_ \\ / _ \\ |  ___/| '_ \\ / _ \\ '_ \\| \\ \\/ /
                            | |  | | | |  __/ | |__| | | | (_| |  __/ |    | (_) | |   | |_| | | |  __/ | |    | | | |  __/ | | | |>  <\s
                            |_|  |_| |_|\\___|  \\____/|_|  \\__,_|\\___|_|     \\___/|_|    \\__|_| |_|\\___| |_|    |_| |_|\\___|_| |_|_/_/\\_\\
                                                                                                                                       \s
                                                                                                                                       \s

                        """;
                this.boss = new Boss(100, 100, 1, 60, 0.5, "Dolores Ombrage", "Fireworks", null);
            }
            case 6 -> {
                this.number = number;
                this.name = """


                          _______ _            _    _       _  __        ____  _                 _   _____      _               \s
                         |__   __| |          | |  | |     | |/ _|      |  _ \\| |               | | |  __ \\    (_)              \s
                            | |  | |__   ___  | |__| | __ _| | |_ ______| |_) | | ___   ___   __| | | |__) | __ _ _ __   ___ ___\s
                            | |  | '_ \\ / _ \\ |  __  |/ _` | |  _|______|  _ <| |/ _ \\ / _ \\ / _` | |  ___/ '__| | '_ \\ / __/ _ \\
                            | |  | | | |  __/ | |  | | (_| | | |        | |_) | | (_) | (_) | (_| | | |   | |  | | | | | (_|  __/
                            |_|  |_| |_|\\___| |_|  |_|\\__,_|_|_|        |____/|_|\\___/ \\___/ \\__,_| |_|   |_|  |_|_| |_|\\___\\___|
                                                                                                                                \s
                                                                                                                                \s

                        """;
                this.boss = new Boss(100, 100, 1, 40, 0.7, "Mangemorts", null, "Sectumsempra");
            }
            case 7 -> {
                this.number = number;
                this.name = """


                          _______ _            _____             _   _     _         _    _       _ _                  \s
                         |__   __| |          |  __ \\           | | | |   | |       | |  | |     | | |                 \s
                            | |  | |__   ___  | |  | | ___  __ _| |_| |__ | |_   _  | |__| | __ _| | | _____      _____\s
                            | |  | '_ \\ / _ \\ | |  | |/ _ \\/ _` | __| '_ \\| | | | | |  __  |/ _` | | |/ _ \\ \\ /\\ / / __|
                            | |  | | | |  __/ | |__| |  __/ (_| | |_| | | | | |_| | | |  | | (_| | | | (_) \\ V  V /\\__ \\
                            |_|  |_| |_|\\___| |_____/ \\___|\\__,_|\\__|_| |_|_|\\__, | |_|  |_|\\__,_|_|_|\\___/ \\_/\\_/ |___/
                                                                              __/ |                                    \s
                                                                             |___/                                     \s

                        """;
                this.boss = new Boss(100, 100, 1, 100, 0.9, "Voldemort et Bellatrix Lestrange", null, "Expelliarmus");
            }
        }
    }
}
