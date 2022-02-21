package model;

public enum UserState {
    START,
    ENGRU,
    RUENG,
    ENGTR,
    TRENG,
    TRRU,
    RUTR;

    public String function(){
        switch (this){
            case RUTR -> {return "ru-tr";}
            case TRRU -> {return "tr-ru";}
            case ENGRU -> {return "en-ru";}
            case RUENG -> {return "ru-en";}
            case ENGTR -> {return "en-tr";}
            case TRENG -> {return "tr-en";}
        }
        return null;
    }
}
