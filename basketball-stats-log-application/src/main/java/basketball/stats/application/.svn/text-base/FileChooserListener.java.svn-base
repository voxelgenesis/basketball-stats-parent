package basketball.stats.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import basketball.stats.enums.Teams;

public class FileChooserListener implements ActionListener {

    final JFrame applicationFrame;
    final DefaultTableModel dataModel;
    final BasketballTime basketballTime;
    final JTextField timeText;
    final JSpinner teamScoreSpinner;
    final JSpinner opponentScoreSpinner;
    final JSpinner gameSpinner;
    final JSpinner seasonSpinner;
    final JComboBox<Teams> teamsList;

    public FileChooserListener(final JFrame applicationFrame, final DefaultTableModel dataModel, final BasketballTime basketballTime, final JTextField timeText,
            final JSpinner teamScoreSpinner, final JSpinner opponentScoreSpinner, final JSpinner gameSpinner, final JSpinner seasonSpinner,
            final JComboBox<Teams> teamsList) {
        this.applicationFrame = applicationFrame;
        this.dataModel = dataModel;
        this.basketballTime = basketballTime;
        this.timeText = timeText;
        this.teamScoreSpinner = teamScoreSpinner;
        this.opponentScoreSpinner = opponentScoreSpinner;
        this.gameSpinner = gameSpinner;
        this.seasonSpinner = seasonSpinner;
        this.teamsList = teamsList;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final JFileChooser fc = new JFileChooser(new File("C:\\Users\\Anonymous\\Desktop\\games"));
        fc.showOpenDialog(applicationFrame);

        if (fc.getSelectedFile() == null) {
            return;
        }

        try (final Scanner reader = new Scanner(fc.getSelectedFile())) {
            if (reader.hasNext()) {
                dataModel.setRowCount(0);
            }
            while (reader.hasNext()) {
                final String splitLines = reader.nextLine();
                dataModel.addRow(splitLines.split("\\|"));
            }

            final String fileName = fc.getSelectedFile().getName();
            final String gameNumber = fileName.substring(fileName.indexOf("-G") + 2, fileName.indexOf("."));
            final String seasonNumber = fileName.substring(fileName.indexOf("-S") + 2, fileName.indexOf("-G"));
            final String opponent = fileName.substring(fileName.indexOf("-") + 6, fileName.indexOf("-S"));
            gameSpinner.setValue(Integer.parseInt(gameNumber));
            seasonSpinner.setValue(Integer.parseInt(seasonNumber));
            teamsList.setSelectedItem(Teams.valueOf(opponent));

            final String[] timeStrings = ((String) dataModel.getValueAt(dataModel.getRowCount() - 1, 1)).split(":");
            final Integer minutes = Integer.parseInt(timeStrings[0]);
            final Integer seconds = Integer.parseInt(timeStrings[1].split("\\.")[0]);
            final Integer splitSeconds = Integer.parseInt(timeStrings[1].split("\\.")[1]);

            basketballTime.setTime(minutes, seconds, splitSeconds);
            timeText.setText(basketballTime.formatRemainingTime());
            teamScoreSpinner.setValue(Integer.parseInt((String) dataModel.getValueAt(dataModel.getRowCount() - 1, 6)));
            opponentScoreSpinner.setValue(Integer.parseInt((String) dataModel.getValueAt(dataModel.getRowCount() - 1, 7)));
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    // columnNames.add("Shot Clock");
    // columnNames.add("Time");
    // columnNames.add("Player");
    // columnNames.add("Possession");
    // columnNames.add("Event Type");
    // columnNames.add("Event Detail");
    // columnNames.add("Team Score");
    // columnNames.add("Opponent Score");

}
