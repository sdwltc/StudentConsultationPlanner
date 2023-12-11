import java.util.LinkedList;
import java.util.Scanner;
class UniversityOffice
{
    private LinkedList<Student> arriving;
    private LinkedList<Student> waiting;
    private Student currentStudent;
    private int consultEndTime;

    public UniversityOffice()
    {
        arriving = new LinkedList<>();
        waiting = new LinkedList<>();
        currentStudent = null;
        consultEndTime = -1;
    }

    public void readInput()
    {
        Scanner in = new Scanner(System.in);
        int numberStudents = in.nextInt();

        for (int i = 0; i < numberStudents; i++)
        {
            int arrivalTime = in.nextInt();
            String name = in.next();
            Student s = new Student(arrivalTime, name);
            arriving.add(s);
        }
    }

    public void simulateConsultations()
    {
        boolean noMoreMeetings = arriving.isEmpty();

        for (int time = 0; !noMoreMeetings; time++) {
            handleStudentArrival(time);
            handleStudentDeparture(time);
            handleNewStudentConsultation(time);

            noMoreMeetings = checkNoMoreMeetings(time);
        }
    }

    private void handleStudentArrival(int time)
    {
        Student s = arriving.peek();
        if ((s != null) && (s.getArrivalTime() == time))
        {
            System.out.println(time + ": " + s.getName() + " arrives.");
            waiting.add(s);
            arriving.remove(s);
        }
    }

    private void handleStudentDeparture(int time)
    {
        if (consultEndTime == time)
        {
            System.out.println(time + ": " + currentStudent.getName() + " leaves.");
            currentStudent = null;
        }
    }

    private void handleNewStudentConsultation(int time)
    {
        if ((consultEndTime <= time) && (!waiting.isEmpty()))
        {
            currentStudent = waiting.peek();
            consultEndTime = time + 15;
            waiting.remove(currentStudent);
            System.out.println(time + ": " + currentStudent.getName() + " sees professor.");
        }
    }

    private boolean checkNoMoreMeetings(int time) {
        return arriving.isEmpty() && waiting.isEmpty() && consultEndTime <= time;
    }
}