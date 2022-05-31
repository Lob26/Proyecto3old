package com.uniandes.lithub.view.left.interest;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.MinMaxDateEvaluator;
import org.joda.time.DateTime;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("serial")
public class DayTimeHolder extends JPanel {
    private final JCalendar calendar;

    public DayTimeHolder() {
        setBorder(new CompoundBorder(new MatteBorder(3, 0, 0, 0, new Color(0, 0, 0)),
                new EmptyBorder(2, 2, 2, 2)));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{446, 0};
        gridBagLayout.rowHeights = new int[]{135, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        calendar = new JCalendar();
        calendar.getDayChooser().getDayPanel().setFocusable(false);
        calendar.setWeekOfYearVisible(false);
        calendar.getDayChooser().setAlwaysFireDayProperty(false);
        calendar.getDayChooser().setWeekOfYearVisible(false);
        calendar.getDayChooser().getDayPanel().setForeground(new Color(64, 64, 64));
        GridBagConstraints gbc_calendar = new GridBagConstraints();
        gbc_calendar.insets = new Insets(0, 0, 5, 0);
        gbc_calendar.fill = GridBagConstraints.BOTH;
        gbc_calendar.gridx = 0;
        gbc_calendar.gridy = 0;
        add(calendar, gbc_calendar);

        JPanel colorInfo = new JPanel();
        GridBagConstraints gbc_colorInfo = new GridBagConstraints();
        gbc_colorInfo.fill = GridBagConstraints.BOTH;
        gbc_colorInfo.gridx = 0;
        gbc_colorInfo.gridy = 1;
        add(colorInfo, gbc_colorInfo);
        GridBagLayout gbl_colorInfo = new GridBagLayout();
        gbl_colorInfo.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gbl_colorInfo.rowHeights = new int[]{0, 0};
        gbl_colorInfo.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_colorInfo.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        colorInfo.setLayout(gbl_colorInfo);

        JLabel less = new JLabel("Poco");
        GridBagConstraints gbc_less = new GridBagConstraints();
        gbc_less.anchor = GridBagConstraints.EAST;
        gbc_less.insets = new Insets(0, 0, 0, 5);
        gbc_less.gridx = 0;
        gbc_less.gridy = 0;
        colorInfo.add(less, gbc_less);

        JLabel lvl1 = new JLabel("01");
        GridBagConstraints gbc_lvl1 = new GridBagConstraints();
        gbc_lvl1.insets = new Insets(0, 0, 0, 5);
        gbc_lvl1.gridx = 1;
        gbc_lvl1.gridy = 0;
        colorInfo.add(lvl1, gbc_lvl1);

        JLabel lvl2 = new JLabel("12");
        GridBagConstraints gbc_lvl2 = new GridBagConstraints();
        gbc_lvl2.insets = new Insets(0, 0, 0, 5);
        gbc_lvl2.gridx = 2;
        gbc_lvl2.gridy = 0;
        colorInfo.add(lvl2, gbc_lvl2);

        JLabel lvl3 = new JLabel("23");
        GridBagConstraints gbc_lvl3 = new GridBagConstraints();
        gbc_lvl3.insets = new Insets(0, 0, 0, 5);
        gbc_lvl3.gridx = 3;
        gbc_lvl3.gridy = 0;
        colorInfo.add(lvl3, gbc_lvl3);

        JLabel lvl4 = new JLabel("34");
        GridBagConstraints gbc_lvl4 = new GridBagConstraints();
        gbc_lvl4.insets = new Insets(0, 0, 0, 5);
        gbc_lvl4.gridx = 4;
        gbc_lvl4.gridy = 0;
        colorInfo.add(lvl4, gbc_lvl4);

        JLabel lvl5 = new JLabel("45");
        GridBagConstraints gbc_lvl5 = new GridBagConstraints();
        gbc_lvl5.insets = new Insets(0, 0, 0, 5);
        gbc_lvl5.gridx = 5;
        gbc_lvl5.gridy = 0;
        colorInfo.add(lvl5, gbc_lvl5);

        JLabel more = new JLabel("Mucho");
        GridBagConstraints gbc_more = new GridBagConstraints();
        gbc_more.fill = GridBagConstraints.HORIZONTAL;
        gbc_more.gridx = 6;
        gbc_more.gridy = 0;
        colorInfo.add(more, gbc_more);
    }

    public DayTimeHolder(Map<DateTime, Long> timeByDay) {
        this();
        try {
            generatorColorLvl(timeByDay);
            revalidate();
            repaint();
        } catch (NoSuchElementException e) {
        }
    }

    private void generatorColorLvl(Map<DateTime, Long> timeByDay) throws NoSuchElementException {
        long maxValue;
        long minValue;
        long step;
        Collection<Long> values = timeByDay.values();
        maxValue = Collections.max(values);
        minValue = Collections.min(values);
        step = (maxValue - minValue) / 4;
        Predicate<Long> r01 = num -> (0 <= num && num <= minValue);
        Predicate<Long> r12 = num -> (minValue < num && num <= minValue + step);
        Predicate<Long> r23 = num -> (minValue + step < num && num <= minValue + 2 * step);
        Predicate<Long> r34 = num -> (minValue + 2 * step < num && num <= maxValue - 2 * step);
        Predicate<Long> r45 = num -> (maxValue - step < num && num <= maxValue);

        List<Date> lvl1 = new ArrayList<Date>();
        List<Date> lvl2 = new ArrayList<Date>();
        List<Date> lvl3 = new ArrayList<Date>();
        List<Date> lvl4 = new ArrayList<Date>();
        List<Date> lvl5 = new ArrayList<Date>();

        timeByDay.forEach((k, v) -> {
            if (r01.test(v)) {
                lvl1.add(k.toDate());
            } else if (r12.test(v)) {
                lvl2.add(k.toDate());
            } else if (r23.test(v)) {
                lvl3.add(k.toDate());
            } else if (r34.test(v)) {
                lvl4.add(k.toDate());
            } else if (r45.test(v)) {
                lvl5.add(k.toDate());
            }
        });

        calendar.getDayChooser().addDateEvaluator(new HighlightLevel(lvl5, new Color(53, 212, 84)));
        calendar.getDayChooser().addDateEvaluator(new HighlightLevel(lvl4, new Color(37, 166, 64)));
        calendar.getDayChooser().addDateEvaluator(new HighlightLevel(lvl3, new Color(0, 109, 52)));
        calendar.getDayChooser().addDateEvaluator(new HighlightLevel(lvl2, new Color(14, 68, 40)));
        calendar.getDayChooser().addDateEvaluator(new HighlightLevel(lvl1, new Color(22, 27, 33)));
    }

}

class HighlightLevel extends MinMaxDateEvaluator {

    private final List<Date> lvlList;
    private final Color bg;

    public HighlightLevel(List<Date> lvl, Color lvlColor) {
        this.lvlList = lvl;
        this.bg = lvlColor;
    }

    @Override
    public Color getSpecialBackroundColor() {
        return this.bg;
    }

    @Override
    public Color getSpecialForegroundColor() {
        return Color.BLACK;
    }

    @Override
    public boolean isSpecial(Date arg0) {
        return this.lvlList.contains(arg0);
    }

}
