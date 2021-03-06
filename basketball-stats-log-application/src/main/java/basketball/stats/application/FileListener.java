package basketball.stats.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

import basketball.stats.enums.Teams;

public class FileListener implements ActionListener {

    final DefaultTableModel dataModel;
    final JComboBox<String> homeAway;
    final JComboBox<Teams> team;
    final JSpinner seasonCount;
    final JSpinner gameCount;

    public FileListener(final DefaultTableModel dataModel, final JComboBox<String> homeAway, final JComboBox<Teams> team, final JSpinner seasonCount,
            final JSpinner gameCount) {
        this.dataModel = dataModel;
        this.homeAway = homeAway;
        this.team = team;
        this.seasonCount = seasonCount;
        this.gameCount = gameCount;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final String fileName = "basketballStats-" + homeAway.getSelectedItem().toString() + "-" + team.getSelectedItem().toString() + "-S"
                + seasonCount.getValue().toString() + "-G" + gameCount.getValue().toString();
        final File outputFile = new File("C:/Users/Anonymous/Desktop/games/" + fileName + ".bbl");
        final File backupFile = new File("C:/Users/Anonymous/Desktop/games/" + fileName + ".bak");

        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            if (!backupFile.exists()) {
                backupFile.createNewFile();
            }

            Files.copy(outputFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            final int rows = dataModel.getRowCount();
            final int columns = dataModel.getColumnCount();

            final StringBuilder builder = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                builder.append(dataModel.getValueAt(i, 0));
                for (int j = 1; j < columns; j++) {
                    builder.append("|").append(dataModel.getValueAt(i, j));
                }
                if (i != rows - 1) {
                    builder.append("\n");
                }
            }

            try (FileWriter writer = new FileWriter(new File("C:/Users/Anonymous/Desktop/games/" + fileName + ".bbl"))) {
                writer.write(builder.toString());
            }

        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
