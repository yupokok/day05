package Boardgame;

public class Boardgame  {

    private int minTime;
    private int maxTime;
    private String name;
    public String year;

    public Boardgame( String game, String year, int minTime, int maxTime){

        this.minTime = minTime;
        this.maxTime = maxTime;
        this.name = name;
        this.year = year;
    }
    


    public String getName(){return name;}
    public void setName(String name) {this.name = name;}

    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public Integer getMinTime() {return minTime;}
    public void setMinTime(int minTime) {this.minTime = minTime;} 
    
    public Integer getMaxTime() {return maxTime;}
    public void setMaxTime(int maxTime) {this.maxTime = maxTime;} 


    public int getDuration() { 
        int dur = maxTime - minTime;
        if (0==dur) {
            double v = Math.floor((maxTime+minTime)/2);
            dur = (int)v;
        }
        return dur;

    }
    

    
}
