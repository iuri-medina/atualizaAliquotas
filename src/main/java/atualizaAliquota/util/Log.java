package atualizaAliquota.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Log {
	private static final Logger LOGGER = Logger.getLogger(Log.class.getName());
    private static final String LOG_DIRECTORY = "../log/vratualiza-aliquota-log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_FORMAT_LOG_LINE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void init() {
        // Configurar o formatter
        SimpleFormatter formatter = new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return DATE_FORMAT_LOG_LINE.format(new Date()) + " " + record.getLevel() + " " + record.getMessage() + "\n";
            }
        };

        // Configura o file handler
        File logDir = new File(LOG_DIRECTORY);
        logDir.mkdirs(); // Cria o diretório se não existir
        FileHandler fh = null;
        try {
            String fileName = "vratualiza-aliquota-" + DATE_FORMAT.format(new Date()) + ".log";
            fh = new FileHandler(LOG_DIRECTORY + "/" + fileName, true);
        } catch (IOException e) {
            LOGGER.severe("Erro ao criar FileHandler: " + e.getMessage());
        }
        fh.setFormatter(formatter);
        LOGGER.addHandler(fh);

    }

    public static void info(String message) {
        LOGGER.info(message);
    }


    
}
