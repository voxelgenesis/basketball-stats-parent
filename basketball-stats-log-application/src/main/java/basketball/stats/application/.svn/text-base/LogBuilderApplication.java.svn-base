package basketball.stats.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import basketball.stats.enums.EventDetail;
import basketball.stats.enums.EventType;
import basketball.stats.enums.Players;
import basketball.stats.enums.Possession;
import basketball.stats.enums.Teams;

public class LogBuilderApplication {

    private JFrame applicationFrame;
    private final BasketballTime basketballTime = new BasketballTime();

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final LogBuilderApplication window = new LogBuilderApplication();
                    window.applicationFrame.setVisible(true);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public LogBuilderApplication() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        applicationFrame = new JFrame();
        applicationFrame.setBounds(100, 100, 1500, 900);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLUE);
        gamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        final JComboBox<String> homeAwayList = new JComboBox<String>();
        homeAwayList.setModel(new DefaultComboBoxModel<String>(Arrays.asList("HOME", "AWAY").toArray(new String[0])));
        gamePanel.add(homeAwayList);

        final JComboBox<Teams> teamsList = new JComboBox<Teams>();
        teamsList.setModel(new DefaultComboBoxModel<>(Teams.values()));
        gamePanel.add(teamsList);

        final JSpinner seasonSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        final JSpinner gameSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 110, 1));

        gamePanel.add(seasonSpinner);
        gamePanel.add(gameSpinner);

        // --- //

        final JPanel entryPanel = new JPanel();
        entryPanel.setBackground(Color.GREEN);
        entryPanel.setLayout(new GridLayout(0, 1));

        final JPanel viewPanel = new JPanel();
        viewPanel.setBackground(Color.RED);
        viewPanel.setLayout(new BoxLayout(viewPanel, BoxLayout.Y_AXIS));

        final JSpinner shotClockSpinner = new JSpinner(new SpinnerNumberModel(24, 0, 24, 1));
        shotClockSpinner.setFont(new Font("Tahoma", Font.PLAIN, 28));
        shotClockSpinner.setPreferredSize(new Dimension(50, 30));
        // entryPanel.add(shotClockSpinner);

        final SpringLayout timeLayout = new SpringLayout();
        final JPanel timePanel = new JPanel();
        // timePanel.setPreferredSize(new Dimension(135, 30));
        timePanel.setBackground(Color.CYAN);

        final JTextField timeText = new JTextField();
        timeText.setFont(new Font("Tahoma", Font.PLAIN, 26));
        timeText.setText(basketballTime.formatRemainingTime());
        timePanel.add(timeText);

        final JButton decrementTimeButton = new JButton("-");
        decrementTimeButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        decrementTimeButton.setMargin(new Insets(0, 0, 0, 0));
        decrementTimeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                basketballTime.decrement();
                timeText.setText(basketballTime.formatRemainingTime());
            }
        });
        timePanel.add(decrementTimeButton);

        final JButton decrementSplitTimeButton = new JButton("--");
        decrementSplitTimeButton.setFont(new Font("Tahoma", Font.PLAIN, 28));
        decrementSplitTimeButton.setMargin(new Insets(0, 0, 0, 0));
        decrementSplitTimeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                basketballTime.decrementSplitSecond();
                timeText.setText(basketballTime.formatRemainingTime());
            }
        });
        timePanel.add(decrementSplitTimeButton);

        timeLayout.putConstraint(SpringLayout.NORTH, shotClockSpinner, 0, SpringLayout.NORTH, timePanel);
        timeLayout.putConstraint(SpringLayout.WEST, shotClockSpinner, 0, SpringLayout.WEST, timePanel);
        timeLayout.putConstraint(SpringLayout.SOUTH, shotClockSpinner, 0, SpringLayout.SOUTH, timePanel);
        timeLayout.putConstraint(SpringLayout.EAST, shotClockSpinner, 80, SpringLayout.WEST, timePanel);
        timePanel.add(shotClockSpinner);

        timeLayout.putConstraint(SpringLayout.NORTH, timeText, 0, SpringLayout.NORTH, timePanel);
        timeLayout.putConstraint(SpringLayout.WEST, timeText, 80, SpringLayout.WEST, timePanel);
        timeLayout.putConstraint(SpringLayout.SOUTH, timeText, 0, SpringLayout.SOUTH, timePanel);
        timeLayout.putConstraint(SpringLayout.EAST, timeText, 240, SpringLayout.WEST, timePanel);

        timeLayout.putConstraint(SpringLayout.NORTH, decrementTimeButton, 0, SpringLayout.NORTH, timePanel);
        timeLayout.putConstraint(SpringLayout.WEST, decrementTimeButton, 240, SpringLayout.WEST, timePanel);
        timeLayout.putConstraint(SpringLayout.SOUTH, decrementTimeButton, 0, SpringLayout.SOUTH, timePanel);
        timeLayout.putConstraint(SpringLayout.EAST, decrementTimeButton, 280, SpringLayout.WEST, timePanel);

        timeLayout.putConstraint(SpringLayout.NORTH, decrementSplitTimeButton, 0, SpringLayout.NORTH, timePanel);
        timeLayout.putConstraint(SpringLayout.WEST, decrementSplitTimeButton, 280, SpringLayout.WEST, timePanel);
        timeLayout.putConstraint(SpringLayout.SOUTH, decrementSplitTimeButton, 0, SpringLayout.SOUTH, timePanel);
        timeLayout.putConstraint(SpringLayout.EAST, decrementSplitTimeButton, 320, SpringLayout.WEST, timePanel);

        timePanel.setLayout(timeLayout);

        // entryPanel.add(timePanel);

        final JComboBox<Players> playersList = buildPlayersList();
        playersList.setFont(new Font("Tahoma", Font.PLAIN, 28));

        final JComboBox<Possession> possessionList = new JComboBox<Possession>();
        possessionList.setModel(new DefaultComboBoxModel<Possession>(Possession.values()));
        possessionList.setFont(new Font("Tahoma", Font.PLAIN, 32));

        final JComboBox<EventType> eventTypeList = new JComboBox<EventType>();
        eventTypeList.setModel(new DefaultComboBoxModel<EventType>(EventType.values()));
        eventTypeList.setMaximumRowCount(20);
        eventTypeList.setFont(new Font("Tahoma", Font.PLAIN, 28));

        final JComboBox<EventDetail> eventDetailsList = new JComboBox<EventDetail>();
        eventDetailsList.setModel(new DefaultComboBoxModel<EventDetail>(EventDetail.values()));
        eventDetailsList.setSelectedItem(null);
        eventDetailsList.setMaximumRowCount(30);

        eventTypeList.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(final ItemEvent e) {
                @SuppressWarnings("unchecked")
                final
                JComboBox<EventType> jlist = (JComboBox<EventType>) e.getSource();
                final EventType type = (EventType) jlist.getSelectedItem();
                eventDetailsList.setModel(new DefaultComboBoxModel<EventDetail>(EventDetail.getEventDetails(type)));
            }
        });

        final JPanel teamScorePanel = new JPanel(new GridLayout(1, 4));

        final JSpinner teamScoreSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        teamScoreSpinner.setFont(new Font("Tahoma", Font.PLAIN, 28));
        teamScoreSpinner.setPreferredSize(new Dimension((int) teamScoreSpinner.getPreferredSize().getWidth(), 30));
        teamScorePanel.add(teamScoreSpinner);

        teamScorePanel.add(createSpinnerPlusButton(teamScoreSpinner, 1));
        teamScorePanel.add(createSpinnerPlusButton(teamScoreSpinner, 2));
        teamScorePanel.add(createSpinnerPlusButton(teamScoreSpinner, 3));

        final JPanel opponentScorePanel = new JPanel(new GridLayout(1, 4));

        final JSpinner opponentScoreSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
        opponentScoreSpinner.setFont(new Font("Tahoma", Font.PLAIN, 28));
        opponentScoreSpinner.setPreferredSize(new Dimension((int) opponentScoreSpinner.getPreferredSize().getWidth(), 30));
        opponentScorePanel.add(opponentScoreSpinner);

        opponentScorePanel.add(createSpinnerPlusButton(opponentScoreSpinner, 1));
        opponentScorePanel.add(createSpinnerPlusButton(opponentScoreSpinner, 2));
        opponentScorePanel.add(createSpinnerPlusButton(opponentScoreSpinner, 3));

        final JButton submitButton = new JButton("Add Entry");
        submitButton.setPreferredSize(new Dimension((int) submitButton.getPreferredSize().getWidth(), 60));

        final JPanel extraPanel = new JPanel(new GridLayout(1, 2));

        final JCheckBox fastBreakCheckBox = new JCheckBox("Fast Break");
        fastBreakCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 28));
        extraPanel.add(fastBreakCheckBox);

        final JComboBox<Players> assistPlayersList = buildPlayersList();
        extraPanel.add(assistPlayersList);
        // assistPlayersList.setVisible(false);

        final JTable table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
            }
        });

        final Vector<String> columnNames = new Vector<>();
        columnNames.add("Shot Clock");
        columnNames.add("Time");
        columnNames.add("Possession");
        columnNames.add("Player");
        columnNames.add("Event Type");
        columnNames.add("Event Detail");
        columnNames.add("Team Score");
        columnNames.add("Opponent Score");

        final DefaultTableModel dataModel = new DefaultTableModel(columnNames, 0);
        table.setModel(dataModel);
        viewPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        table.getColumnModel().getColumn(3).setPreferredWidth(125);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                if (possessionList.getSelectedItem() == Possession.OFFENSE) {
                    final String detail = eventDetailsList.getSelectedItem() == null ? "" : eventDetailsList.getSelectedItem().toString();
                    if (detail.startsWith("MADE_SHOT")) {
                        if (detail.contains("FREE_THROW")) {
                            teamScoreSpinner.setValue((Integer) teamScoreSpinner.getValue() + 1);
                        } else if (detail.contains("THREE")) {
                            teamScoreSpinner.setValue((Integer) teamScoreSpinner.getValue() + 3);
                        } else {
                            teamScoreSpinner.setValue((Integer) teamScoreSpinner.getValue() + 2);
                        }
                    }
                }
                if (possessionList.getSelectedItem() == Possession.DEFENSE) {
                    final String detail = eventDetailsList.getSelectedItem() == null ? "" : eventDetailsList.getSelectedItem().toString();
                    if (detail.startsWith("MADE_SHOT")) {
                        if (detail.contains("FREE_THROW")) {
                            opponentScoreSpinner.setValue((Integer) opponentScoreSpinner.getValue() + 1);
                        } else if (detail.contains("THREE")) {
                            opponentScoreSpinner.setValue((Integer) opponentScoreSpinner.getValue() + 3);
                        } else {
                            opponentScoreSpinner.setValue((Integer) opponentScoreSpinner.getValue() + 2);
                        }
                    }
                }

                if (fastBreakCheckBox.isSelected()) {
                    final List<String> data = new ArrayList<String>();
                    data.add(shotClockSpinner.getValue().toString());
                    data.add(basketballTime.formatRealTime());
                    data.add(possessionList.getSelectedItem() == null ? "" : possessionList.getSelectedItem().toString());
                    data.add(playersList.getSelectedItem() == null ? "" : playersList.getSelectedItem().toString());
                    data.add(EventType.FAST_BREAK.toString());
                    data.add("");
                    data.add(teamScoreSpinner.getValue().toString());
                    data.add(opponentScoreSpinner.getValue().toString());
                    dataModel.addRow(data.toArray(new String[data.size()]));
                }

                if (eventTypeList.getSelectedItem() != null && eventTypeList.getSelectedItem() == EventType.MADE_ASSISTED_SHOT) {
                    final List<String> data = new ArrayList<String>();
                    data.add(shotClockSpinner.getValue().toString());
                    data.add(basketballTime.formatRealTime());
                    data.add(possessionList.getSelectedItem() == null ? "" : possessionList.getSelectedItem().toString());
                    data.add(assistPlayersList.getSelectedItem() == null ? "" : assistPlayersList.getSelectedItem().toString());
                    data.add(EventType.ASSIST.toString());
                    data.add("");
                    data.add(teamScoreSpinner.getValue().toString());
                    data.add(opponentScoreSpinner.getValue().toString());
                    dataModel.addRow(data.toArray(new String[data.size()]));
                }

                final List<String> data = new ArrayList<String>();
                data.add(shotClockSpinner.getValue().toString());
                data.add(basketballTime.formatRealTime());
                data.add(possessionList.getSelectedItem() == null ? "" : possessionList.getSelectedItem().toString());
                data.add(playersList.getSelectedItem() == null ? "" : playersList.getSelectedItem().toString());
                data.add(eventTypeList.getSelectedItem() == null ? "" : eventTypeList.getSelectedItem().toString());
                data.add(eventDetailsList.getSelectedItem() == null ? "" : eventDetailsList.getSelectedItem().toString());

                data.add(teamScoreSpinner.getValue().toString());
                data.add(opponentScoreSpinner.getValue().toString());
                dataModel.addRow(data.toArray(new String[data.size()]));

                assistPlayersList.setSelectedItem(null);
                fastBreakCheckBox.setSelected(false);
                shotClockSpinner.setValue(new Integer(24));
                playersList.setSelectedItem(null);

                if (possessionList.getSelectedItem() == Possession.DEFENSE && eventTypeList.getSelectedItem() == EventType.REBOUND) {
                    possessionList.setSelectedItem(Possession.OFFENSE);
                    return;
                }
                if (possessionList.getSelectedItem() == Possession.OFFENSE && eventTypeList.getSelectedItem() == EventType.TURNOVER) {
                    possessionList.setSelectedItem(Possession.DEFENSE);
                    return;
                }
                if (possessionList.getSelectedItem() == Possession.DEFENSE && eventTypeList.getSelectedItem() == EventType.STEAL) {
                    possessionList.setSelectedItem(Possession.OFFENSE);
                    return;
                }
                if (possessionList.getSelectedItem() == Possession.OFFENSE && eventTypeList.getSelectedItem() == EventType.FOUL) {
                    possessionList.setSelectedItem(Possession.DEFENSE);
                    return;
                }
                if (possessionList.getSelectedItem() == Possession.OFFENSE
                        && (eventTypeList.getSelectedItem() == EventType.MADE_SHOT || eventTypeList.getSelectedItem() == EventType.MADE_ASSISTED_SHOT || eventTypeList
                                .getSelectedItem() == EventType.MISSED_SHOT)) {
                    possessionList.setSelectedItem(Possession.DEFENSE);
                    return;
                }
                if (possessionList.getSelectedItem() == Possession.DEFENSE && (eventTypeList.getSelectedItem() == EventType.MADE_SHOT)) {
                    possessionList.setSelectedItem(Possession.OFFENSE);
                    return;
                }
                // eventDetailsList.setSelectedItem(null);
                // eventTypeList.setSelectedItem(null);

                // table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
            }
        });

        final JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(1920, 60));
        panel.setLayout(new GridLayout(1, 2));

        final JButton loadButton = new JButton();
        loadButton.setText("Load");
        loadButton.addActionListener(new FileChooserListener(applicationFrame, dataModel, basketballTime, timeText, teamScoreSpinner, opponentScoreSpinner, gameSpinner,
                seasonSpinner, teamsList));

        final JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new FileListener(dataModel, homeAwayList, teamsList, seasonSpinner, gameSpinner));

        panel.add(loadButton);
        panel.add(saveButton);
        viewPanel.add(panel);

        final SpringLayout springLayout = new SpringLayout();
        applicationFrame.getContentPane().setLayout(springLayout);

        final JLabel label = new JLabel(new ImageIcon("src/main/resources/images/basketball-base.png"));
        label.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(final MouseEvent e) {
                visualizeShotLocation(eventDetailsList, eventTypeList, label, e.getX(), e.getY());
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                visualizeShotLocation(eventDetailsList, eventTypeList, label, e.getX(), e.getY());
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                // Do nothing.
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                // Do nothing.
            }

            @Override
            public void mouseClicked(final MouseEvent e) {
                visualizeShotLocation(eventDetailsList, eventTypeList, label, e.getX(), e.getY());
            }
        });

        springLayout.putConstraint(SpringLayout.NORTH, gamePanel, 0, SpringLayout.NORTH, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, gamePanel, -320, SpringLayout.EAST, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, gamePanel, 150, SpringLayout.NORTH, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, gamePanel, 0, SpringLayout.EAST, applicationFrame.getContentPane());
        applicationFrame.getContentPane().add(gamePanel);

        springLayout.putConstraint(SpringLayout.NORTH, entryPanel, 0, SpringLayout.SOUTH, gamePanel);
        springLayout.putConstraint(SpringLayout.WEST, entryPanel, -320, SpringLayout.EAST, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, entryPanel, 417, SpringLayout.SOUTH, gamePanel);
        springLayout.putConstraint(SpringLayout.EAST, entryPanel, 0, SpringLayout.EAST, applicationFrame.getContentPane());
        applicationFrame.getContentPane().add(entryPanel);

        springLayout.putConstraint(SpringLayout.NORTH, label, 0, SpringLayout.SOUTH, entryPanel);
        springLayout.putConstraint(SpringLayout.WEST, label, -320, SpringLayout.EAST, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, label, 235, SpringLayout.SOUTH, entryPanel);
        springLayout.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, applicationFrame.getContentPane());
        applicationFrame.getContentPane().add(label);

        springLayout.putConstraint(SpringLayout.NORTH, submitButton, 0, SpringLayout.SOUTH, label);
        springLayout.putConstraint(SpringLayout.WEST, submitButton, -320, SpringLayout.EAST, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, submitButton, 0, SpringLayout.SOUTH, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, submitButton, 0, SpringLayout.EAST, applicationFrame.getContentPane());
        applicationFrame.getContentPane().add(submitButton);

        springLayout.putConstraint(SpringLayout.NORTH, viewPanel, 0, SpringLayout.NORTH, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, viewPanel, 0, SpringLayout.WEST, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, viewPanel, 0, SpringLayout.SOUTH, applicationFrame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, viewPanel, -321, SpringLayout.EAST, applicationFrame.getContentPane());
        applicationFrame.getContentPane().add(viewPanel);

        entryPanel.add(timePanel);
        entryPanel.add(teamScorePanel);
        entryPanel.add(opponentScorePanel);
        entryPanel.add(possessionList);
        entryPanel.add(playersList);
        entryPanel.add(eventTypeList);
        entryPanel.add(extraPanel);
        entryPanel.add(eventDetailsList);

    }

    private JButton createSpinnerPlusButton(final JSpinner spinner, final int amount) {
        final JButton button = new JButton("+" + amount);
        button.setFont(new Font("Tahoma", Font.PLAIN, 28));
        button.setMargin(new Insets(1, 1, 1, 1));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                spinner.setValue(((Integer) spinner.getValue()) + amount);
            }
        });

        button.setEnabled(false);
        return button;
    }

    private void setShotLocation(final JComboBox<EventDetail> eventDetailsList, final JLabel label, final EventDetail detail) {
        eventDetailsList.setSelectedItem(detail);
        label.setIcon(new ImageIcon("src/main/resources/images/" + detail.toString() + ".png"));
    }

    private void visualizeShotLocation(final JComboBox<EventDetail> eventDetailsList, final JComboBox<EventType> eventTypeList, final JLabel label, final int x,
            final int y) {
        boolean isMadeShot = true;
        if (eventTypeList.getSelectedItem() == EventType.MISSED_SHOT || eventTypeList.getSelectedItem() == EventType.BLOCK) {
            isMadeShot = false;
        } else if (eventTypeList.getSelectedItem() != EventType.MADE_SHOT && eventTypeList.getSelectedItem() != EventType.MADE_ASSISTED_SHOT) {
            return;
        }
        if (x <= 19 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_CORNER_THREE : EventDetail.MISSED_SHOT_LEFT_CORNER_THREE);
            return;
        }
        if (x > 19 && x <= 64 && y <= 45) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_FAR_BASELINE : EventDetail.MISSED_SHOT_LEFT_FAR_BASELINE);
            return;
        }
        if (x > 19 && x <= 64 && y > 45 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_FAR_MID : EventDetail.MISSED_SHOT_LEFT_FAR_MID);
            return;
        }
        if (x > 64 && x <= 109 && y <= 45) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_NEAR_BASELINE : EventDetail.MISSED_SHOT_LEFT_NEAR_BASELINE);
            return;
        }
        if (x > 64 && x <= 109 && y > 45 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_NEAR_MID : EventDetail.MISSED_SHOT_LEFT_NEAR_MID);
            return;
        }
        if (x > 64 && x <= 109 && y > 90 && y < 110) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_NEAR_DIAGONAL : EventDetail.MISSED_SHOT_LEFT_NEAR_DIAGONAL);
            return;
        }
        if (x > 22 && x <= 89 && y >= 110 && y < 164) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_FAR_DIAGONAL : EventDetail.MISSED_SHOT_LEFT_FAR_DIAGONAL);
            return;
        }
        if (x < 109 && y >= 164 && y < 210) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_DIAGONAL_THREE : EventDetail.MISSED_SHOT_LEFT_DIAGONAL_THREE);
            return;
        }
        if (y >= 210) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_FAR_THREE : EventDetail.MISSED_SHOT_FAR_THREE);
            return;
        }
        if (x > 109 && x <= 160 && y >= 59 && y < 120) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_FAR_POST : EventDetail.MISSED_SHOT_LEFT_FAR_POST);
            return;
        }
        if (x > 160 && x <= 212 && y >= 59 && y < 120) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_FAR_POST : EventDetail.MISSED_SHOT_RIGHT_FAR_POST);
            return;
        }
        if (x > 109 && x <= 212 && y >= 120 && y < 153) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_NEAR_TOP_OF_KEY : EventDetail.MISSED_SHOT_NEAR_TOP_OF_KEY);
            return;
        }
        if (x > 109 && x <= 212 && y >= 143 && y < 183) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_FAR_TOP_OF_KEY : EventDetail.MISSED_SHOT_FAR_TOP_OF_KEY);
            return;
        }

        if (x > 109 && x <= 212 && y >= 183 && y < 210) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_CENTER_THREE : EventDetail.MISSED_SHOT_CENTER_THREE);
            return;
        }
        if (x > 300 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_CORNER_THREE : EventDetail.MISSED_SHOT_RIGHT_CORNER_THREE);
            return;
        }
        if (x > 257 && x <= 300 && y <= 45) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_FAR_BASELINE : EventDetail.MISSED_SHOT_RIGHT_FAR_BASELINE);
            return;
        }
        if (x > 212 && x <= 257 && y <= 45) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_NEAR_BASELINE : EventDetail.MISSED_SHOT_RIGHT_NEAR_BASELINE);
            return;
        }
        if (x > 257 && x <= 300 && y > 45 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_FAR_MID : EventDetail.MISSED_SHOT_RIGHT_FAR_MID);
            return;
        }
        if (x > 212 && x <= 257 && y > 45 && y <= 90) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_NEAR_MID : EventDetail.MISSED_SHOT_RIGHT_NEAR_MID);
            return;
        }
        if (x > 212 && x <= 257 && y > 90 && y < 110) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_NEAR_DIAGONAL : EventDetail.MISSED_SHOT_RIGHT_NEAR_DIAGONAL);
            return;
        }
        if (x > 229 && x <= 294 && y >= 110 && y < 164) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_FAR_DIAGONAL : EventDetail.MISSED_SHOT_RIGHT_FAR_DIAGONAL);
            return;
        }
        if (x > 212 && y >= 164 && y < 210) {
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_DIAGONAL_THREE : EventDetail.MISSED_SHOT_RIGHT_DIAGONAL_THREE);
            return;
        }
        // inside left near post
        if (x > 110 && x <= 160 && y < 60) {
            if (x >= 149 && x <= 172 && y > 14 && y <= 36) {
                setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_DUNK : EventDetail.MISSED_SHOT_DUNK);
                return;
            }
            if (x >= 140 && x <= 180 && y <= 14) {
                setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LAYUP : EventDetail.MISSED_SHOT_LAYUP);
                return;
            }
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LEFT_NEAR_POST : EventDetail.MISSED_SHOT_LEFT_NEAR_POST);
            return;
        }

        // inside right near post
        if (x > 160 && x < 212 && y < 60) {
            if (x >= 149 && x <= 172 && y > 14 && y <= 36) {
                setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_DUNK : EventDetail.MISSED_SHOT_DUNK);
                return;
            }
            if (x >= 140 && x <= 180 && y <= 14) {
                setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_LAYUP : EventDetail.MISSED_SHOT_LAYUP);
                return;
            }
            setShotLocation(eventDetailsList, label, isMadeShot ? EventDetail.MADE_SHOT_RIGHT_NEAR_POST : EventDetail.MISSED_SHOT_RIGHT_NEAR_POST);
            return;
        }

        eventDetailsList.setSelectedItem(null);
        label.setIcon(new ImageIcon("src/main/resources/images/basketball-base.png"));
    }

    private JComboBox<Players> buildPlayersList() {
        final JComboBox<Players> playersList = new JComboBox<Players>();
        playersList.setModel(new DefaultComboBoxModel<>(Players.values()));
        playersList.setSelectedItem(null);
        playersList.setMaximumRowCount(20);
        return playersList;
    }
}
