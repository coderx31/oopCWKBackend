package models;

import java.io.Serializable;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {
    private int goalScored;

    /*default constructor*/
    public FootballClub(){
        super();
        this.goalScored = 0;
    }

    public FootballClub(String clubName, Address clubLocation ){
        super(clubName,clubLocation);

    }

    public int getGoalScored(){
        return this.goalScored;
    }

    public void setGoalScored(int goalScored){
        this.goalScored += goalScored;
    }

    @Override
    public String toString(){
        return super.toString()+
                "Goal Scored: "+this.goalScored+"\n";
    }

    @Override
    public int compareTo(FootballClub footballClub){
        if (this.getPoints() == footballClub.getPoints()){ // checking whether points are equal or not
            return this.getGoalScored() - footballClub.getGoalScored();  // if true compare with goals
        }else{
            return this.getPoints() - footballClub.getPoints();  // if not compare with points
        }
    }
}