/*
 * Decompiled with CFR 0.152.
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class DiagnosticLogger {
    private static final LinkedList<String> log = new LinkedList();
    private static final int limit = 10000;

    public static void add(String item) {
        if (log.size() == 10000) {
            log.removeFirst();
        }
        log.add(item);
    }

    public static void writeFile() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
            File file = new File(String.valueOf(Aurum.getPlugin().getDataFolder()) + "/spawnDiagnostics/" + sdf.format(new Date()) + ".log");
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (String str : log) {
                bw.write(str);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

