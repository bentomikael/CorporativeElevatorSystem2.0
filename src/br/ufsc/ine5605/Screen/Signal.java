package br.ufsc.ine5605.Screen;


public enum Signal {
    //action map
        ACTION,
    //geral
        EMPITY,
        NEXT,
    //frame
        LOGOUT,
    //home
        FLOOR,
        ADMNISTRATIVE,
    //andares
        GROUND_FLOOR,
        FIRST_FLOOR,
        SECOND_FLOOR,
        THIRD_FLOOR,
        FOURTH_FLOOR,
        FIFTH_FLOOR,
    //adm 
        NEW_EMPLOYEE,
        DEL_EMPLOYEE,
        CHANGE_EMPLOYEE,
        REPORTS,
        LIST,
    //relatorios
        REPORT_FLOOR,
        REPORT_DAY,
        REPORT_REGISTERED,
        REPORT_REMOVED,
        REPORT_ALL,
    //listas
        LIST_ALL,
        LIST_OCCUPATION,
        LIST_FLOOR,
        LIST_WORK;
}
