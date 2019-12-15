package be.gib.helper.calendar.builder.jfxtra;

import be.gib.helper.calendar.builder.CalendarFactory;
import be.gib.helper.core.Scheduler;
import be.gib.helper.core.bean.Show;
import be.gib.helper.core.bean.TimeSlot;
import javafx.scene.Node;
import jfxtras.scene.control.agenda.Agenda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarFactoryImpl implements CalendarFactory {
    @Override
    public Node getLoadedCalendar(List<Scheduler> schedulers) {
        Agenda agenda = new Agenda();
        agenda.appointments().addAll(loadAppointments(schedulers));

        return agenda;
    }

    private List<Agenda.Appointment> loadAppointments(List<Scheduler> schedulers) {
        ArrayList<Agenda.Appointment> appointments = new ArrayList<>();
        for (Scheduler scheduler : schedulers) {
            for (TimeSlot timeSlot : scheduler.getTimeSlots()) {
                Show show = timeSlot.getShow();
                for (Date date : timeSlot.getStartDates()) {
                    Agenda.AppointmentImpl app = new Agenda.AppointmentImpl();
                    Calendar start = getCalendarDate(date);
                    Calendar end = getCalendarDate(timeSlot, date);
                    app.setStartTime(start);
                    app.setEndTime(end);
                    app.setAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1"));
                    app.setDescription(show.getTitle());
                    app.setSummary(show.getCountry());
                    appointments.add(app);
                }
            }
        }
        return appointments;
    }

    private Calendar getCalendarDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Calendar getCalendarDate(TimeSlot timeSlot, Date startDate) {
        return getCalendarDate(timeSlot.getEndDate(startDate));
    }
}
