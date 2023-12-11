class Student
{
    private int arrivalTime;
    private String name;

    public Student(int arrivalTime, String name)
    {
        this.arrivalTime = arrivalTime;
        this.name = name;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    public String getName()
    {
        return name;
    }
}